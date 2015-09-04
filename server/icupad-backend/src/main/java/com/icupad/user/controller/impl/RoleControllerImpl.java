package com.icupad.user.controller.impl;

import com.icupad.controller.AbstractBaseController;
import com.icupad.domain.Role;
import com.icupad.service.RoleService;
import com.icupad.user.controller.RoleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
class RoleControllerImpl extends AbstractBaseController<Role> implements RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleControllerImpl(RoleService roleService) {
        super(roleService);
        this.roleService = roleService;
    }
}