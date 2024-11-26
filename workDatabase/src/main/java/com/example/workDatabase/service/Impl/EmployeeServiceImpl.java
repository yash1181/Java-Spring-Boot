package com.example.workDatabase.service.Impl;


import com.example.workDatabase.entity.Department;
import com.example.workDatabase.entity.Employee;
import com.example.workDatabase.repository.DepartmentRepository;
import com.example.workDatabase.repository.EmployeeRepository;
import com.example.workDatabase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        Department department = departmentRepository.findById(employee.getDepartment().getId()).orElse(null);
        if (department != null) {
            employee.setDepartment(department);
            return employeeRepository.save(employee);
        }
        return null;
    }


    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setName(employeeDetails.getName());
            employee.setRole(employeeDetails.getRole());
            employee.setSalary(employeeDetails.getSalary());
            employee.setDepartment(employeeDetails.getDepartment());
            return employeeRepository.save(employee);
        }
        return null;
    }


    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
