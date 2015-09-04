package com.icupad.service.impl;

import com.icupad.domain.Role;
import com.icupad.repository.RoleRepository;
import com.icupad.service.AbstractBaseService;
import com.icupad.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class RoleServiceImpl extends AbstractBaseService<Role> implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveIfNotExists(Role role) {
        Role roleInDB = roleRepository.findByName(role.getName());
        return roleInDB != null ? roleInDB : save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
