package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = "application/json")
public class StudentController {
    @Autowired
    private StudentService studentService;

    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/students")
    public List<Student> getStudents() {
        return this.studentService.getAllStudents();
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity getStudent(@PathVariable String id) {
        Student retStudent = this.studentService.getStudentById(id);
        if (retStudent != null) {
            return new ResponseEntity(retStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity("StudentID: " + id + " not found!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/students")
    public ResponseEntity newStudent(@RequestBody Student student) {
        this.studentService.addStudent(student);
        return new ResponseEntity("CREATED " + student, HttpStatus.OK);
    }

    @PutMapping(value = "/students/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody Student student) {
        Student retStudent = this.studentService.updateStudent(id, student);
        if (retStudent != null) {
            return new ResponseEntity(student, HttpStatus.OK);
        }
        return new ResponseEntity("StudentID: " + id + " not found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/students/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        boolean deletedStudent = this.studentService.deleteStudent(id);
        if (deletedStudent) {
            return new ResponseEntity("DELETED " + id, HttpStatus.OK);
        }
            return new ResponseEntity("StudentID: " + id + " does not exist", HttpStatus.NOT_FOUND);
    }

}
