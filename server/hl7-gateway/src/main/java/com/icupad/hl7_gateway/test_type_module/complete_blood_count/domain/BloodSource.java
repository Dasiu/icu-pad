package com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain;

import com.icupad.hl7_gateway.test_type_module.complete_blood_count.service.UnknownBloodSourceException;

import java.util.Arrays;

public enum BloodSource {
    VEIN("krew żylna"),
    ARTERY("krew tętnicza"),
    CAPILLARY("krew włośniczkowa");

    private final String hl7StringValue;

    BloodSource(String hl7StringValue) {
        this.hl7StringValue = hl7StringValue;
    }

    public static BloodSource parse(String bloodSourceStr) {
        return Arrays.asList(BloodSource.values()).stream().
                filter(bloodSource -> bloodSource.hl7StringValue.equals(bloodSourceStr))
                .findFirst().orElseThrow(UnknownBloodSourceException::new);
    }
}
