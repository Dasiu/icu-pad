package com.icupad.service.hl7_server;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ADT_A01;
import com.icupad.Application;
import com.icupad.domain.Patient;
import com.icupad.domain.Stay;
import com.icupad.service.PatientService;
import com.icupad.service.StayService;
import com.icupad.test_data.Patients;
import com.icupad.test_data.Stays;
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

import static com.icupad.service.hl7_server.HL7TestUtils.getAcknowledgmentCode;
import static com.icupad.test_data.HL7Messages.patientRegistrationMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class PatientRegistrationTest {
    private static final String host = "localhost";

    @Value("${hl7_server.port}")
    private int port;

    @Value("${hl7_server.use_ssl}")
    private boolean useSSL;

    private HapiContext context;

    private Connection connection;

    private Initiator initiator;

    @Autowired
    private PatientService patientService;

    @Autowired
    private StayService stayService;

    @Before
    public void before() throws HL7Exception {
        context = new DefaultHapiContext();
        context.setLowerLayerProtocol(new MinLowerLayerProtocol(true));

        connection = context.newClient(host, port, useSSL);
        initiator = connection.getInitiator();
    }

    @After
    public void after() {
        connection.close();

        stayService.deleteAll();
        patientService.deleteAll();
    }

    @Test
    public void shouldCreatePatientIfOneNotExists() throws HL7Exception, IOException, LLPException {
        Patient adamKowalski = Patients.createAdamKowalski();
        ADT_A01 adt_a01 = (ADT_A01) context.getGenericParser().parse(patientRegistrationMessage);

        ACK ack = (ACK) initiator.sendAndReceive(adt_a01);

        Patient actualPatient = patientService.findByHl7Id(adamKowalski.getHl7Id());
        assertNotNull(actualPatient);
        assertEquals(adamKowalski, actualPatient);
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }

    @Test
    public void shouldNotTryToCreateNewPatientIfOneExists() throws HL7Exception, IOException, LLPException {
        patientService.save(Patients.createAdamKowalski());
        ADT_A01 adt_a01 = (ADT_A01) context.getGenericParser().parse(patientRegistrationMessage);

        ACK ack = (ACK) initiator.sendAndReceive(adt_a01);

        assertEquals(1, patientService.count());
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }

    @Test
    public void shouldCreateStay() throws HL7Exception, IOException, LLPException {
        Stay adamKowalskiStay = Stays.createAdamKowalskiStay();
        ADT_A01 adt_a01 = (ADT_A01) context.getGenericParser().parse(patientRegistrationMessage);

        ACK ack = (ACK) initiator.sendAndReceive(adt_a01);

        Stay actualStay = stayService.findByHl7Id(adamKowalskiStay.getHl7Id());
        assertNotNull(actualStay);
        assertEquals(adamKowalskiStay, actualStay);
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }
}
