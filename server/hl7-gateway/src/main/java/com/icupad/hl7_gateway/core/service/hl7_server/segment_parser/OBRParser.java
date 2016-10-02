package com.icupad.hl7_gateway.core.service.hl7_server.segment_parser;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.datatype.EI;
import ca.uhn.hl7v2.model.v23.segment.OBR;
import com.icupad.hl7_gateway.domain.TestRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.icupad.hl7_gateway.core.service.hl7_server.segment_parser.ParserUtils.convertToLocalDateTime;

@Component
public class OBRParser implements Parser<OBR, TestRequest> {
    @Override
    public TestRequest parse(OBR obr) throws HL7Exception {
        TestRequest testRequest = new TestRequest();

        testRequest.setHl7Id(getHl7Id(obr.getPlacerOrderNumber()[0]));
        testRequest.setRequestDate(getRequestDate(obr));
        testRequest.setRawTestName(getRawTestName(obr));

        return testRequest;
    }

    private String getRawTestName(OBR obr) {
        return obr.getUniversalServiceIdentifier().getIdentifier().getValue();
    }

    private LocalDateTime getRequestDate(OBR obr) throws HL7Exception {
        return convertToLocalDateTime(obr.getObservationDateTime().getTimeOfAnEvent());
    }

    private String getHl7Id(EI ei) {
        return ei.getEi1_EntityIdentifier().getValue();
    }
}
