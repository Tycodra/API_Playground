package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private final Logger logger = LoggerFactory.getLogger(StudentRepository.class);
    private final ObjectMapper objectMapper;

    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public StudentRepository(ObjectMapper objectMapper, DataSource dataSource) {
        this.objectMapper = objectMapper;
        this.template = new JdbcTemplate(dataSource);
        this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Optional<Student> getById(String id) {
        return Optional.empty();
    }

    public Student save(Student studentDetails) {
        return null;
    }

    public boolean deleteById(String id) {
        return true;
    }

    public List<Student> findAll() {
        return null;
    }
}
