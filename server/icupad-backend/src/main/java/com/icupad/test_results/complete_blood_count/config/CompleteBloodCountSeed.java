package com.icupad.test_results.complete_blood_count.config;

import com.icupad.domain.User;
import com.icupad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("dev")
public class CompleteBloodCountSeed {
    @Autowired
    private UserService userService;

    @Autowired
    public CompleteBloodCountSeed(UserService userService) {
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
        if (userService.findByLogin(user.getLogin()) == null) userService.save(user);
    }
}