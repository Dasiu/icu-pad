package com.icupad.hl7_gateway.core.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestResultExecutor that = (TestResultExecutor) o;
        return Objects.equals(executorHl7Id, that.executorHl7Id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(executorHl7Id, name, surname);
    }

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
