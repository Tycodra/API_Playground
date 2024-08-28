package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
            Optional<Student> student = studentRepository.getById(id);
            return student.orElse(null);
    }

    public Student updateStudent(String id, Student studentDetails) {
            return studentRepository.save(studentDetails);
    }

    public boolean deleteStudent(String id) {
            Optional<Student> delStudent = studentRepository.getById(id);
            return studentRepository.deleteById(delStudent.get().getId());
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
}
