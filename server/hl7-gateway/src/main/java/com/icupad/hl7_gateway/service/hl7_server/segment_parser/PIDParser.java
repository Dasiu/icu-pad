package com.icupad.hl7_gateway.service.hl7_server.segment_parser;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v23.datatype.TSComponentOne;
import ca.uhn.hl7v2.model.v23.datatype.XAD;
import ca.uhn.hl7v2.model.v23.segment.PID;
import com.icupad.hl7_gateway.domain.Address;
import com.icupad.hl7_gateway.domain.Patient;
import com.icupad.hl7_gateway.domain.Sex;
import com.icupad.hl7_gateway.service.hl7_server.MissingSexException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PIDParser implements Parser<PID, Patient> {
    private static final String peselNullValue = "00000000000";

    @Override
    public Patient parse(PID pid) throws DataTypeException {
        Patient patient = new Patient();

        patient.setHl7Id(getHl7Id(pid));
        patient.setPesel(getPesel(pid));
        patient.setName(getName(pid));
        patient.setSurname(getSurname(pid));
        patient.setBirthDate(getDateOfBirth(pid));
        patient.setSex(getSex(pid));
        patient.setAddress(getAddress(pid));

        return patient;
    }

    private Address getAddress(PID pid) {
        Address address = new Address();

        XAD xad = pid.getPatientAddress()[0];
        address.setStreet(getStreet(xad));
        address.setStreetNumber(getStreetNumber(xad));
        address.setHouseNumber(getHouseNumber(xad));
        address.setCity(getCity(xad));
        address.setPostalCode(getPostalCode(xad));

        return address;
    }

    private String getHouseNumber(XAD xad) {
        Pattern findHouseNumberAfterAndCharacterInPIDSegment = Pattern.compile("PID.+?&(.+?)\\^");
        Matcher m = findHouseNumberAfterAndCharacterInPIDSegment.matcher(xad.getMessage().toString());
        return m.find() ? m.group(1) : null;
    }

    private String getPostalCode(XAD xad) {
        return xad.getZipOrPostalCode().getValue();
    }

    private String getCity(XAD xad) {
        return xad.getCity().getValue();
    }

    private String getStreetNumber(XAD xad) {
        return xad.getOtherDesignation().getValue();
    }

    private String getStreet(XAD xad) {
        return xad.getStreetAddress().getValue();
    }

    private Sex getSex(PID pid) {
        switch (pid.getSex().getValue()) {
            case "U":
                return Sex.UNKNOWN;
            case "M":
                return Sex.MALE;
            case "F":
                return Sex.FEMALE;
            default:
                throw new MissingSexException();
        }
    }

    private LocalDateTime getDateOfBirth(PID pid) throws DataTypeException {
        TSComponentOne timeOfAnEvent = pid.getDateOfBirth().getTimeOfAnEvent();
        return LocalDateTime.of(timeOfAnEvent.getYear(), timeOfAnEvent.getMonth(), timeOfAnEvent.getDay(), 0, 0);
    }

    private String getSurname(PID pid) {
        return pid.getPatientName()[0].getFamilyName().getValue();
    }

    private String getName(PID pid) {
        return pid.getPatientName()[0].getGivenName().getValue();
    }

    private String getHl7Id(PID pid) {
        return pid.getPatientIDInternalID(0).getID().getValue();
    }

    private String getPesel(PID pid) {
        String pesel = pid.getPatientIDExternalID().getID().getValue();
        return pesel == null || pesel.equals(peselNullValue) ? null : pesel;
    }
}
