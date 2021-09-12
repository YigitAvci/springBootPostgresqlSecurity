package com.hunter.springbootpostgresql.api.controllers;

import com.hunter.springbootpostgresql.business.concretes.EmployeeManager;
import com.hunter.springbootpostgresql.core.utilities.results.CustomDataResult;
import com.hunter.springbootpostgresql.entities.concretes.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    private EmployeeManager employeeManager;

    @Autowired
    public EmployeesController(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    @PostMapping("/addEmployee")
    public CustomDataResult<Employee> addEmployee(@RequestBody Employee employee) {
        return employeeManager.addEmployee(employee);
    }

    @GetMapping("/getAllEmployees")
    public CustomDataResult<List<Employee>> getAllEmployees() {
        return employeeManager.getAllEmployees();
    }
}
