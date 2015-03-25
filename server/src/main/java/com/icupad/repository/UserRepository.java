package com.icupad.repository;

import com.icupad.domain.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    User findByLogin(String login);
}
