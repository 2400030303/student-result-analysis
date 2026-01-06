package com.demo.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.dao.*;
import com.demo.entity.*;
import com.demo.util.HibernateUtil;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== PROCUREPULSE MENU =====");
            System.out.println("1. Add Department");
            System.out.println("2. Add Employee");
            System.out.println("3. Add Vendor");
            System.out.println("4. Create Invoice Draft");
            System.out.println("5. Record Payment");
            System.out.println("6. View Audit Logs");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    addDepartment(sc);
                    break;

                case 2:
                    addEmployee(sc);
                    break;

                case 3:
                    addVendor(sc);
                    break;

                case 4:
                    createInvoice(sc);
                    break;

                case 5:
                    recordPayment(sc);
                    break;

                case 6:
                    viewAuditLogs();
                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }

    // ================= METHODS =================

    private static void addDepartment(Scanner sc) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Department d = new Department();
            System.out.print("Dept Code: ");
            d.setDeptCode(sc.nextLine());
            System.out.print("Dept Name: ");
            d.setName(sc.nextLine());
            System.out.print("Yearly Budget: ");
            d.setYearlyBudget(sc.nextBigDecimal());

            new DepartmentDao().save(session, d);

            log(session, "Department", d.getId(), "DEPARTMENT_CREATED");

            tx.commit();
            System.out.println("Department added successfully.");
        }
    }

    private static void addEmployee(Scanner sc) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Employee e = new Employee();
            System.out.print("Emp Code: ");
            e.setEmpCode(sc.nextLine());
            System.out.print("Name: ");
            e.setName(sc.nextLine());
            System.out.print("Email: ");
            e.setEmail(sc.nextLine());
            System.out.print("Dept ID: ");
            e.setDeptId(sc.nextLong());

            new EmployeeDao().save(session, e);

            log(session, "Employee", e.getId(), "EMPLOYEE_CREATED");

            tx.commit();
            System.out.println("Employee added successfully.");
        }
    }

    private static void addVendor(Scanner sc) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Vendor v = new Vendor();
            System.out.print("Vendor Code: ");
            v.setVendorCode(sc.nextLine());
            System.out.print("Vendor Name: ");
            v.setName(sc.nextLine());
            System.out.print("GSTIN: ");
            v.setGstin(sc.nextLine());
            System.out.print("Email: ");
            v.setEmail(sc.nextLine());
            System.out.print("Phone: ");
            v.setPhone(sc.nextLine());

            v.setStatus("ACTIVE");
            v.setCreatedOn(LocalDate.now());

            new VendorDao().save(session, v);

            log(session, "Vendor", v.getId(), "VENDOR_CREATED");

            tx.commit();
            System.out.println("Vendor added successfully.");
        }
    }

    private static void createInvoice(Scanner sc) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Invoice i = new Invoice();
            System.out.print("Invoice No: ");
            i.setInvoiceNo(sc.nextLine());
            System.out.print("Vendor ID: ");
            i.setVendorId(sc.nextLong());
            System.out.print("Dept ID: ");
            i.setDeptId(sc.nextLong());
            System.out.print("Created By (Emp ID): ");
            i.setCreatedBy(sc.nextLong());
            System.out.print("Amount: ");
            i.setAmount(sc.nextBigDecimal());
            System.out.print("Tax Amount: ");
            i.setTaxAmount(sc.nextBigDecimal());

            i.setInvoiceDate(LocalDate.now());
            i.setDueDate(LocalDate.now().plusDays(30));
            i.setStatus("DRAFT");

            new InvoiceDao().save(session, i);

            log(session, "Invoice", i.getId(), "INVOICE_DRAFT_CREATED");

            tx.commit();
            System.out.println("Invoice draft created.");
        }
    }

    private static void recordPayment(Scanner sc) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Payment p = new Payment();
            System.out.print("Invoice ID: ");
            Long invoiceId = sc.nextLong();
            System.out.print("Paid Amount: ");
            BigDecimal amt = sc.nextBigDecimal();

            p.setInvoiceId(invoiceId);
            p.setPaidAmount(amt);
            p.setPaidOn(LocalDateTime.now());
            p.setMode("CASH");

            new PaymentDao().save(session, p);

            log(session, "Payment", p.getId(), "PAYMENT_RECORDED");

            tx.commit();
            System.out.println("Payment recorded.");
        }
    }

    private static void viewAuditLogs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            new AuditLogDao().findAll(session)
                    .forEach(a -> System.out.println(
                            a.getAction() + " | " + a.getEntityName() + " | " + a.getAtTime()));
        }
    }

    private static void log(Session session, String entity, Long id, String action) {
        AuditLog log = new AuditLog();
        log.setEntityName(entity);
        log.setEntityId(id);
        log.setAction(action);
        log.setActor("SYSTEM");
        log.setAtTime(LocalDateTime.now());

        new AuditLogDao().save(session, log);
    }
}
