package com.portfolio.showcase_spring.User;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public List<UserEntity> fetchUsersFrom3rdParty(String url) {
        RestTemplate restTemplate = new RestTemplate();
        UserEntity[] users = restTemplate.getForObject(url, UserEntity[].class);
        if (users == null) return new ArrayList<>();
        return Arrays.asList(users);
    }

    public void save(UserEntity user) {
        user.setId(null);
        userRepo.save(user);
    }

    public Iterable<UserEntity> findAll() {
        return userRepo.findAll();
    }
}
