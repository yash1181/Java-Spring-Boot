package com.example.workDatabase.controller;


import com.example.workDatabase.entity.Department;
import com.example.workDatabase.service.Impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<String> createDepartment(@RequestBody Department department) {
        departmentServiceImpl.createDepartment(department);
        return new ResponseEntity<>("Department created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentServiceImpl.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

}
