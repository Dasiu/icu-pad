package com.icupad.test_results.complete_blood_count.domain;

import com.icupad.domain.BaseEntity;
import com.icupad.patient.domain.Stay;
import com.icupad.test_results.common.domain.Abnormality;
import com.icupad.test_results.common.domain.TestResultExecutor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "complete_blood_count_test_result")
public class TestResult extends BaseEntity {
    private String hl7Id;
    private Double value;
    private String unit;
    private String norm;

    @Enumerated(EnumType.STRING)
    private Abnormality abnormality;

    @OneToOne
    @NotNull
    private TestRequest testRequest;

    @ManyToOne
    @NotNull
    private Stay stay;

    private LocalDateTime resultDate;

    @Embedded
    private TestResultExecutor executor;

    public String getHl7Id() {
        return hl7Id;
    }

    public void setHl7Id(String hl7Id) {
        this.hl7Id = hl7Id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    public Abnormality getAbnormality() {
        return abnormality;
    }

    public void setAbnormality(Abnormality abnormality) {
        this.abnormality = abnormality;
    }

    public LocalDateTime getResultDate() {
        return resultDate;
    }

    public void setResultDate(LocalDateTime resultDate) {
        this.resultDate = resultDate;
    }

    public TestResultExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(TestResultExecutor executor) {
        this.executor = executor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public TestRequest getTestRequest() {
        return testRequest;
    }

    public void setTestRequest(TestRequest testRequest) {
        this.testRequest = testRequest;
    }

    public Stay getStay() {
        return stay;
    }

    public void setStay(Stay stay) {
        this.stay = stay;
    }
}

