package com.icupad.hl7_gateway.service.hl7_server.segment_parser;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.datatype.XCN;
import ca.uhn.hl7v2.model.v23.segment.PV1;
import com.icupad.hl7_gateway.domain.AdmittingDoctor;
import com.icupad.hl7_gateway.domain.AssignedPatientLocation;
import com.icupad.hl7_gateway.domain.Stay;
import com.icupad.hl7_gateway.domain.StayType;
import com.icupad.hl7_gateway.service.hl7_server.InvalidStayTypeException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.icupad.hl7_gateway.service.hl7_server.segment_parser.ParserUtils.convertToLocalDateTime;

@Component
public class PV1Parser extends AbstractParser implements Parser<PV1, Stay> {
    @Override
    public Stay parse(PV1 pv1) throws HL7Exception {
        Stay stay = new Stay();

        stay.setType(getType(pv1));
        stay.setHl7Id(getHl7Id(pv1));
        stay.setAssignedPatientLocation(getAssignedPatientLocation(pv1));
        stay.setAdmittingDoctor(getAdmittingDoctor(pv1));
        stay.setAdmitDate(getAdmitDate(pv1));
        stay.setDischargeDate(getDischargeDate(pv1));

        return stay;
    }

    private AdmittingDoctor getAdmittingDoctor(PV1 pv1) {
        AdmittingDoctor admittingDoctor = new AdmittingDoctor();

        XCN xcn = pv1.getAdmittingDoctor()[0];
        admittingDoctor.setHl7Id(getAdmittingDoctorHl7Id(xcn));
        admittingDoctor.setName(getName(xcn));
        admittingDoctor.setSurname(getSurname(xcn));
        admittingDoctor.setNpwz(getNpwz(xcn));

        return admittingDoctor;
    }

    private String getNpwz(XCN xcn) {
        String npwz = xcn.getMiddleInitialOrName().getValue();
        return parseNullValue(npwz);
    }

    private String getSurname(XCN xcn) {
        String surname = xcn.getFamilyName().getValue();
        return parseNullValue(surname);
    }

    private String getName(XCN xcn) {
        String name = xcn.getGivenName().getValue();
        return parseNullValue(name);
    }

    private String getAdmittingDoctorHl7Id(XCN xcn) {
        String hl7Id = xcn.getIDNumber().getValue();
        return parseNullValue(hl7Id);
    }

    private String getHl7Id(PV1 pv1) {
        return pv1.getVisitNumber().getID().getValue();
    }

    private LocalDateTime getDischargeDate(PV1 pv1) throws HL7Exception {
        return convertToLocalDateTime(pv1.getDischargeDateTime().getTimeOfAnEvent());
    }

    private LocalDateTime getAdmitDate(PV1 pv1) throws HL7Exception {
        return convertToLocalDateTime(pv1.getAdmitDateTime().getTimeOfAnEvent());
    }

    private AssignedPatientLocation getAssignedPatientLocation(PV1 pv1) {
        AssignedPatientLocation assignedPatientLocation = new AssignedPatientLocation();
        assignedPatientLocation.setName(pv1.getAssignedPatientLocation().getPointOfCare().getValue());
        return assignedPatientLocation;
    }

    private StayType getType(PV1 pv1) {
        switch (pv1.getPatientClass().getValue()) {
            case "B":
                return StayType.OBSTETRICS;
            case "E":
                return StayType.EMERGENCY;
            case "I":
                return StayType.INPATIENT;
            case "O":
                return StayType.OUTPATIENT;
            case "P":
                return StayType.PREADMIT;
            case "R":
                return StayType.RECURRING_PATIENT;
            default:
                throw new InvalidStayTypeException();
        }
    }
}
