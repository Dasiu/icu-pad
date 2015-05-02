package com.icupad.service;


import com.icupad.domain.User;

public interface UserService extends BaseService<User> {
    User findByLogin(String login);
}

