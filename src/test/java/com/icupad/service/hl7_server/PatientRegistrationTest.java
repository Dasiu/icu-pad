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
import com.icupad.domain.Address;
import com.icupad.domain.Patient;
import com.icupad.domain.Sex;
import com.icupad.service.PatientService;
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
import java.time.LocalDateTime;

import static com.icupad.test_data.HL7Messages.patientRegistrationMessage;
import static org.junit.Assert.*;

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
    }

    @Test
    public void shouldCreatePatientIfOneNotExists() throws HL7Exception, IOException, LLPException {
        Patient adamKowalski = createAdamKowalski();
        ADT_A01 adt_a01 = (ADT_A01) context.getGenericParser().parse(patientRegistrationMessage);

        initiator.sendAndReceive(adt_a01);

        Patient actualPatient = patientService.findByHl7Id(getHl7Id(adt_a01));
        assertNotNull(actualPatient);
        assertEquals(adamKowalski, actualPatient);
    }

    @Test
    public void shouldNotTryToCreateNewPatientIfOneExists() throws HL7Exception, IOException, LLPException {
        patientService.save(createAdamKowalski());
        ADT_A01 adt_a01 = (ADT_A01) context.getGenericParser().parse(patientRegistrationMessage);

        ACK ack = (ACK) initiator.sendAndReceive(adt_a01);

        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }

    private AcknowledgmentCode getAcknowledgmentCode(ACK ack) {
        return AcknowledgmentCode.valueOf(ack.getMSA().getAcknowledgementCode().getValue());
    }

    private Patient createAdamKowalski() {
        Patient patient = new Patient();

        patient.setHl7Id("123456789");
        patient.setPesel("69123001518");
        patient.setName("Adam");
        patient.setSurname("Kowalski");
        patient.setBirthDate(LocalDateTime.of(1991, 2, 10, 0, 0));
        patient.setSex(Sex.UNKNOWN);
        patient.setAddress(createAdamKowalskiAddress());

        return patient;
    }

    private Address createAdamKowalskiAddress() {
        Address address = new Address();
        address.setStreet("Piłsudskiego");
        address.setStreetNumber("112a");
        address.setHouseNumber("2b");
        address.setPostalCode("64-500");
        address.setCity("Poznań");
        return address;
    }

    private String getHl7Id(ADT_A01 adt_a01) {
        return adt_a01.getPID().getPatientIDInternalID(0).getID().getValue();
    }
}
