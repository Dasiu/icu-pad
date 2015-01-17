package com.icupad.domain;

import com.icupad.repository.validation.constraints.Past;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Patient extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Size(min = 1, max = 255)
    @NotNull
    private String hl7Id;

    @Column(length = 11)
    @Size(min = 11, max = 11)
    @Pattern(regexp = "[0-9]+")
    private String pesel;

    @Column(length = 15, nullable = false)
    @Size(min = 1, max = 15)
    @NotNull
    private String name;

    @Column(length = 30, nullable = false)
    @Size(min = 1, max = 30)
    @NotNull
    private String surname;

    @Column(nullable = false)
    @Past
    @NotNull
    private LocalDateTime birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Sex sex;

    @Embedded
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (address != null ? !address.equals(patient.address) : patient.address != null) return false;
        if (birthDate != null ? !birthDate.equals(patient.birthDate) : patient.birthDate != null) return false;
        if (name != null ? !name.equals(patient.name) : patient.name != null) return false;
        if (pesel != null ? !pesel.equals(patient.pesel) : patient.pesel != null) return false;
        if (sex != patient.sex) return false;
        if (surname != null ? !surname.equals(patient.surname) : patient.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pesel != null ? pesel.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getHl7Id() {
        return hl7Id;
    }

    public void setHl7Id(String hl7Id) {
        this.hl7Id = hl7Id;
    }
}
