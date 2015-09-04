package com.icupad.repository;

import com.icupad.domain.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {
    Role findByName(String name);
}
