package com.hunter.springbootpostgresql.dataAccess.concretes;

import com.hunter.springbootpostgresql.dataAccess.abstracts.EmployeeDataAccessObject;
import com.hunter.springbootpostgresql.entities.concretes.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDataAccessObjectImplementation implements EmployeeDataAccessObject {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDataAccessObjectImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addEmployee(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.save(employee);
        session.close();
    }

    @Override
    public List<Employee> getAllEmployees() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Employee", Employee.class);
        List<Employee> employees = query.getResultList();
        session.close();
        return employees;
    }
}
