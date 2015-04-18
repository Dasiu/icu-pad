package com.icupad.nurse.model.external;

import com.icupad.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Patient extends BaseEntity {
	
    @Column(nullable = false, unique = true)
    private String hl7Id;

    @Column
    private String pesel;

    @Column(length = 15, nullable = false)
    private String name;

    @Column(length = 30)
    private String surname;

    @Override
    public String toString() {
        return "Patient{" +
                "hl7Id='" + hl7Id + '\'' +
                ", pesel='" + pesel + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
    
    public void setId(Long id) {
    	this.id = id;
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

    public String getHl7Id() {
        return hl7Id;
    }

    public void setHl7Id(String hl7Id) {
        this.hl7Id = hl7Id;
    }
}
