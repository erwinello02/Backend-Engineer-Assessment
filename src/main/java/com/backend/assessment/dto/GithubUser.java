package com.backend.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubUser {
    private String name;
    private String login;
    private String company;
    private int followers;
    private int public_repos;
    private double average_number_of_followers_per_public_repo;
}
