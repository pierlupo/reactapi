package com.reactapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCreateDTO {

    private Integer id;

    private String departmentName;

    private String departmentDescription;
}
