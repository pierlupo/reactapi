package com.reactapi.service;

import com.reactapi.dto.EmployeeCreateDTO;
import com.reactapi.dto.EmployeeUpdateDTO;
import com.reactapi.dto.EmployeeViewDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeViewDTO createEmployee(EmployeeCreateDTO employeeCreateDTO);

    EmployeeViewDTO updateEmployee(Integer id, EmployeeUpdateDTO employeeUpdateDTO);

    void deleteEmployee(Integer id);

    List<EmployeeViewDTO> listAllEmployeeDto();

    EmployeeViewDTO getEmployeeById(Integer id);

}

