package com.icupad.hl7_gateway.core.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {
    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}


