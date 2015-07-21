package com.icupad.hl7_gateway.test_type_module.blood_gas.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum BloodSource {
    VEIN(Collections.singletonList("krew żylna")),
    ARTERY(Collections.singletonList("krew tętnicza")),
    CAPILLARY(Arrays.asList("krew włośniczkowa", "krew włośń.")),
    UNKNOWN(Collections.singletonList("nieznane"));

    private final List<String> hl7StringValues;

    BloodSource(List<String> hl7StringValues) {
        this.hl7StringValues = hl7StringValues;
    }

    public static BloodSource parse(String bloodSourceStr) {
        return Arrays.asList(BloodSource.values()).stream().
                filter(bloodSource -> bloodSourcesThatContainsGivenString(bloodSource, bloodSourceStr))
                .findFirst().orElse(UNKNOWN);
    }

    private static boolean bloodSourcesThatContainsGivenString(BloodSource bloodSource, String bloodSourceStr) {
        return bloodSource.hl7StringValues.stream()
                .anyMatch(str -> str.equals(bloodSourceStr));
    }
}
