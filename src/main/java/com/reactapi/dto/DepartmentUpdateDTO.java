package com.reactapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentUpdateDTO {

    private  Integer id;

    private String departmentName;

    private String departmentDescription;
}
