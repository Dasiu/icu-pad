package com.icupad.user.config;

import com.icupad.domain.Right;
import com.icupad.domain.Role;
import com.icupad.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductionUsersSeed {
    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void seed() {
        createRoles();
    }

    private void createRoles() {
        roleService.saveIfNotExists(createRole("Doctor", Arrays.asList(Right.TEST_RESULTS_READ)));
        roleService.saveIfNotExists(createRole("Nurse", Arrays.asList(Right.TEST_RESULTS_READ)));
        roleService.saveIfNotExists(createRole(RoleService.ADMIN_ROLE_NAME, Arrays.asList(Right.TEST_RESULTS_READ)));
    }

    private Role createRole(String name, List<Right> rights) {
        Role role = new Role();
        role.setName(name);
        role.addRights(rights);
        return role;
    }
}