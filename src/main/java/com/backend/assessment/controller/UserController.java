package com.backend.assessment.controller;

import com.backend.assessment.dto.GithubUser;
import com.backend.assessment.service.GithubUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GithubUserService githubUserService;

    @GetMapping("/github-users")
    public ResponseEntity<List<Optional<GithubUser>>> getUsers(@RequestBody List<String> usernames){
        Instant getGithubUsersStart = Instant.now();
        List<Optional<GithubUser>> foundUsers = githubUserService.getUsers(usernames);
        logger.info("Get github users start: "+getGithubUsersStart);
        return ResponseEntity.ok(foundUsers);
    }
}
