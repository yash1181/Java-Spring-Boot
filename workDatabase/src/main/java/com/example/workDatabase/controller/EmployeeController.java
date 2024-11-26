package com.example.workDatabase.controller;


import com.example.workDatabase.dto.EmployeeDto;
import com.example.workDatabase.entity.Department;
import com.example.workDatabase.entity.Employee;
import com.example.workDatabase.service.DepartmentService;
import com.example.workDatabase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Department department = departmentService.getDepartmentById(employeeDto.getDepartmentId());
        if (department != null) {
            Employee employee = new Employee();
            employee.setName(employeeDto.getName());
            employee.setRole(employeeDto.getRole());
            employee.setSalary(employeeDto.getSalary());
            employee.setDepartment(department);
            employeeService.createEmployee(employee);
            return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Department not found", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Department department = departmentService.getDepartmentById(employeeDto.getDepartmentId());
        if (department != null) {
            Employee employee = employeeService.getEmployeeById(id);
            if (employee != null) {
                employee.setName(employeeDto.getName());
                employee.setRole(employeeDto.getRole());
                employee.setSalary(employeeDto.getSalary());
                employee.setDepartment(department);
                employeeService.updateEmployee(id, employee);
                return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Department not found", HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }
}
