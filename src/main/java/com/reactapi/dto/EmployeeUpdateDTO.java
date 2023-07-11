package com.reactapi.dto;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeUpdateDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String departmentId;
}
