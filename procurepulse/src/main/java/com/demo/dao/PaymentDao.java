package com.demo.dao;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import com.demo.entity.Payment;

public class PaymentDao {

    public void save(Session session, Payment payment) {
        session.persist(payment);
    }

    public List<Payment> findByInvoiceId(Session session, Long invoiceId) {
        return session.createQuery(
                "from Payment p where p.invoiceId = :id", Payment.class)
                .setParameter("id", invoiceId)
                .getResultList();
    }

    public BigDecimal totalPaidForInvoice(Session session, Long invoiceId) {
        return session.createQuery(
                "select coalesce(sum(p.paidAmount),0) from Payment p where p.invoiceId = :id",
                BigDecimal.class)
                .setParameter("id", invoiceId)
                .uniqueResult();
    }
}
