package com.icupad.domain;

import com.icupad.repository.validation.constraints.Past;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Patient extends BaseEntity {
    @Size(min = 1, max = 255)
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

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public String getHl7Id() {
        return hl7Id;
    }

    public void setHl7Id(String hl7Id) {
        this.hl7Id = hl7Id;
    }
}
