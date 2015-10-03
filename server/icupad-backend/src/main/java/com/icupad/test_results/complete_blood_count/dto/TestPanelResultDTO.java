package com.icupad.test_results.complete_blood_count.dto;

import com.icupad.test_results.common.domain.TestResultExecutor;
import com.icupad.test_results.complete_blood_count.domain.BloodSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class TestPanelResultDTO {
    private LocalDateTime requestDate;
    private TestResultExecutor executor;
    private BloodSource bloodSource;
    private Collection<TestDTO> tests = new ArrayList<>();
    private Long id;

    public Collection<TestDTO> getTests() {
        return tests;
    }

    public void setTests(Collection<TestDTO> tests) {
        this.tests = tests;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
