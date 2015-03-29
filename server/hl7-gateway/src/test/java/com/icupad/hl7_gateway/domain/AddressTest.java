package com.icupad.hl7_gateway.domain;

import org.junit.Test;

import static com.icupad.test_utils.ValidationUtils.*;
import static org.junit.Assert.assertThat;

public class AddressTest {
    @Test
    public void streetShouldBeValid() {
        Address address = new Address();
        address.setStreet("28 Czerwca");

        assertThat(validationFor(address, onProperty("street")), succeeds());
    }

    @Test
    public void streetShouldNotBeLongerThan30() {
        Address address = new Address();
        address.setStreet("aaaabbbbaaaabbbbaaaabbbbaaaabbb");

        assertThat(validationFor(address, onProperty("street")), fails());
    }

    @Test
    public void streetNumberShouldBeValid() {
        Address address = new Address();
        address.setStreetNumber("12a");

        assertThat(validationFor(address, onProperty("streetNumber")), succeeds());
    }

    @Test
    public void streetNumberShouldNotBeLongerThan8() {
        Address address = new Address();
        address.setStreetNumber("aaaabbbba");

        assertThat(validationFor(address, onProperty("streetNumber")), fails());
    }

    @Test
    public void houseNumberShouldBeValid() {
        Address address = new Address();
        address.setHouseNumber("7b");

        assertThat(validationFor(address, onProperty("houseNumber")), succeeds());
    }

    @Test
    public void houseNumberShouldNotBeLongerThan4() {
        Address address = new Address();
        address.setHouseNumber("aaaabbbba");

        assertThat(validationFor(address, onProperty("houseNumber")), fails());
    }

    @Test
    public void cityShouldBeValid() {
        Address address = new Address();
        address.setCity("Oborniki");

        assertThat(validationFor(address, onProperty("city")), succeeds());
    }

    @Test
    public void cityShouldNotBeLongerThan50() {
        Address address = new Address();
        address.setCity("aaaaabbbbbaaaaabbbbbaaaaabbbbbaaaaabbbbbaaaaabbbbba");

        assertThat(validationFor(address, onProperty("city")), fails());
    }

    @Test
    public void postalCodeShouldBeValid() {
        Address address = new Address();
        address.setPostalCode("64-200");

        assertThat(validationFor(address, onProperty("postalCode")), succeeds());
    }

    @Test
    public void postalCodeShouldNotHasNonstandardPattern() {
        Address address = new Address();
        address.setPostalCode("123-45");

        assertThat(validationFor(address, onProperty("postalCode")), fails());
    }
}