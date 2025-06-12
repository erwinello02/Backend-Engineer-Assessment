package com.backend.assessment.service;

import com.backend.assessment.model.User;

import java.util.List;

public interface AuthenticationService {
    String generateToken(String username, String password);
    String registerUser(User user);
    List<User> getRegisteredUsers();
}
