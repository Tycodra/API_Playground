package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


@Repository
public class UserRepository {

    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public UserRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<User> getAllUsers() {

        String query = """
                SELECT id, first_name, last_name, email FROM users
                                            WHERE email LIKE :emailFilter
                """;

        MapSqlParameterSource paramMap = new MapSqlParameterSource() {{
            addValue("emailFilter", "%@example.com");
        }};

        try {
            return namedTemplate.query(query, paramMap, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            logger.warn("Error getting all users - e={}", e.toString());
            throw e;
        }
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
