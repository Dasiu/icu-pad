package com.icupad.hl7_gateway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Test extends BaseEntity {
    @Column(nullable = false, unique = true, length = 2000)
    @Size(min = 1, max = 2000)
    @NotNull
    private String name;

    @JoinColumn(nullable = false)
    @ManyToOne
    @NotNull
    private TestGroup testGroup;

    @Column(nullable = false)
    @Size(min = 1, max = 255)
    @NotNull
    private String unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestGroup getTestGroup() {
        return testGroup;
    }

    public void setTestGroup(TestGroup testGroup) {
        this.testGroup = testGroup;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
