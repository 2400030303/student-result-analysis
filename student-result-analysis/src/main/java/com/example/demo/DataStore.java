package com.example.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class DataStore {

    public AtomicLong studentSeq = new AtomicLong(0);
    public AtomicLong facultySeq = new AtomicLong(0);
    public AtomicLong courseSeq = new AtomicLong(0);
    public AtomicLong enrollSeq = new AtomicLong(0);
    public AtomicLong feesSeq = new AtomicLong(0);
    public AtomicLong marksSeq = new AtomicLong(0);

    public Map<Long, Student> students = new ConcurrentHashMap<>();
    public Map<Long, Faculty> faculty = new ConcurrentHashMap<>();
    public Map<Long, Course> courses = new ConcurrentHashMap<>();
    public Map<Long, Enrollment> enrollments = new ConcurrentHashMap<>();
    public Map<Long, FeesRecord> fees = new ConcurrentHashMap<>();
    public Map<Long, MarksEntry> marks = new ConcurrentHashMap<>();
}