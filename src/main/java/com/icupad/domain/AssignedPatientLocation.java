package com.icupad.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class AssignedPatientLocation {
    @Size(min = 1, max = 255)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
