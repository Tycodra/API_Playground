package com.example.demo.repositories;

import com.example.demo.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
            addValue("emailFilter", "%.com");
        }};

        try {
            return namedTemplate.query(query, paramMap, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            logger.warn("Error getting all users - e={}", e.toString());
            throw e;
        }
    }

    public Optional<User> findById(String id) {
        String query = """
                SELECT * FROM users WHERE id = :idFilter
                """;

        MapSqlParameterSource paramMap = new MapSqlParameterSource() {{
            addValue("idFilter", id);
        }};

        try {
            User user =  namedTemplate.queryForObject(query, paramMap, new BeanPropertyRowMapper<>(User.class));
            return Optional.ofNullable(user);
        } catch (Exception e) {
            logger.warn("Error getting user - e={}", e.toString());
            return Optional.empty();
        }
    }

    public boolean emailExists(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE email = :emailFilter";

        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("emailFilter", email);

        Integer count = namedTemplate.queryForObject(query, paramMap, Integer.class);
        return count != null && count > 0;
    }

    public Optional<User> insertUser(User userDetails) {
        String query = "INSERT INTO users (first_name, last_name, email) VALUES (:first_name, :last_name, :email)";

        MapSqlParameterSource paramMap = new MapSqlParameterSource();

        paramMap.addValue("first_name", userDetails.getFirst_name())
                .addValue("last_name", userDetails.getLast_name())
                .addValue("email", userDetails.getEmail());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedTemplate.update(query, paramMap, keyHolder);

        userDetails.setId(keyHolder.getKey().toString());
        return Optional.of(userDetails);
    }

    public Optional<User> updateUser(User user) {
        String query = "UPDATE users " +
                "SET first_name = :first_name, " +
                "last_name = :last_name, " +
                "email = :email " +
                "WHERE id = :id";

        MapSqlParameterSource mapParam = new MapSqlParameterSource();
        mapParam.addValue("first_name", user.getFirst_name());
        mapParam.addValue("last_name", user.getLast_name());
        mapParam.addValue("email", user.getEmail());
        mapParam.addValue("id", user.getId());

        namedTemplate.update(query, mapParam);

        return Optional.of(user);
    }

//    There might be multiple users with the same first and last name
    public List<User> findByName(String name) {
        /*
        Assuming name includes:
            only first and last name
            names are only one word without spaces, hyphens are fine
         */

        // Split name into first and last names
        String first_name;
        String last_name;
        if (name.contains(",")) { // Assuming "last_name, first_name"
            String[] names = name.split(", ");
            last_name = names[0];
            first_name = names[1];
        } else {
            String[] names = name.split(" ");
            first_name = names[0];
            last_name = names[1];
        }

        String query = "SELECT * FROM users WHERE first_name = :first_name AND last_name = :last_name";

        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("first_name", first_name);
        paramMap.addValue("last_name", last_name);

        try {
            return namedTemplate.query(query, paramMap, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            logger.warn("Error getting all users - e={}", e.toString());
            throw e;
        }
    }

    public void deleteById(String id) {

    }
}
