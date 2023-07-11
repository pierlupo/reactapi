package com.reactapi.service.impl;

import com.reactapi.dto.DepartmentCreateDTO;
import com.reactapi.dto.DepartmentUpdateDTO;
import com.reactapi.dto.DepartmentViewDTO;
import com.reactapi.entity.Department;
import com.reactapi.exception.GlobalException;
import com.reactapi.exception.NotFoundException;
import com.reactapi.repository.DepartmentRepo;
import com.reactapi.service.DepartmentService;
import com.reactapi.util.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IDepartmentService implements DepartmentService {

    @Autowired
    private DtoUtil dtoUtil;

    @Autowired
    private DepartmentRepo departmentRepo;


    @Override
    public DepartmentViewDTO createDepartment(DepartmentCreateDTO department) {

        Department department1 = dtoUtil.convertToDto(department, new Department(), Department.class);

        Department department2 = departmentRepo.save(department1);

        return dtoUtil.convertToDto(department2, new DepartmentViewDTO(), DepartmentViewDTO.class);
    }

    @Override
    public DepartmentViewDTO updateDepartment(Integer id, DepartmentUpdateDTO departmentUpdateDTO) {

        if(departmentRepo.findById(id).isPresent()) {
            Department department = departmentRepo.findById(id).get();
            department.setDepartmentName(departmentUpdateDTO.getDepartmentName());
            department.setDepartmentDescription(departmentUpdateDTO.getDepartmentDescription());
            Department updateDepartment = departmentRepo.save(department);
            return dtoUtil.convertToDto(updateDepartment, new DepartmentViewDTO(), DepartmentViewDTO.class);
        }else{
            return null;
        }
    }


    @Override
    public void deleteDepartment(Integer id) {

            Department department = getDepartmentByIdFromDatabase(id);

            departmentRepo.deleteById(id);
    }

    @Override
    public List<DepartmentViewDTO> listAllDepartmentDto() {

        List<DepartmentViewDTO> liste = new ArrayList<>();

        departmentRepo.findAll().forEach(department -> {

            liste.add(dtoUtil.convertToDto(department, new DepartmentViewDTO(), DepartmentViewDTO.class));

        });

        return liste;
    }

    @Override
    public DepartmentViewDTO getDepartmentById(Integer id) {

        return dtoUtil.convertToDto(departmentRepo.findById(id).orElseThrow(()->new GlobalException("Ressource non trouvée")), new DepartmentViewDTO(), DepartmentViewDTO.class);
    }


    private Department getDepartmentByIdFromDatabase(Integer id) {

        return departmentRepo.findById(id).orElseThrow(() -> new NotFoundException("Departement", "id", id));
    }
}
