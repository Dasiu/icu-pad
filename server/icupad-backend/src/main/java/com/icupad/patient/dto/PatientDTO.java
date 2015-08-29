package com.icupad.patient.dto;

import com.icupad.patient.domain.Address;
import com.icupad.patient.domain.Sex;

import java.util.ArrayList;
import java.util.List;

public class PatientDTO {
    private long id;
    private String hl7Id;
    private String pesel;
    private String name;
    private String surname;
    private Sex sex;
    private Address address;
    private List<StayDTO> stays = new ArrayList<>();
    private String birthDate;

    public StayDTO getActiveStay() {
        return stays.stream()
                .filter(StayDTO::isActive)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Missing active stay"));
    }

    public String getHl7Id() {
        return hl7Id;
    }

    public void setHl7Id(String hl7Id) {
        this.hl7Id = hl7Id;
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

    public List<StayDTO> getStays() {
        return stays;
    }

    public void setStays(List<StayDTO> stays) {
        this.stays = stays;
    }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
