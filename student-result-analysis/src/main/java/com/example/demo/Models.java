package com.example.demo;

/* STUDENT */
class Student {
    public long id;
    public String rollNo;
    public String name;
    public String dept;
    public int batchYear;
}

/* FACULTY */
class Faculty {
    public long id;
    public String empId;
    public String name;
    public String dept;
}

/* COURSE */
class Course {
    public long id;
    public String code;
    public String title;
    public int credits;
    public long facultyId;
}

/* ENROLLMENT */
class Enrollment {
    public long id;
    public long studentId;
    public long courseId;
    public int semester;
    public String status; // ENROLLED / DROPPED
}

/* FEES */
class FeesRecord {
    public long id;
    public long studentId;
    public int semester;
    public double totalFee;
    public double paidFee;
    public String status; // PAID / PARTIAL / PENDING
}

/* MARKS */
class MarksEntry {
    public long id;
    public long studentId;
    public long courseId;
    public int semester;
    public int internalMarks; // 0–30
    public int externalMarks; // 0–70
    public int total;
    public String grade;
    public boolean pass;
}