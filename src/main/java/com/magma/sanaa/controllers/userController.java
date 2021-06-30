package com.magma.sanaa.controllers;


import com.magma.sanaa.entities.User;
import com.magma.sanaa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/currentUser")
    public User getCurrentUser(Authentication authentication) {
        return userService.getUser(authentication.getName()).orElseThrow();
    }

    @PreAuthorize("hasRole('User_Read')")
    @GetMapping(value = "/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('User_Create')")
    @PostMapping(value = "/user")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PreAuthorize("hasRole('User_Write')")
    @PutMapping(value = "/user/{id}")
    public User editUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        return userService.saveUser(user);
    }

    @PreAuthorize("hasRole('User_Delete')")
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
