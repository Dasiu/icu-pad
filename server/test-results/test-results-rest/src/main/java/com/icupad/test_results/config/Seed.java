package com.icupad.test_results.config;

import com.icupad.domain.User;
import com.icupad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("dev")
public class Seed {
    @Autowired
    private UserService userService;

    @Autowired
    public Seed(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void seed() {
        createUsers();
    }

    private void createUsers() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        user.setEnabled(true);
        userService.save(user);
    }
}