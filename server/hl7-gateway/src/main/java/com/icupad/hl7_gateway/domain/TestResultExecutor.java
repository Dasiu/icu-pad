package com.icupad.hl7_gateway.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class TestResultExecutor {
    @Column(length = 6)
    @Size(min = 1, max = 6)
    private String executorHl7Id;

    @Column(length = 15)
    @Size(min = 1, max = 15)
    private String name;

    @Column(length = 30)
    @Size(min = 1, max = 30)
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getExecutorHl7Id() {
        return executorHl7Id;
    }

    public void setExecutorHl7Id(String executorHl7Id) {
        this.executorHl7Id = executorHl7Id;
    }
}
