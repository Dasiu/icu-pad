package com.icupad.service;

import com.icupad.domain.user.User;

public interface UserService extends BaseService<User> {
    User findByLogin(String login);
}
