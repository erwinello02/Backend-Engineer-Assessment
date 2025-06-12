package com.backend.assessment.controller;

import com.backend.assessment.model.User;
import com.backend.assessment.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/auth-login")
    public String login(@RequestParam String username, @RequestParam String password){
        Instant authenticationLoginStart = Instant.now();
        String auth =  authenticationService.generateToken(username, password);
        logger.info("Authentication login start: "+ authenticationLoginStart);
        return auth;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        Instant registrationUserStart = Instant.now();
        String registerUser = authenticationService.registerUser(user);
        logger.info("Registration user start: "+ registrationUserStart);
        return registerUser;
    }

    @GetMapping("/users")
    public List<User> getRegisteredUsers(){
        Instant getRegisteredUserStart = Instant.now();
        List<User> users = authenticationService.getRegisteredUsers();
        logger.info("Get registered user start: "+ getRegisteredUserStart);
        return users;
    }
}
