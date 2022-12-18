package com.up.usersauth.controller;

import com.up.usersauth.entity.User;
import com.up.usersauth.exceptions.ExceptionHandler;
import com.up.usersauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isEmpty()) {
            return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
        } else {
            exceptionHandler.setMessage("Duplicate entry");
            exceptionHandler.setDetails("Given username already exists.");
            return new ResponseEntity<>(exceptionHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        return userService.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<User> getByUsername(@RequestParam(name = "username") String username) {
        return userService.findByUsername(username)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        return userService.deleteById(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        return userService.findById(user.getId())
                .map(u -> new ResponseEntity<>(userService.update(user).get(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
