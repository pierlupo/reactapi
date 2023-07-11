package com.reactapi.service;

import com.reactapi.dto.DepartmentCreateDTO;
import com.reactapi.dto.DepartmentUpdateDTO;
import com.reactapi.dto.DepartmentViewDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentViewDTO createDepartment(DepartmentCreateDTO DepartmentCreateDTO);

    DepartmentViewDTO updateDepartment(Integer id, DepartmentUpdateDTO departmentUpdateDTO);

    void deleteDepartment(Integer id);

    List<DepartmentViewDTO> listAllDepartmentDto();

    DepartmentViewDTO getDepartmentById(Integer id);
}
