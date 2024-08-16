package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    public Student findByFirstName(String firstName);
    public Optional<Student> findById(String id);
    public List<Student> findAll();
}
