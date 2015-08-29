package com.icupad.test_data;

import com.icupad.patient.domain.Stay;
import com.icupad.patient.domain.StayType;

import java.time.LocalDateTime;

public abstract class Stays {
    public static Stay stay() {
        Stay stay = new Stay();
        stay.setHl7Id("XXX");
        stay.setType(StayType.INPATIENT);
        stay.setAdmitDate(LocalDateTime.now());
        return stay;
    }
}
