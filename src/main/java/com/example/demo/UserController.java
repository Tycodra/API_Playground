package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(produces = "application/json")
public class UserController {
    @Autowired
    private UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String index() {
        return "Greetings from the API!";
    }
    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return this.userService.getAllUsers();
    }
    @GetMapping(value = "/users/{id}")
    public ResponseEntity getUser(@PathVariable String id) {
        User retUser = this.userService.getUserById(id);
        if (retUser != null) {
            return new ResponseEntity(retUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("UserID:" + id + " not found!", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(value = "/users")
    public ResponseEntity newUser(@RequestBody User user) {
        this.userService.addUser(user);
        return new ResponseEntity("CREATED " + user, HttpStatus.CREATED);
    }
    @PutMapping(value = "/users/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody User user) {
        User retUser = this.userService.updateUser(id, user);
        if(retUser != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("UserID:" + id + " not found!", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        User delUser = this.userService.deleteUser(id);
        if(delUser != null) {
            return new ResponseEntity("DELETED " + delUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("UserID " + id + " does not exist", HttpStatus.NOT_FOUND);
        }
    }
}
