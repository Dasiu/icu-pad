package com.icupad.user.controller.impl;

import com.icupad.controller.AbstractBaseController;
import com.icupad.domain.User;
import com.icupad.service.UserService;
import com.icupad.user.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
class UserControllerImpl extends AbstractBaseController<User> implements UserController {
    private UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        super(userService);

        this.userService = userService;
    }

    @Override
    public void update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.update(user);
    }
}