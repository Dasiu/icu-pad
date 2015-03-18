package com.icupad.service.hl7_server.segment_parsers;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v23.datatype.EI;
import ca.uhn.hl7v2.model.v23.segment.OBR;
import com.icupad.domain.TestRequest;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class OBRParser implements Parser<OBR, TestRequest> {
    @Override
    public TestRequest parse(OBR obr) throws HL7Exception {
        TestRequest testRequest = new TestRequest();

        testRequest.setHl7Id(getHl7Id(obr.getPlacerOrderNumber()[0]));
        testRequest.setRequestDate(getRequestDate(obr));

        return testRequest;
    }

    private LocalDateTime getRequestDate(OBR obr) throws DataTypeException {
        Instant instant = obr.getObservationDateTime().getTimeOfAnEvent().getValueAsDate().toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    private String getHl7Id(EI ei) {
        return ei.getEi1_EntityIdentifier().getValue();
    }
}
