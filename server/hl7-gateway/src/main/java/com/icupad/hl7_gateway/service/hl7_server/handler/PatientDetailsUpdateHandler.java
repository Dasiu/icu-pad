package com.icupad.hl7_gateway.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.message.ADT_A08;
import com.icupad.hl7_gateway.domain.Patient;
import com.icupad.hl7_gateway.service.PatientService;
import com.icupad.hl7_gateway.service.hl7_server.PatientNotFoundException;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.PIDParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class PatientDetailsUpdateHandler implements MessageHandler<ADT_A08> {
    private final PatientService patientService;
    private final PIDParser pidParser;

    @Autowired
    public PatientDetailsUpdateHandler(PatientService patientService, PIDParser pidParser) {
        this.patientService = patientService;
        this.pidParser = pidParser;
    }

    @Override
    public Class<ADT_A08> getMessageType() {
        return ADT_A08.class;
    }

    @Override
    @Transactional
    public void handle(ADT_A08 adt_a08) throws HL7Exception {
        Patient patient = pidParser.parse(adt_a08.getPID());

        Patient existingPatient = patientService.findByHl7Id(patient.getHl7Id());
        if (existingPatient != null) {
            existingPatient.setPesel(patient.getPesel());
            existingPatient.setName(patient.getName());
            existingPatient.setSurname(patient.getSurname());
            existingPatient.setBirthDate(patient.getBirthDate());
            existingPatient.setSex(patient.getSex());
            existingPatient.setAddress(patient.getAddress());

            patientService.save(existingPatient);
        } else {
            throw new PatientNotFoundException();
        }
    }
}
