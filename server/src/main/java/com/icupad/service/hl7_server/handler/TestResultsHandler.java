package com.icupad.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.group.ORU_R01_ORDER_OBSERVATION;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import ca.uhn.hl7v2.model.v23.segment.ORC;
import com.icupad.domain.Patient;
import com.icupad.domain.Stay;
import com.icupad.service.PatientService;
import com.icupad.service.StayService;
import com.icupad.service.hl7_server.segment_parsers.OBRParser;
import com.icupad.service.hl7_server.segment_parsers.OBXParser;
import com.icupad.service.hl7_server.segment_parsers.PIDParser;
import com.icupad.service.hl7_server.segment_parsers.PV1Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class TestResultsHandler extends AbstractMessageHandler<ORU_R01> {
    private final PatientService patientService;

    private final StayService stayService;

    private final PIDParser pidParser;

    private final PV1Parser pv1Parser;

    private final OBRParser obrParser;

    private final OBXParser obxParser;

    @Autowired
    public TestResultsHandler(PatientService patientService, StayService stayService, PIDParser pidParser,
                              PV1Parser pv1Parser, OBRParser obrParser, OBXParser obxParser) {
        this.patientService = patientService;
        this.stayService = stayService;
        this.pidParser = pidParser;
        this.pv1Parser = pv1Parser;
        this.obrParser = obrParser;
        this.obxParser = obxParser;
    }

    @Override
    public ACK handle(ORU_R01 oru_r01) throws IOException, HL7Exception {
        Patient patient = pidParser.parse(oru_r01.getRESPONSE().getPATIENT().getPID());
        Stay stay = pv1Parser.parse(oru_r01.getRESPONSE().getPATIENT().getVISIT().getPV1());

        ORC orc = getOrc(oru_r01);

        List<ORU_R01_ORDER_OBSERVATION> observations = oru_r01.getRESPONSE().getORDER_OBSERVATIONAll();
//        List<Pair<TestRequest, TestResult>> testRequestsAndResults = observations.stream()
//                .map(this::getTestRequestsAndResults)
//                .collect(Collectors.toList());

        return generateACK(oru_r01);
    }

    @Override
    public Class<ORU_R01> getMessageType() {
        return ORU_R01.class;
    }

//    private Pair<TestRequest, TestResult> getTestRequestsAndResults(ORU_R01_ORDER_OBSERVATION orderObservation) {
//        try {
//            return Pair.of(obrParser.parse(orderObservation.getOBR()),
//                    obxParser.parse(orderObservation.getOBSERVATION().getOBX()));
//        } catch (HL7Exception e) {
//            e.printStackTrace(); // fixme
//            return null;
//        }
//    }

    private ORC getOrc(ORU_R01 oru_r01) {
        return oru_r01.getRESPONSE().getORDER_OBSERVATION().getORC();
    }
}
