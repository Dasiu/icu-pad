package com.icupad.default_test_type.domain;

import com.icupad.hl7_gateway.domain.BaseEntity;

import javax.persistence.Entity;

@Entity(name = "default_test")
public class Test extends BaseEntity {
    private String name;
    private String unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
