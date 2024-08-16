package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a user by ID
    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // Retrieve a user by name
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    // Add a new user
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(String id, User userDetails) {
        if (userRepository.existsById(id)) {
            userDetails.setId(id);
            return userRepository.save(userDetails);
        }
        return null;
    }

    // Delete a user
    public User deleteUser(String id) {
        if (userRepository.existsById(id)) {
            Optional<User> delUser = userRepository.findById(id);
            userRepository.deleteById(id);
            return delUser.orElse(null);
        }
        return null;
    }
}
