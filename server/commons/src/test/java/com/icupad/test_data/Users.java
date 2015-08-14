package com.icupad.test_data;

import com.icupad.domain.User;

public abstract class Users {
    public static User admin() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        user.setEnabled(true);
        return user;
    }
}
