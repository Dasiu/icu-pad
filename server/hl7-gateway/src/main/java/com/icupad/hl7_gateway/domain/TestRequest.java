package com.icupad.hl7_gateway.domain;

import com.icupad.hl7_gateway.core.repository.validation.constraints.Past;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class TestRequest {
    @Size(min = 1, max = 255)
    @NotNull
    private String hl7Id;

    @Past
    @NotNull
    private LocalDateTime requestDate;

    @Size(min = 1, max = 2000)
    @NotNull
    private String rawTestName;

    @NotNull
    private TestMapping testMapping;

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

    public String getRawTestName() {
        return rawTestName;
    }

    public void setRawTestName(String rawTestName) {
        this.rawTestName = rawTestName;
    }

    public TestMapping getTestMapping() {
        return testMapping;
    }

    public void setTestMapping(TestMapping testMapping) {
        this.testMapping = testMapping;
    }
}
