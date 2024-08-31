package com.example.demo.services;

import com.example.demo.EmailAlreadyExistsException;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    // Retrieve a user by ID
    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // Retrieve a user by name
    public List<User> getUserByName(String name) {
        // There is a chance multiple users have the same name
        return userRepository.findByName(name);
    }

    // Add a new user
    public User addUser(User user) {
        // Check if email already in use
        if (userRepository.emailExists(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email " + user.getEmail() + " is already in use.");
        }

        Optional<User> newUser = userRepository.insertUser(user);
        return newUser.orElse(null);
    }

    // Update an existing user
    public User updateUser(String id, User userDetails) {
            userDetails.setId(id);
            Optional<User> updatedUser = userRepository.updateUser(userDetails);
            return updatedUser.orElse(null);
    }

    // Delete a user
    public User deleteUser(String id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                userRepository.deleteById(id);
                return userOptional.get();
            }
        } catch (Exception e) {
            logger.warn("Failed to delete user with ID {}", id);
            throw new RuntimeException("Failed to delete User with ID " + id);
        }

        return null;
    }
}
