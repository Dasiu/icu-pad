package com.icupad.user.config;

import com.icupad.domain.Role;
import com.icupad.domain.User;
import com.icupad.service.RoleService;
import com.icupad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn("productionUsersSeed")
@Profile("dev")
public class DevUsersSeed {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private User admin;

    @PostConstruct
    public void seed() {
        createUsers();
        setRoleForAdmin();
    }

    private void setRoleForAdmin() {
        Role adminRole = roleService.findByName(RoleService.ADMIN_ROLE_NAME);
        if (adminRole == null) {
            throw new RuntimeException("Missing admin role");
        }
        admin.getRoles().add(adminRole);
        userService.save(admin);
    }

    private void createUsers() {
        admin = createUser("admin", "admin");
    }

    private User createUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEnabled(true);
        return userService.saveIfNotExists(user);
    }
}