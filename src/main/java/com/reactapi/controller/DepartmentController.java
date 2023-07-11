package com.reactapi.controller;


import com.reactapi.dto.DepartmentCreateDTO;
import com.reactapi.dto.DepartmentUpdateDTO;
import com.reactapi.dto.DepartmentViewDTO;
import com.reactapi.exception.NotFoundException;
import com.reactapi.service.DepartmentService;
import com.reactapi.service.impl.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService IDepartmentService ;

    @PostMapping("")
    public DepartmentViewDTO createDepartment(@RequestBody DepartmentCreateDTO departmentCreateDTO){

        return IDepartmentService.createDepartment(departmentCreateDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentViewDTO> updateDepartment(@PathVariable Integer id, @RequestBody DepartmentUpdateDTO departmentUpdateDTO) {
        if(IDepartmentService.getDepartmentById(id) == null){
            throw new NotFoundException("Departement", "id", id);
        }
        return new ResponseEntity<>(IDepartmentService.updateDepartment(id,departmentUpdateDTO), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<DepartmentViewDTO>> getAllDepartments() {

        return new ResponseEntity(IDepartmentService.listAllDepartmentDto(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentViewDTO> getDepartmentById(@PathVariable(name = "id") Integer id){

        return ResponseEntity.ok(IDepartmentService.getDepartmentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment( @PathVariable Integer id){

        if(IDepartmentService.getDepartmentById(id) == null){
            throw new NotFoundException("Departement", "id", id);
        }

        try{
            IDepartmentService.deleteDepartment(id);
            return ResponseEntity.status(204).build();

        }catch (Exception exception){
            throw exception;
        }

    }
}
