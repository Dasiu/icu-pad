package com.icupad.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ADT_A01;
import com.icupad.domain.Patient;
import com.icupad.service.PatientService;
import com.icupad.service.hl7_server.segment_parsers.PIDParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PatientRegistrationHandler extends AbstractMessageHandler<ADT_A01> {
    private final PatientService patientService;
    private final PIDParser pidParser;

    @Autowired
    public PatientRegistrationHandler(PatientService patientService, PIDParser pidParser) {
        this.patientService = patientService;
        this.pidParser = pidParser;
    }

    @Override
    public Class<ADT_A01> getMessageType() {
        return ADT_A01.class;
    }

    @Override
    public ACK handle(ADT_A01 adt_a01) throws IOException, HL7Exception {
        Patient patient = pidParser.parse(adt_a01.getPID());

        if (!patientService.existsByHl7Id(patient.getHl7Id())) {
            patientService.save(patient);
        }

        return generateACK(adt_a01);
    }
}
