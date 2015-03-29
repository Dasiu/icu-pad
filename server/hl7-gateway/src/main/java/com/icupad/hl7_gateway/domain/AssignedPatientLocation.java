package com.icupad.hl7_gateway.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class AssignedPatientLocation {
    @Size(min = 1, max = 255)
    private String name;

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignedPatientLocation that = (AssignedPatientLocation) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
