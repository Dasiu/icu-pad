package com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain;

import com.icupad.hl7_gateway.core.domain.BaseEntity;
import com.icupad.hl7_gateway.core.domain.TestResultExecutor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity(name = "complete_blood_count_test_panel_result")
public class TestPanelResult extends BaseEntity {
    private LocalDateTime requestDate;

    @Embedded
    private TestResultExecutor executor;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private BloodSource bloodSource;

    @OneToMany(mappedBy = "testPanelResult")
    private List<TestRequest> testRequests = new ArrayList<>();

    /**
     * Used only to make handler implementation easier
     */
    private transient List<TestResult> testResults = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPanelResult that = (TestPanelResult) o;
        return Objects.equals(requestDate, that.requestDate) &&
                Objects.equals(executor, that.executor) &&
                Objects.equals(bloodSource, that.bloodSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDate, executor, bloodSource);
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public TestResultExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(TestResultExecutor executor) {
        this.executor = executor;
    }

    public BloodSource getBloodSource() {
        return bloodSource;
    }

    public void setBloodSource(BloodSource bloodSource) {
        this.bloodSource = bloodSource;
    }

    public void setTestRequests(List<TestRequest> testRequests) {
        this.testRequests = testRequests;
    }

    public List<TestRequest> getTestRequests() {
        return testRequests;
    }

    public void addTestRequests(Collection<TestRequest> requests) {
        testRequests.addAll(requests);
    }

    public List<TestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
    }

    public void addTestResults(Collection<TestResult> results) {
        testResults.addAll(results);
    }
}
