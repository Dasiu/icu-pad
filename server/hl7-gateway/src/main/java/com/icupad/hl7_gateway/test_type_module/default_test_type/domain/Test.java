package com.icupad.hl7_gateway.test_type_module.default_test_type.domain;

import com.icupad.hl7_gateway.core.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "default_test")
@Table(name = "default_test", uniqueConstraints = {
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
