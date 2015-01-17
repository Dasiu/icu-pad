package com.icupad.service.hl7_server.segment_parsers;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v23.datatype.TSComponentOne;
import ca.uhn.hl7v2.model.v23.segment.PV1;
import com.icupad.domain.AssignedPatientLocation;
import com.icupad.domain.Stay;
import com.icupad.domain.StayType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PV1Parser implements Parser<PV1, Stay> {
    @Override
    public Stay parse(PV1 pv1) throws DataTypeException {
        Stay stay = new Stay();

        stay.setType(getType(pv1));
        stay.setHl7Id(getHl7Id(pv1));
        stay.setAssignedPatientLocation(getAssignedPatientLocation(pv1));
        stay.setAdmitDate(getAdmitDate(pv1));
        stay.setDischargeDate(getDischargeDate(pv1));

        return stay;
    }

    private String getHl7Id(PV1 pv1) {
        return pv1.getVisitNumber().getID().getValue();
    }

    private LocalDateTime getDischargeDate(PV1 pv1) throws DataTypeException {
        TSComponentOne timeOfAnEvent = pv1.getDischargeDateTime().getTimeOfAnEvent();
        return LocalDateTime.of(timeOfAnEvent.getYear(),
                timeOfAnEvent.getMonth(),
                timeOfAnEvent.getDay(),
                timeOfAnEvent.getHour(),
                timeOfAnEvent.getMinute(),
                timeOfAnEvent.getSecond());
    }

    private LocalDateTime getAdmitDate(PV1 pv1) throws DataTypeException {
        TSComponentOne timeOfAnEvent = pv1.getAdmitDateTime().getTimeOfAnEvent();
        return LocalDateTime.of(timeOfAnEvent.getYear(),
                timeOfAnEvent.getMonth(),
                timeOfAnEvent.getDay(),
                timeOfAnEvent.getHour(),
                timeOfAnEvent.getMinute(),
                timeOfAnEvent.getSecond());
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
}
