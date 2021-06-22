package com.magma.sanaa.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class userController {

    @GetMapping(value = "/hello")
    public String test() {
        return "Hello World";
    }

    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }
}
