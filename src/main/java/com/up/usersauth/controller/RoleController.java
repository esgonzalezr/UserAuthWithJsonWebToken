package com.up.usersauth.controller;

import com.up.usersauth.entity.Role;
import com.up.usersauth.entity.User;
import com.up.usersauth.exceptions.ExceptionHandler;
import com.up.usersauth.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable long id) {
        return roleService.findById(id)
                .map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Role role) {
        if (roleService.findByName(role.getName()).isEmpty()) {
            return new ResponseEntity<>(roleService.create(role), HttpStatus.OK);
        } else {
            exceptionHandler.setMessage("Duplicate entry");
            exceptionHandler.setDetails("Given role name already exists.");
            return new ResponseEntity<>(exceptionHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        return roleService.deleteById(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Role> update(@RequestBody Role role) {
        return roleService.findById(role.getId())
                .map(u -> new ResponseEntity<>(roleService.update(role).get(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
