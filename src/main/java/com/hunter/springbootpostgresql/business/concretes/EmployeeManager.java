package com.hunter.springbootpostgresql.business.concretes;

import com.hunter.springbootpostgresql.business.abstracts.EmployeeService;
import com.hunter.springbootpostgresql.core.utilities.results.CustomSuccessDataResult;
import com.hunter.springbootpostgresql.dataAccess.concretes.EmployeeDataAccessObjectImplementation;
import com.hunter.springbootpostgresql.entities.concretes.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmployeeManager implements EmployeeService {

    EmployeeDataAccessObjectImplementation employeeDataAccessObjectImplementation;

    @Autowired
    public EmployeeManager(EmployeeDataAccessObjectImplementation employeeDataAccessObjectImplementation) {
        this.employeeDataAccessObjectImplementation = employeeDataAccessObjectImplementation;
    }

    @Transactional
    @Override
    public CustomSuccessDataResult<Employee> addEmployee(Employee employee) {
        this.employeeDataAccessObjectImplementation.addEmployee(employee);
        return new CustomSuccessDataResult<>("employee addition process is successful", employee);
    }

    @Override
    public CustomSuccessDataResult<List<Employee>> getAllEmployees() {
        return new CustomSuccessDataResult<>("these are all employees", this.employeeDataAccessObjectImplementation.getAllEmployees());
    }
}
