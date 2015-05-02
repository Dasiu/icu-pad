package com.icupad.service;

import com.icupad.domain.User;
import com.icupad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractBaseService<User> implements UserService {
    private UserRepository userRepository;

    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        super(userRepository);

        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public <S extends User> S save(S entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    @Transactional
    public <S extends User> List<S> save(Iterable<S> entities) {
        entities.forEach(e -> e.setPassword(encoder.encode(e.getPassword())));
        return userRepository.save(entities);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
