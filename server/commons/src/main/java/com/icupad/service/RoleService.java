package com.icupad.service;


import com.icupad.domain.Role;

public interface RoleService extends BaseService<Role> {
    String ADMIN_ROLE_NAME = "Admin";

    Role saveIfNotExists(Role role);

    Role findByName(String name);
}

