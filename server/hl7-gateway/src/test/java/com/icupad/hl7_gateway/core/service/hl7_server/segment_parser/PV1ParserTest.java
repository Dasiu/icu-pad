package com.icupad.hl7_gateway.core.service.hl7_server.segment_parser;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v23.message.ADT_A01;
import ca.uhn.hl7v2.model.v23.segment.PV1;
import com.icupad.hl7_gateway.Application;
import com.icupad.hl7_gateway.domain.AdmittingDoctor2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.icupad.hl7_gateway.test_data.HL7Messages.patientRegistrationMessageWithoutAddressAndAdmittingDoctor;
import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class PV1ParserTest {
    @Autowired
    private HapiContext hapiContext;

    @Autowired
    private PV1Parser pv1Parser;

    @Test
    public void emptyAdmittingDoctorShouldBeParsedToNull() throws HL7Exception {
        PV1 pv1 = pv1WithEmptyAdmittingDoctor();

        AdmittingDoctor2 admittingDoctor2 = pv1Parser.parse(pv1).getAdmittingDoctor2();

        assertEquals(null, admittingDoctor2.getHl7Id());
        assertEquals(null, admittingDoctor2.getName());
        assertEquals(null, admittingDoctor2.getNpwz());
        assertEquals(null, admittingDoctor2.getSurname());
    }

    private PV1 pv1WithEmptyAdmittingDoctor() throws HL7Exception {
        ADT_A01 messageWithPid = (ADT_A01) hapiContext.getGenericParser()
                .parse(patientRegistrationMessageWithoutAddressAndAdmittingDoctor);
        return messageWithPid.getPV1();
    }
}
