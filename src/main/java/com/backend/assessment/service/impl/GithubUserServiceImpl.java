package com.backend.assessment.service.impl;

import com.backend.assessment.dto.GithubUser;
import com.backend.assessment.service.GithubClientService;
import com.backend.assessment.service.GithubUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GithubUserServiceImpl implements GithubUserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private GithubClientService githubClientService;

    public List<Optional<GithubUser>> getUsers(List<String> usernames) {
        if(usernames.size() > 10){
            throw new IllegalArgumentException("Maximum of 10 usernames can be input!");
        }
        List<Optional<GithubUser>> githubUserList = new ArrayList<>();
        for (String username : usernames) {
            Cache cache = cacheManager.getCache("githubUsers");
            Optional<GithubUser> cachedUserOpt = Optional.ofNullable(cache.get(username, GithubUser.class));
            if (cachedUserOpt.isPresent()) {
                githubUserList.add(cachedUserOpt);
            } else {
                Optional<GithubUser> fetchedUser = githubClientService.fetchUser(username);
                fetchedUser.ifPresent(user -> {
                    if (user.getPublic_repos() != 0) {
                        double average = (double) user.getFollowers() / user.getPublic_repos();
                        user.setAverage_number_of_followers_per_public_repo(average);
                    } else {
                        user.setAverage_number_of_followers_per_public_repo(0);
                    }
                    if (cache != null) {
                        cache.put(username, user);
                    }
                });
                githubUserList.add(fetchedUser);
            }
        }
        return githubUserList;
    }
}
