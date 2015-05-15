package com.icupad.hl7_gateway.core.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class RepositoryFactoryBean<R extends SimpleJpaRepository
        <T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {

    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new RepositoryFactory(entityManager);
    }

    private static class RepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {
        private EntityManager entityManager;

        public RepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

        @SuppressWarnings("unchecked")
        protected Object getTargetRepository(RepositoryMetadata metadata) {
            JpaEntityInformation<T, I> entityInformation =
                    (JpaEntityInformation<T, I>) getEntityInformation(metadata.getDomainType());
            return new BaseRepositoryImpl<T, I>(entityInformation, entityManager) {
            };
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
            //to check for QueryDslJpaRepository's which is out of scope.
            return BaseRepository.class;
        }
    }
}


