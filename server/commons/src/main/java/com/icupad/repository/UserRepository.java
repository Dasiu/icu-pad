package com.icupad.repository;

import com.icupad.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    User findByLogin(String login);
}
