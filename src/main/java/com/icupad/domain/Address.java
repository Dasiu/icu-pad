package com.icupad.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
    @Column(length = 30)
    @Size(min = 1, max = 30)
    private String street;

    @Column(length = 8)
    @Size(min = 1, max = 8)
    private String streetNumber;

    @Column(length = 4)
    @Size(min = 1, max = 4)
    private String houseNumber;

    @Column(length = 6)
    @Pattern(regexp = "[0-9][0-9]-[0-9][0-9][0-9]")
    private String postalCode;

    @Column(length = 50)
    @Size(min = 1, max = 50)
    private String city;

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
