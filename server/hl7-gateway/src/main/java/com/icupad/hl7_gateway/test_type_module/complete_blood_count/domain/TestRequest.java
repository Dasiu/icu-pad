package com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain;

import com.icupad.hl7_gateway.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * There's not guarantee that hl7 will send us same request date for each request in one test panel
 * (like blood gas for instance), even so normalization (in form of TestPanelResult) was introduced.
 * For safety - to avoid data lose - request date attribute is left.
 */
@Entity(name = "complete_blood_count_test_request")
public class TestRequest extends BaseEntity {
    private String hl7Id;
    private LocalDateTime requestDate;

    @ManyToOne
    @NotNull
    private Test test;

    @ManyToOne
    @NotNull
    private TestPanelResult testPanelResult;

    /**
     * Used only to make handler implementation easier
     */
    private transient String rawTestName;

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

    public String getRawTestName() {
        return rawTestName;
    }

    public void setRawTestName(String rawTestName) {
        this.rawTestName = rawTestName;
    }

    public TestPanelResult getTestPanelResult() {
        return testPanelResult;
    }

    public void setTestPanelResult(TestPanelResult testPanelResult) {
        this.testPanelResult = testPanelResult;
    }
}

