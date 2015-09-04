package com.icupad.service.impl;

import com.icupad.domain.Role;
import com.icupad.domain.User;
import com.icupad.exception.*;
import com.icupad.repository.UserRepository;
import com.icupad.service.AbstractBaseService;
import com.icupad.service.RoleService;
import com.icupad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
class UserServiceImpl extends AbstractBaseService<User> implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder encoder,
                           RoleService roleService) {
        super(userRepository);

        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public <S extends User> S save(S entity) {
        if (entity.getId() == null) {
            doNewUserOperations(entity);
        }
        return userRepository.save(entity);
    }

    private <S extends User> void doNewUserOperations(S entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        entity.setEnabled(true);
    }

    @Override
    @Transactional
    public <S extends User> List<S> save(Iterable<S> entities) {
        return userRepository.save(entities);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User saveIfNotExists(User user) {
        User userInDB = findByLogin(user.getLogin());
        return userInDB != null ? userInDB : save(user);
    }

    @Override
    public User update(User updateAttributes) {
        validate(updateAttributes);
        User userInDB = getOne(updateAttributes.getId());
        update(userInDB, updateAttributes);
        return userRepository.save(userInDB);
    }

    private void update(User userInDB, User updateAttributes) {
        updateEnabled(userInDB, updateAttributes);
        updateRoles(userInDB, updateAttributes);
    }

    private void updateEnabled(User userInDB, User updateAttributes) {
        Boolean enabled = updateAttributes.isEnabled();
        if (enabled != null) {
            userInDB.setEnabled(enabled);
        }
    }

    private void updateRoles(User userInDB, User updateAttributes) {
        Set<Role> roles = updateAttributes.getRoles();
        if (roles != null) {
            validate(roles);
            userInDB.getRoles().addAll(roles.stream()
                    .map(Role::getId)
                    .map(roleService::getOne)
                    .collect(Collectors.toList()));
        }
    }

    private void validate(Set<Role> roles) {
        checkAgainstNullIds(roles);
        checkAgainstMissingRoles(roles);
    }

    private void checkAgainstMissingRoles(Set<Role> roles) {
        if (roles.stream()
                .map(Role::getId)
                .map(roleService::getOne)
                .anyMatch(role -> role == null)) {
            throw new RoleWithGivenIdNotExist();
        }
    }

    private void checkAgainstNullIds(Set<Role> roles) {
        if (roles.stream()
                .anyMatch(role -> role.getId() == null)) {
            throw new MissingRoleId();
        }
    }

    private void validate(User user) {
        Long id = user.getId();
        if (id == null || getOne(id) == null) {
            throw new CanNotUpdateUserOneNotExists();
        }
        if (user.getPassword() != null) {
            throw new CanNotChangePassword("Use specific service operation for that");
        }
        if (user.getLogin() != null) {
            throw new CanNotChangeLogin();
        }
    }
}
