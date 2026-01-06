package com.demo.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dept_code", unique = true, nullable = false)
    private String deptCode;

    private String name;

    @Column(name = "yearly_budget")
    private BigDecimal yearlyBudget;

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getYearlyBudget() {
        return yearlyBudget;
    }

    public void setYearlyBudget(BigDecimal yearlyBudget) {
        this.yearlyBudget = yearlyBudget;
    }
}
