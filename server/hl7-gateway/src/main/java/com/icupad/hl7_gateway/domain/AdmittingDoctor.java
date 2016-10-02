package com.icupad.hl7_gateway.domain;

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

    @Size(min = 1, max = 30)
    @NotNull
    private String surname;

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

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setNpwz(String npwz) {
        this.npwz = npwz;
    }

    public String getNpwz() {
        return npwz;
    }
}
