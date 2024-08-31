package com.example.demo.services;

import com.example.demo.EmailAlreadyExistsException;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
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
        if (userRepository.findById(id).isPresent()) {
            Optional<User> delUser = userRepository.findById(id);
            userRepository.deleteById(id);
            return delUser.orElse(null);
        }
        return null;
    }
}
