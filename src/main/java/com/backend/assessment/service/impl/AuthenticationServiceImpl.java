package com.backend.assessment.service.impl;

import com.backend.assessment.JWTSecurity.JwtUtil;
import com.backend.assessment.model.User;
import com.backend.assessment.repository.UserRepository;
import com.backend.assessment.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    public String generateToken(String username, String password) {
        Instant generateTokenStart = Instant.now();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    username,
                    password
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String generatedToken =  jwtUtil.generateToken(userDetails.getUsername());
        logger.info("Generate token start: "+generateTokenStart);
        return generatedToken;
    }

    public String registerUser(User user){
        Instant createUserStart = Instant.now();
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Error: Username is already taken!";
        }
        // Create new user's account
        User newUser = new User(
                null,
                user.getUsername(),
                encoder.encode(user.getPassword())
        );
        userRepository.save(newUser);
        logger.info("Create user start: "+createUserStart);
        return "User registered successfully!";
    }

    public List<User> getRegisteredUsers(){
        Instant getRegisteredUserStart = Instant.now();
        List<User> users = userRepository.findAll();
        logger.info("Get registered user start: "+getRegisteredUserStart);
        return users;
    }
}
