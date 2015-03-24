package com.icupad.domain;

import com.icupad.repository.validation.constraints.Past;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class TestRequest extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Size(min = 1, max = 255)
    @NotNull
    private String hl7Id;

    @Column(nullable = false)
    @Past
    @NotNull
    private LocalDateTime requestDate;

    @ManyToOne
    private Test test;

    public void setHl7Id(String hl7Id) {
        this.hl7Id = hl7Id;
    }

    public String getHl7Id() {
        return hl7Id;
    }


    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
