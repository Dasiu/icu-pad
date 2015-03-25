package com.icupad.test_data;

import com.icupad.domain.*;

import java.time.LocalDateTime;

public class Patients {
    public static Patient createAdamKowalski() {
        Patient patient = new Patient();

        patient.setHl7Id("123456789");
        patient.setPesel("69123001518");
        patient.setName("Adam");
        patient.setSurname("Kowalski");
        patient.setBirthDate(LocalDateTime.of(1991, 2, 10, 0, 0));
        patient.setSex(Sex.UNKNOWN);
        patient.setAddress(createAdamKowalskiAddress());

        return patient;
    }

    private static Address createAdamKowalskiAddress() {
        Address address = new Address();
        address.setStreet("Piłsudskiego");
        address.setStreetNumber("112a");
        address.setHouseNumber("2b");
        address.setPostalCode("64-500");
        address.setCity("Poznań");
        return address;
    }
}
