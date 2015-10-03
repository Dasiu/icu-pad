package com.icupad.test_results.complete_blood_count.repository.impl;

import com.icupad.test_results.complete_blood_count.domain.TestResult;
import com.icupad.test_results.complete_blood_count.repository.BloodCountTestResultRepositoryCustom;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Collection;

import static com.icupad.patient.domain.QStay.stay;
import static com.icupad.test_results.complete_blood_count.domain.QTestPanelResult.testPanelResult;
import static com.icupad.test_results.complete_blood_count.domain.QTestRequest.testRequest;
import static com.icupad.test_results.complete_blood_count.domain.QTestResult.testResult;

@Repository("completeBloodCountTestResultRepository")
class BloodCountTestResultRepositoryImpl implements BloodCountTestResultRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    BloodCountTestResultRepositoryImpl(EntityManager entityManager) {
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
