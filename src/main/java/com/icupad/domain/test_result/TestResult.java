package com.icupad.domain.test_result;

import com.icupad.domain.BaseEntity;
import com.icupad.domain.Stay;
import com.icupad.domain.TestRequest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class TestResult extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Size(min = 1, max = 255)
    @NotNull
    private String hl7Id;

    @Column(nullable = false)
    @NotNull
    private Double value;

    @Column(nullable = false)
    @Size(min = 1, max = 255)
    @NotNull
    private String unit;

    @Size(max = 255)
    private String norm;

    @Enumerated(EnumType.STRING)
    private Abnormality abnormality;

    @JoinColumn(nullable = false)
    @OneToOne
    @NotNull
    private TestRequest testRequest;

    @JoinColumn(nullable = false)
    @ManyToOne
    @NotNull
    private Stay stay;

    private LocalDateTime resultDate;

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
