package com.backend.assessment.service.impl;

import com.backend.assessment.dto.GithubUser;
import com.backend.assessment.service.GithubClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Optional;

@Service
public class GithubClientServiceImpl implements GithubClientService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RestTemplate restTemplate;

    public Optional<GithubUser> fetchUser(String username){
        String url = "https://api.github.com/users/" + username;
        try {
            Instant fetchGithubUsersStart = Instant.now();
            ResponseEntity<GithubUser> response = restTemplate.getForEntity(url, GithubUser.class);
            logger.info("Fetch github user start: "+fetchGithubUsersStart);
            return Optional.of(response.getBody());
        } catch (HttpClientErrorException.NotFound e) {
            logger.error(e.getMessage());
            return Optional.empty(); // User not found
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty(); // Log for diagnostics if needed
        }

    }
}