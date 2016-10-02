package com.icupad.hl7_gateway.core.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class AdmittingDoctor {
    @Column(name = "admitting_doctor_hl7Id")
    @Size(min = 1, max = 6)
    @NotNull
    private String hl7Id;

    @Column(name = "admitting_doctor_name")
    @Size(min = 1, max = 15)
    @NotNull
    private String name;

    @Size(min = 20, max = 100)
    @NotNull
    private String lastname;

    /**
     * numer prawa wykonywania zawodu
     */
    @Size(min = 1, max = 30)
    @NotNull
    private String npwz;

    public void setHl7Id(String hl7Id) {
        this.hl7Id = hl7Id;
    }

    public String getHl7Id() {
        return hl7Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setNpwz(String npwz) {
        this.npwz = npwz;
    }

    public String getNpwz() {
        return npwz;
    }
}
