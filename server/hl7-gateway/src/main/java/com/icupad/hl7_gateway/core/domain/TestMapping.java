package com.icupad.hl7_gateway.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Used to store mapping information between raw test name, which is send by hl7 gateway and proper test name, its unit
 * and belonging to test type.
 * <p>
 * These information are needed to normalize hl7 data structures to our domain models
 *
 * For example raw test name can be 'Gazometria (krew włośniczkowa) - pCO2'. In that case 'Gazometria' is a test type,
 * 'krew włośniczkowa' is a blood source and pCO2 is a proper test name.
 */
@Entity
public class TestMapping extends BaseEntity {
    @Column(nullable = false, unique = true, length = 2000)
    @Size(min = 1, max = 2000)
    @NotNull
    private String rawTestName;

    private String unit;
    private String testName;

    @ManyToOne
    private TestType testType;

    public TestMapping() {
        // left for libs
    }

    public TestMapping(String testName, String rawTestName, String unit) {
        this.testName = testName;
        this.rawTestName = rawTestName;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestMapping that = (TestMapping) o;
        return Objects.equals(rawTestName, that.rawTestName) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(testName, that.testName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawTestName, unit, testName);
    }

    @Override
    public String toString() {
        return "TestMapping{" +
                "rawTestName='" + rawTestName + '\'' +
                ", unit='" + unit + '\'' +
                ", testName='" + testName + '\'' +
                '}';
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    public String getRawTestName() {
        return rawTestName;
    }

    public void setRawTestName(String rawTestName) {
        this.rawTestName = rawTestName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
