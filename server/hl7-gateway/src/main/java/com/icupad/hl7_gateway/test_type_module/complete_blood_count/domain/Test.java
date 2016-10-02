package com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain;

import com.icupad.hl7_gateway.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "complete_blood_count_test")
@Table(name = "complete_blood_count_test", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "unit"}),
})
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
