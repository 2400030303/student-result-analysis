package com.example.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final DataStore store;

    public ApiController(DataStore store) {
        this.store = store;
    }

    /* ---------------- STUDENT ---------------- */
    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        student.id = store.studentSeq.incrementAndGet();
        store.students.put(student.id, student);
        return student;
    }

    /* ---------------- FACULTY ---------------- */
    @PostMapping("/faculty")
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        faculty.id = store.facultySeq.incrementAndGet();
        store.faculty.put(faculty.id, faculty);
        return faculty;
    }

    /* ---------------- COURSE ---------------- */
    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        if (!store.faculty.containsKey(course.facultyId)) {
            throw new RuntimeException("Faculty not found");
        }
        course.id = store.courseSeq.incrementAndGet();
        store.courses.put(course.id, course);
        return course;
    }

    /* ---------------- ENROLLMENT ---------------- */
    @PostMapping("/enrollments")
    public Enrollment enrollStudent(@RequestBody Enrollment enroll) {
        if (!store.students.containsKey(enroll.studentId))
            throw new RuntimeException("Student not found");

        if (!store.courses.containsKey(enroll.courseId))
            throw new RuntimeException("Course not found");

        enroll.id = store.enrollSeq.incrementAndGet();
        enroll.status = "ENROLLED";
        store.enrollments.put(enroll.id, enroll);
        return enroll;
    }

    /* ---------------- MARKS ---------------- */
    @PostMapping("/marks")
    public MarksEntry enterMarks(@RequestBody MarksEntry marks) {
        marks.id = store.marksSeq.incrementAndGet();
        marks.total = marks.internalMarks + marks.externalMarks;
        marks.pass = marks.total >= 40;

        if (marks.total >= 90) marks.grade = "A";
        else if (marks.total >= 75) marks.grade = "B";
        else if (marks.total >= 60) marks.grade = "C";
        else marks.grade = "F";

        store.marks.put(marks.id, marks);
        return marks;
    }

    /* ---------------- STUDENT RESULT ---------------- */
    @GetMapping("/analysis/student/{id}")
    public List<MarksEntry> studentResult(
            @PathVariable long id,
            @RequestParam int semester) {

        if (!store.students.containsKey(id))
            throw new RuntimeException("Student not found");

        List<MarksEntry> result = new ArrayList<>();
        for (MarksEntry m : store.marks.values()) {
            if (m.studentId == id && m.semester == semester) {
                result.add(m);
            }
        }
        return result;
    }

    /* ---------------- SEMESTER TOPPERS ---------------- */
    @GetMapping("/analysis/semester/{semester}/toppers")
    public List<MarksEntry> semesterToppers(@PathVariable int semester) {
        List<MarksEntry> list = new ArrayList<>();
        for (MarksEntry m : store.marks.values()) {
            if (m.semester == semester) {
                list.add(m);
            }
        }
        list.sort(Comparator.comparingInt(a -> -a.total));
        return list;
    }
}