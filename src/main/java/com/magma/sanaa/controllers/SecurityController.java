package com.magma.sanaa.controllers;

import com.magma.sanaa.services.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecurityController {

    @GetMapping(value = "/hello")
    public String test() {
        return "Hello World";
    }

    // used only for test purpose
    @GetMapping(value = "/username")
    @ResponseBody
    public Principal currentUserName(Principal principal) {
        return principal;
    }

}
