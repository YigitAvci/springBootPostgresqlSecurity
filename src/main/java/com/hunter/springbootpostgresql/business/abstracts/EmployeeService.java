package com.hunter.springbootpostgresql.business.abstracts;

import com.hunter.springbootpostgresql.core.utilities.results.CustomSuccessDataResult;
import com.hunter.springbootpostgresql.core.utilities.results.CustomSuccessResult;
import com.hunter.springbootpostgresql.entities.concretes.Employee;

import java.util.List;

public interface EmployeeService {
    CustomSuccessDataResult<Employee> addEmployee(Employee employee);
    CustomSuccessDataResult<List<Employee>> getAllEmployees();
}
