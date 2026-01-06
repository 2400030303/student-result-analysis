package com.demo.dao;

import org.hibernate.Session;
import com.demo.entity.Department;

public class DepartmentDao {

    public void save(Session session, Department dept) {
        session.persist(dept);
    }

    public Department findById(Session session, Long id) {
        return session.get(Department.class, id);
    }

    public Department findByCode(Session session, String deptCode) {
        return session.createQuery(
                "from Department d where d.deptCode = :code", Department.class)
                .setParameter("code", deptCode)
                .uniqueResult();
    }
}
