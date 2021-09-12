package com.hunter.springbootpostgresql.dataAccess.abstracts;

import com.hunter.springbootpostgresql.entities.concretes.Employee;

import java.util.List;

public interface EmployeeDataAccessObject {

    void addEmployee(Employee employee);
    List<Employee> getAllEmployees();
}
