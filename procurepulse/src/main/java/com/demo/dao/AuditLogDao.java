package com.demo.dao;

import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Session;
import com.demo.entity.AuditLog;

public class AuditLogDao {

    public void save(Session session, AuditLog log) {
        session.persist(log);
    }

    public List<AuditLog> findAll(Session session) {
        return session.createQuery(
                "from AuditLog a order by a.atTime desc", AuditLog.class)
                .getResultList();
    }

    public void deleteOlderThan(Session session, LocalDateTime cutoff) {
        session.createQuery(
                "delete from AuditLog a where a.atTime < :cutoff")
                .setParameter("cutoff", cutoff)
                .executeUpdate();
    }
}
