package com.backend.assessment.service;

import com.backend.assessment.dto.GithubUser;

import java.util.List;
import java.util.Optional;

public interface GithubUserService {
    List<Optional<GithubUser>> getUsers(List<String> usernames);
}