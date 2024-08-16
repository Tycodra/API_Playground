package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        if (studentRepository.existsById(id)) {
            Optional<Student> student = studentRepository.findById(id);
            return student.orElse(null);
        }
        return null;
    }

    public Student updateStudent(String id, Student studentDetails) {
        if (studentRepository.existsById(id)) {
            return studentRepository.save(studentDetails);
        }
        return null;
    }

    public Student deleteStudent(String id) {
        if (studentRepository.existsById(id)) {
            Optional<Student> delStudent = studentRepository.findById(id);
            studentRepository.deleteById(id);
            return delStudent.orElse(null);
        }
        return null;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
}
