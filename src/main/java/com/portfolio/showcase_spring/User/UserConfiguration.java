package com.portfolio.showcase_spring.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Value("${users.api.url}")
    private String USERS_API_URL;

    public String getUsersApiUrl() {
        return USERS_API_URL;
    }

}
