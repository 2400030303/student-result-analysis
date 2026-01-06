package com.demo.dao;

import org.hibernate.Session;
import com.demo.entity.Employee;

public class EmployeeDao {

    public void save(Session session, Employee emp) {
        session.persist(emp);
    }

    public Employee findById(Session session, Long id) {
        return session.get(Employee.class, id);
    }

    public Employee findByEmpCode(Session session, String empCode) {
        return session.createQuery(
                "from Employee e where e.empCode = :code", Employee.class)
                .setParameter("code", empCode)
                .uniqueResult();
    }
}
