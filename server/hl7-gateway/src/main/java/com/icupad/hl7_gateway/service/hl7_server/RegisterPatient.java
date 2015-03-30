package com.icupad.hl7_gateway.service.hl7_server;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.segment.PID;
import ca.uhn.hl7v2.model.v23.segment.PV1;
import com.icupad.hl7_gateway.domain.Patient;
import com.icupad.hl7_gateway.domain.Stay;
import com.icupad.hl7_gateway.service.PatientService;
import com.icupad.hl7_gateway.service.StayService;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.PIDParser;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.PV1Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Command like object used to share common code between PatientRegisterHandler and TestResultsHandler
 */
@Component
public class RegisterPatient {
    private final PatientService patientService;
    private final StayService stayService;
    private final PIDParser pidParser;
    private final PV1Parser pv1Parser;

    @Autowired
    public RegisterPatient(PatientService patientService, StayService stayService, PIDParser pidParser,
                                      PV1Parser pv1Parser) {
        this.patientService = patientService;
        this.stayService = stayService;
        this.pidParser = pidParser;
        this.pv1Parser = pv1Parser;
    }

    public void accept(PID pid, PV1 pv1) throws HL7Exception {
        Patient patient = pidParser.parse(pid);
        Patient existingPatient = patientService.findByHl7Id(patient.getHl7Id());

        if (existingPatient == null) {
            patientService.save(patient);
        }

        Stay stay = pv1Parser.parse(pv1);
        stay.setPatient(existingPatient == null ? patient : existingPatient);
        stay.setActive(true);

        stayService.save(stay);
    }
}
