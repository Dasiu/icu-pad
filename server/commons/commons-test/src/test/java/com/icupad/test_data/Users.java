package com.icupad.test_data;

import com.icupad.domain.User;
import com.icupad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("test")
public class Users {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void createUsers() {
        userService.save(admin());
    }

    @Bean
    public User admin() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        user.setEnabled(true);
        return user;
    }
}
