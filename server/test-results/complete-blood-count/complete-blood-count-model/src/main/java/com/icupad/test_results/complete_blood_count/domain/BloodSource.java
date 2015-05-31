package com.icupad.test_results.complete_blood_count.domain;

public enum BloodSource {
    VEIN("krew żylna"),
    ARTERY("krew tętnicza"),
    CAPILLARY("krew włośniczkowa");

    private final String hl7StringValue;

    BloodSource(String hl7StringValue) {
        this.hl7StringValue = hl7StringValue;
    }
}
