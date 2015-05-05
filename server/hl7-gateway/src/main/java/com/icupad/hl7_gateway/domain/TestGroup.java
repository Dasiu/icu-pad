package com.icupad.hl7_gateway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class TestGroup extends BaseEntity {
    @Column(nullable = false)
    @Size(min = 1)
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
