package com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain;

import com.icupad.hl7_gateway.domain.Abnormality;
import com.icupad.hl7_gateway.domain.BaseEntity;
import com.icupad.hl7_gateway.domain.Stay;
import com.icupad.hl7_gateway.domain.TestResultExecutor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * There's not guarantee that hl7 will send us same unit and executor for each result in one test panel
 * (like blood gas for instance), even so normalization (in form of TestPanelResult) was introduced.
 * For safety - to avoid data lose - unit and executor attributes are left.
 */
@Entity(name = "complete_blood_count_test_result")
public class TestResult extends BaseEntity {
    private String hl7Id;
    private Double value;
    private String unit;
    private String norm;

    @Enumerated(EnumType.STRING)
    private Abnormality abnormality;

    @OneToOne
    private TestRequest testRequest;

    @ManyToOne
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

