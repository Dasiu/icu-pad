package com.icupad.repository;

import com.icupad.domain.User;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class BaseRepositoryImpl<T, ID extends Serializable>
        extends QueryDslJpaRepository<T, ID>
        implements BaseRepository<T, ID> {
    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public List<T> findByCreatedBy(User user) {
        throw new UnsupportedOperationException();
    }
}


