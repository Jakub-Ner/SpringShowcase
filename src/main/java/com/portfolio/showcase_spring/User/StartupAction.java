package com.portfolio.showcase_spring.User;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupAction {
    private static final String USERS_API_URL = "https://jsonplaceholder.typicode.com/users";

    @Autowired
    private UserService userService;

    @PostConstruct
    public void seedUsersOnStartup() {
        List<UserEntity> users = userService.fetchUsersFrom3rdParty(USERS_API_URL);
        users.forEach(user -> userService.save(user));
    }
}
