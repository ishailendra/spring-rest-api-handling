package dev.techsphere.webclienttester.controller;

import dev.techsphere.webclienttester.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
public class StudentController {

    private List<Student> students;

    @PostConstruct
    public void createData() {
        students = new ArrayList<>();
        students.add(new Student(1, "John", 9.08, "Kolkata, WB"));
        students.add(new Student(2, "Jack", 9.08, "Patna, Bihar"));
        students.add(new Student(3, "Ramesh", 9.08, "Lucknow, UP"));
        students.add(new Student(4, "Shail", 9.08, "Bengalauru, Karnatak"));
        students.add(new Student(5, "Karan", 9.08, "Mumbai, Maharashtra"));
    }


    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") Integer id) {
        return students.stream().filter(student -> student.getId() == id).findFirst().get();
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/login/{id}")
    public ResponseEntity<Student> getStudentLogin(@PathVariable("id") Integer id) {
        HttpHeaders respHeader = new HttpHeaders();
        respHeader.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");

        Student std = students.stream().filter(student -> student.getId() == id).findFirst().get();

        return new ResponseEntity<>(std, respHeader, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<String> addNewStudent (@RequestBody Student student) {
        Integer id = students.size();
        student.setId(id);

        students.add(student);

        return new ResponseEntity<>("{\"message\":\"Student record added successfully\"}", HttpStatus.CREATED);
    }

    @PutMapping("/students")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        int index = IntStream.range(0, students.size())
                .filter(i -> students.get(i).getId() == student.getId())
                .findFirst()
                .orElse(-1);
        if (index != -1) {
            students.set(index, student);
            return new ResponseEntity<> (HttpStatus.NO_CONTENT);
        }
        return null;
    }

    @DeleteMapping("/students")
    public ResponseEntity<?> deleteStudent(@RequestBody Student student) {
        int index = IntStream.range(0, students.size())
                .filter(i -> students.get(i).getId() == student.getId())
                .findFirst()
                .orElse(-1);
        if (index != -1) {
            students.remove(index);
            return new ResponseEntity<> (HttpStatus.NO_CONTENT);
        }
        return null;
    }

    @GetMapping("/students/error/{id}")
    public ResponseEntity<?> getStudentError(@PathVariable("id") Integer id) {
        return new ResponseEntity<String>("{\"message\":\"Student not found\"}", HttpStatus.NOT_FOUND);
    }
}
