package com.icupad.patient.repository.impl;

import com.icupad.patient.domain.Stay;
import com.icupad.patient.repository.StayRepositoryCustom;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.icupad.patient.domain.QPatient.patient;
import static com.icupad.patient.domain.QStay.stay;

@Repository
class StayRepositoryImpl implements StayRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    StayRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<Stay> findAllActive() {
        JPAQuery query = new JPAQuery(entityManager);
        return query.from(stay)
                .innerJoin(stay.patient, patient)
                .where(stayIsActive())
                .list(stay);
    }

    private BooleanExpression stayIsActive() {
        return stay.active.eq(true);
    }
}
