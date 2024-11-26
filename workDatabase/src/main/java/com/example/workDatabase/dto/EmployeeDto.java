package com.example.workDatabase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

    private Long id;
    private String name;
    private String role;
    private double salary;
    private Long departmentId;
}
