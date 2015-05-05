package com.icupad.hl7_gateway.domain;

import com.icupad.hl7_gateway.repository.validation.constraints.Past;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class  TestRequest extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Size(min = 1, max = 255)
    @NotNull
    private String hl7Id;

    @Column(nullable = false)
    @Past
    @NotNull
    private LocalDateTime requestDate;

    @JoinColumn(nullable = false)
    @ManyToOne
    @NotNull
    private Test test;

    @Column(nullable = false)
    @Size(min = 1)
    @NotNull
    private String rawTestName;

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

    public void setRawTestName(String rawTestName) {
        this.rawTestName = rawTestName;
    }

    public String getRawTestName() {
        return rawTestName;
    }
}
