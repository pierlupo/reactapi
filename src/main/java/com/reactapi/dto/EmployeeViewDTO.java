package com.reactapi.dto;


import com.reactapi.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeViewDTO {


    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String departmentId;
}
