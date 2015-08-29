package com.icupad.test_results.blood_gas.domain;

import com.icupad.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "blood_gas_test_request")
public class TestRequest extends BaseEntity {
    private String hl7Id;
    private LocalDateTime requestDate;

    @ManyToOne
    @NotNull
    private Test test;

    @ManyToOne
    @NotNull
    private TestPanelResult testPanelResult;

    public String getHl7Id() {
        return hl7Id;
    }

    public void setHl7Id(String hl7Id) {
        this.hl7Id = hl7Id;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public TestPanelResult getTestPanelResult() {
        return testPanelResult;
    }

    public void setTestPanelResult(TestPanelResult testPanelResult) {
        this.testPanelResult = testPanelResult;
    }
}

