package com.reactapi.service.impl;


import com.reactapi.dto.DepartmentViewDTO;
import com.reactapi.dto.EmployeeCreateDTO;
import com.reactapi.dto.EmployeeUpdateDTO;
import com.reactapi.dto.EmployeeViewDTO;
import com.reactapi.entity.Department;
import com.reactapi.entity.Employee;
import com.reactapi.exception.GlobalException;
import com.reactapi.exception.NotFoundException;
import com.reactapi.repository.EmployeeRepo;
import com.reactapi.service.EmployeeService;
import com.reactapi.util.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IEmployeeService implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DtoUtil dtoUtil;


    @Override
    public EmployeeViewDTO createEmployee(EmployeeCreateDTO employee) {

        Employee employee1 = dtoUtil.convertToDto(employee, new Employee(), Employee.class);

        Employee employee2 = employeeRepo.save(employee1);

        return dtoUtil.convertToDto(employee2, new EmployeeViewDTO(), EmployeeViewDTO.class);
    }

    @Override
    public EmployeeViewDTO updateEmployee(Integer id, EmployeeUpdateDTO employeeUpdateDTO) {
        if(employeeRepo.findById(id).isPresent()) {
            Employee employee = employeeRepo.findById(id).get();
            employee.setFirstName(employeeUpdateDTO.getFirstName());
            employee.setLastName(employeeUpdateDTO.getLastName());
            employee.setEmail(employeeUpdateDTO.getEmail());
            Employee updateEmployee = employeeRepo.save(employee);
            return dtoUtil.convertToDto(updateEmployee, new EmployeeViewDTO(), EmployeeViewDTO.class);
        }else{
            return null;
        }
    }

    @Override
    public void deleteEmployee(Integer id) {

        Employee employee = getEmployeeByIdFromDatabase(id);

        employeeRepo.deleteById(id);
    }

    @Override
    public List<EmployeeViewDTO> listAllEmployeeDto() {

        List<EmployeeViewDTO> liste = new ArrayList<>();

        employeeRepo.findAll().forEach(employee -> {

            liste.add(dtoUtil.convertToDto(employee, new EmployeeViewDTO(), EmployeeViewDTO.class));

        });

        return liste;
    }

    @Override
    public EmployeeViewDTO getEmployeeById(Integer id) {

        return dtoUtil.convertToDto(employeeRepo.findById(id).orElseThrow(() -> new GlobalException("Resource non trouvée")), new EmployeeViewDTO(), EmployeeViewDTO.class);

    }
    private Employee getEmployeeByIdFromDatabase(Integer id) {

        return employeeRepo.findById(id).orElseThrow(() -> new NotFoundException("Employee", "id", id));
    }



}
