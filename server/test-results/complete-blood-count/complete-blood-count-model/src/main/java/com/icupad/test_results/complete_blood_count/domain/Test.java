package com.icupad.test_results.complete_blood_count.domain;

import com.icupad.domain.BaseEntity;

import javax.persistence.Entity;

@Entity(name = "complete_blood_count_test")
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
