package com.icupad.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ADT_A01;
import com.icupad.domain.Patient;
import com.icupad.domain.Stay;
import com.icupad.service.PatientService;
import com.icupad.service.StayService;
import com.icupad.service.hl7_server.segment_parsers.PIDParser;
import com.icupad.service.hl7_server.segment_parsers.PV1Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PatientRegistrationHandler extends AbstractMessageHandler<ADT_A01> {
    private final PatientService patientService;
    private final StayService stayService;
    private final PIDParser pidParser;
    private final PV1Parser pv1Parser;

    @Autowired
    public PatientRegistrationHandler(PatientService patientService, StayService stayService, PIDParser pidParser,
                                      PV1Parser pv1Parser) {
        this.patientService = patientService;
        this.stayService = stayService;
        this.pidParser = pidParser;
        this.pv1Parser = pv1Parser;
    }

    @Override
    public Class<ADT_A01> getMessageType() {
        return ADT_A01.class;
    }

    @Override
    public ACK handle(ADT_A01 adt_a01) throws IOException, HL7Exception {
        Patient newPatient = pidParser.parse(adt_a01.getPID());
        Stay stay = pv1Parser.parse(adt_a01.getPV1());

        Patient existingPatient = patientService.findByHl7Id(newPatient.getHl7Id());
        if (existingPatient == null) {
            patientService.save(newPatient);

            stay.setPatient(newPatient);
        } else {
            stay.setPatient(existingPatient);
        }

        stayService.save(stay);

        return generateACK(adt_a01);
    }
}
