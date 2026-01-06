package com.demo.dao;

import java.util.List;
import org.hibernate.Session;
import com.demo.entity.Vendor;

public class VendorDao {

    public void save(Session session, Vendor vendor) {
        session.persist(vendor);
    }

    public Vendor findById(Session session, Long id) {
        return session.get(Vendor.class, id);
    }

    public Vendor findByVendorCode(Session session, String vendorCode) {
        return session.createQuery(
                "from Vendor v where v.vendorCode = :code", Vendor.class)
                .setParameter("code", vendorCode)
                .uniqueResult();
    }

    public List<Vendor> findActiveVendors(Session session) {
        return session.createQuery(
                "from Vendor v where v.status = 'ACTIVE'", Vendor.class)
                .getResultList();
    }
}
