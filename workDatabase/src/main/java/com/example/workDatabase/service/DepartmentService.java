package com.example.workDatabase.service;

import com.example.workDatabase.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department getDepartmentById(Long id);
    Department createDepartment(Department department);
    List<Department> getAllDepartments();
}
