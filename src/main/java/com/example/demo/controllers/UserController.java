package com.example.demo.controllers;

import java.util.List;

import com.example.demo.EmailAlreadyExistsException;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(produces = "application/json")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String index() {
        return "Greetings from the API!";
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id) {
        User retUser = this.userService.getUserById(id);
        if (retUser != null) {
            return ResponseEntity.ok(retUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserID:" + id + " not found!");
        }
    }

    @PostMapping(value = "/users")
    public ResponseEntity<Object> newUser(@RequestBody User user) {
        try {
            User newUser = this.userService.addUser(user);
            if (newUser != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("CREATED " + newUser.toString());
            }
        } catch (EmailAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
        return null;
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody User user) {
        User retUser = this.userService.updateUser(id, user);
        if(retUser != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id: " + id + " not found!");
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        User delUser = this.userService.deleteUser(id);
        if(delUser != null) {
            return ResponseEntity.ok("DELETED: " + delUser.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id: " + id + " not found!");
        }
    }
}
