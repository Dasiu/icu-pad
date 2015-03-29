package com.icupad.hl7_gateway.service.hl7_server.segment_parser;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.datatype.TSComponentOne;
import ca.uhn.hl7v2.model.v23.datatype.XCN;
import ca.uhn.hl7v2.model.v23.segment.PV1;
import com.icupad.hl7_gateway.domain.AdmittingDoctor;
import com.icupad.hl7_gateway.domain.AssignedPatientLocation;
import com.icupad.hl7_gateway.domain.Stay;
import com.icupad.hl7_gateway.domain.StayType;
import com.icupad.hl7_gateway.service.hl7_server.MissingStayTypeException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PV1Parser implements Parser<PV1, Stay> {
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
        admittingDoctor.setHl7Id(xcn.getIDNumber().getValue());
        admittingDoctor.setName(xcn.getGivenName().getValue());
        admittingDoctor.setSurname(xcn.getFamilyName().getValue());
        admittingDoctor.setNpwz(xcn.getMiddleInitialOrName().getValue());

        return admittingDoctor;
    }

    private String getHl7Id(PV1 pv1) {
        return pv1.getVisitNumber().getID().getValue();
    }

    private LocalDateTime getDischargeDate(PV1 pv1) throws HL7Exception {
        TSComponentOne timeOfAnEvent = pv1.getDischargeDateTime().getTimeOfAnEvent();
        return convertToLocalDateTime(timeOfAnEvent);
    }

    private LocalDateTime getAdmitDate(PV1 pv1) throws HL7Exception {
        TSComponentOne timeOfAnEvent = pv1.getAdmitDateTime().getTimeOfAnEvent();
        return convertToLocalDateTime(timeOfAnEvent);
    }

    private AssignedPatientLocation getAssignedPatientLocation(PV1 pv1) {
        AssignedPatientLocation assignedPatientLocation = new AssignedPatientLocation();
        assignedPatientLocation.setName(pv1.getAssignedPatientLocation().getPointOfCare().getValue());
        return assignedPatientLocation;
    }

    private StayType getType(PV1 pv1) {
        switch (pv1.getPatientClass().getValue()) {
            case "I":
                return StayType.INPATIENT;
            case "O":
                return StayType.OUTPATIENT;
            default:
                throw new MissingStayTypeException();
        }
    }

    private LocalDateTime convertToLocalDateTime(TSComponentOne timeOfAnEvent) throws HL7Exception {
        return timeOfAnEvent.isEmpty() ? null : LocalDateTime.of(timeOfAnEvent.getYear(),
                timeOfAnEvent.getMonth(),
                timeOfAnEvent.getDay(),
                timeOfAnEvent.getHour(),
                timeOfAnEvent.getMinute(),
                timeOfAnEvent.getSecond());
    }
}
