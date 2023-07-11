package com.reactapi.controller;

import com.reactapi.dto.*;
import com.reactapi.exception.NotFoundException;
import com.reactapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService IEmployeeService;


    @PostMapping("")
    public EmployeeViewDTO createEmployee(@RequestBody EmployeeCreateDTO employeeCreateDTO){
        System.out.println("coucou " + employeeCreateDTO);
        return IEmployeeService.createEmployee(employeeCreateDTO);
    }

    @GetMapping("")
    public ResponseEntity<List<EmployeeViewDTO>> getAllEmployees() {

        return new ResponseEntity(IEmployeeService.listAllEmployeeDto(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeViewDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeUpdateDTO employeeUpdateDTO) {
        if(IEmployeeService.getEmployeeById(id) == null){
            throw new NotFoundException("Departement", "id", id);
        }
        return new ResponseEntity<>(IEmployeeService.updateEmployee(id,employeeUpdateDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeViewDTO> getEmployeeById(@PathVariable(name = "id") Integer id){

        return ResponseEntity.ok(IEmployeeService.getEmployeeById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee( @PathVariable Integer id){

        if(IEmployeeService.getEmployeeById(id) == null){
            throw new NotFoundException("Employee", "id", id);
        }

        try{
            IEmployeeService.deleteEmployee(id);
            return ResponseEntity.status(204).build();

        }catch (Exception exception){
            throw exception;
        }

    }

}
