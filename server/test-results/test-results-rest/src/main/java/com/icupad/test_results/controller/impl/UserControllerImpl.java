package com.icupad.test_results.controller.impl;

import com.icupad.controller.AbstractBaseController;
import com.icupad.domain.User;
import com.icupad.service.UserService;
import com.icupad.test_results.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserControllerImpl extends AbstractBaseController<User> implements UserController {
    private UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        super(userService);

        this.userService = userService;
    }
}