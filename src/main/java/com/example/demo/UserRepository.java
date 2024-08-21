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
public class UserRepository {

    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private final ObjectMapper objectMapper;

    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public UserRepository(ObjectMapper objectMapper, DataSource dataSource) {
        this.objectMapper = objectMapper;
        this.template = new JdbcTemplate(dataSource);
        this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<User> getAllUsers() {
        return null;
    }

    public Optional<User> findById(String id) {
        return null;
    }

    public User save(User userDetails) {
        return null;
    }

    public User findByName(String name) {
        return null;
    }

    public void deleteById(String id) {

    }
}
