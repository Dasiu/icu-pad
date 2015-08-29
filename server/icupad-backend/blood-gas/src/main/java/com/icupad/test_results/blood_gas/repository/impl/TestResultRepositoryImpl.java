package com.icupad.test_results.blood_gas.repository.impl;

import com.icupad.test_results.blood_gas.domain.TestResult;
import com.icupad.test_results.blood_gas.repository.TestResultRepositoryCustom;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Collection;

import static com.icupad.patient.domain.QStay.stay;
import static com.icupad.test_results.blood_gas.domain.QTestPanelResult.testPanelResult;
import static com.icupad.test_results.blood_gas.domain.QTestRequest.testRequest;
import static com.icupad.test_results.blood_gas.domain.QTestResult.testResult;

@Repository
class TestResultRepositoryImpl implements TestResultRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    TestResultRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<TestResult> findBetweenRequestDatesForStay(long stayId, LocalDateTime startDate,
                                                                 LocalDateTime endDate) {
        JPAQuery query = new JPAQuery(entityManager);
        return query.from(testResult)
                .innerJoin(testResult.stay, stay)
                .innerJoin(testResult.testRequest, testRequest)
                .innerJoin(testRequest.testPanelResult, testPanelResult)
                .where(belongsToGivenStay(stayId).and(isBetweenDates(startDate, endDate)))
                .distinct()
                .list(testResult);
    }

    private BooleanExpression belongsToGivenStay(long stayId) {
        return stay.id.eq(stayId);
    }

    private BooleanExpression isBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return (startDate != null && endDate != null)
                ? testPanelResult.requestDate.between(startDate, endDate)
                : null;
    }
}
