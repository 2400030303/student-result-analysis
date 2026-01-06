package com.demo.dao;

import java.util.List;
import org.hibernate.Session;
import com.demo.entity.InvoiceLine;

public class InvoiceLineDao {

    public void save(Session session, InvoiceLine line) {
        session.persist(line);
    }

    public List<InvoiceLine> findByInvoiceId(Session session, Long invoiceId) {
        return session.createQuery(
                "from InvoiceLine l where l.invoiceId = :id", InvoiceLine.class)
                .setParameter("id", invoiceId)
                .getResultList();
    }

    public void deleteByInvoiceId(Session session, Long invoiceId) {
        session.createQuery(
                "delete from InvoiceLine l where l.invoiceId = :id")
                .setParameter("id", invoiceId)
                .executeUpdate();
    }
}
