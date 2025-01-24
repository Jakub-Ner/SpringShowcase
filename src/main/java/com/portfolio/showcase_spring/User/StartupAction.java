package com.portfolio.showcase_spring.User;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupAction {
    private final Logger logger = LoggerFactory.getLogger(StartupAction.class);

    @Autowired
    private UserConfiguration userConfiguration;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void seedUsersOnStartup() {
        if (userService.findById(1L) != null) return;

        logger.info("Seeding users on startup from {}", userConfiguration.getUsersApiUrl());

        List<UserEntity> users = userService.fetchUsersFrom3rdParty(userConfiguration.getUsersApiUrl());
        try{
            users.forEach(user -> userService.save(user));
        } catch (Exception e) {
            logger.warn("Race condition occurred during seeding users", e);
        }
    }
}
