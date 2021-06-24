package com.magma.sanaa.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class userController {

    @GetMapping(value = "/hello")
    public String test() {
        return "Hello World";
    }
    @PreAuthorize("hasRole('User_Read')")
    @GetMapping(value = "/protected")
    public String protectedRes() {
        return "Hello protected";
    }

}
