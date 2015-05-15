package com.icupad.hl7_gateway.core.service.hl7_server;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ADT_A08;
import com.icupad.hl7_gateway.core.Application;
import com.icupad.hl7_gateway.core.domain.Patient;
import com.icupad.hl7_gateway.core.domain.Sex;
import com.icupad.hl7_gateway.core.service.PatientService;
import com.icupad.hl7_gateway.core.service.StayService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static com.icupad.hl7_gateway.core.service.hl7_server.HL7TestUtils.getAcknowledgmentCode;
import static com.icupad.hl7_gateway.test_data.HL7Messages.patientDetailsUpdateMessage;
import static com.icupad.hl7_gateway.test_data.Patients.createAdamKowalski;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class PatientDetailsUpdateTest {
    private static final String host = "localhost";

    @Value("${hl7_server.port}")
    private int port;

    @Value("${hl7_server.use_ssl}")
    private boolean useSSL;

    @Autowired
    private HapiContext hapiContext;

    private Connection connection;

    private Initiator initiator;

    @Autowired
    private PatientService patientService;

    @Autowired
    private StayService stayService;

    @Before
    public void before() throws HL7Exception {
        connection = hapiContext.newClient(host, port, useSSL);
        initiator = connection.getInitiator();
    }

    @After
    public void after() {
        connection.close();

        stayService.deleteAll();
        patientService.deleteAll();
    }

    @Test
    public void shouldUpdatePatientDetails() throws HL7Exception, IOException, LLPException {
        Patient adamKowalski = createAdamKowalski();
        patientService.save(adamKowalski);
        ADT_A08 adt_a08 = (ADT_A08) hapiContext.getGenericParser().parse(patientDetailsUpdateMessage);

        ACK ack = (ACK) initiator.sendAndReceive(adt_a08);

        Patient patient = patientService.findByHl7Id(adamKowalski.getHl7Id());
        assertNotNull(patient);
        assertEquals(Sex.MALE, patient.getSex());
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }
}
