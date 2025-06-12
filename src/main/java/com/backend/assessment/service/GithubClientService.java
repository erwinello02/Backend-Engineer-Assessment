package com.backend.assessment.service;

import com.backend.assessment.dto.GithubUser;

import java.util.Optional;

public interface GithubClientService {
    Optional<GithubUser> fetchUser(String username);
}