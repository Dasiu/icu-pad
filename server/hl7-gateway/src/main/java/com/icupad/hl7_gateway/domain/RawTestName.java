package com.icupad.hl7_gateway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Used to store raw (not parsed) test names and associations to parsed test (test group in consequence).
 * <p>
 * Needed to normalize hl7 data structures
 */
@Entity
public class RawTestName extends BaseEntity {
    @Column(nullable = false, unique = true, length = 2000)
    @Size(min = 1, max = 2000)
    @NotNull
    private String rawName;

    @ManyToOne
    private Test test;

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawTestName that = (RawTestName) o;
        return Objects.equals(rawName, that.rawName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawName);
    }
}
