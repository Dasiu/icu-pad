package com.icupad.test_results.default_test_type.domain;

import com.icupad.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * There's not guarantee that hl7 will send us same request date for each request in one test panel
 * (like blood gas for instance), even so normalization (in form of TestPanelResult) was introduced.
 * For safety - to avoid data lose - request date attribute is left.
 */
@Entity(name = "default_test_request")
public class TestRequest extends BaseEntity {
    private String hl7Id;
    private LocalDateTime requestDate;

    @ManyToOne
    @NotNull
    private Test test;

    @ManyToOne
    @NotNull
    private TestPanelResult testPanelResult;

    public void setTest(Test test) {
        this.test = test;
    }

    public Test getTest() {
        return test;
    }

    public void setHl7Id(String hl7Id) {
        this.hl7Id = hl7Id;
    }

    public String getHl7Id() {
        return hl7Id;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public TestPanelResult getTestPanelResult() {
        return testPanelResult;
    }

    public void setTestPanelResult(TestPanelResult testPanelResult) {
        this.testPanelResult = testPanelResult;
    }
}