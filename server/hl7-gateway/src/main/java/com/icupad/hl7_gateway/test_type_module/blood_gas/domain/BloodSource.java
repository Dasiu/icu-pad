package com.icupad.hl7_gateway.test_type_module.blood_gas.domain;

import java.util.Arrays;

public enum BloodSource {
    VEIN("krew żylna"),
    ARTERY("krew tętnicza"),
    CAPILLARY("krew włośniczkowa"),
    UNKNOWN("nieznane");

    private final String hl7StringValue;

    BloodSource(String hl7StringValue) {
        this.hl7StringValue = hl7StringValue;
    }

    public static BloodSource parse(String bloodSourceStr) {
        return Arrays.asList(BloodSource.values()).stream().
                filter(bloodSource -> bloodSource.hl7StringValue.equals(bloodSourceStr))
                .findFirst().orElse(UNKNOWN);
    }
}
