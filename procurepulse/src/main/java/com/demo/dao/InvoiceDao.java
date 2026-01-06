package com.demo.dao;

import java.util.List;
import org.hibernate.Session;
import com.demo.entity.Invoice;

public class InvoiceDao {

    public void save(Session session, Invoice invoice) {
        session.persist(invoice);
    }

    public Invoice findById(Session session, Long id) {
        return session.get(Invoice.class, id);
    }

    public Invoice findByInvoiceNo(Session session, String invoiceNo) {
        return session.createQuery(
                "from Invoice i where i.invoiceNo = :no", Invoice.class)
                .setParameter("no", invoiceNo)
                .uniqueResult();
    }

    public List<Invoice> findByStatus(Session session, String status) {
        return session.createQuery(
                "from Invoice i where i.status = :st", Invoice.class)
                .setParameter("st", status)
                .getResultList();
    }
}
