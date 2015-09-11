package com.icupad.hl7_gateway.core.service.hl7_server;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ADT_A03;
import com.icupad.hl7_gateway.Application;
import com.icupad.hl7_gateway.core.domain.Stay;
import com.icupad.hl7_gateway.core.service.PatientService;
import com.icupad.hl7_gateway.core.service.StayService;
import com.icupad.hl7_gateway.test_data.Stays;
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

import static com.icupad.hl7_gateway.core.service.hl7_server.HL7TestUtils.getAcknowledgmentCode;
import static com.icupad.hl7_gateway.test_data.HL7Messages.patientDischargeMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class PatientDischargeTest {
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
    public void shouldDischargePatient() throws HL7Exception, IOException, LLPException {
        createAndSaveAdamKowalskisStay();
        ADT_A03 adt_a03 = (ADT_A03) hapiContext.getGenericParser().parse(patientDischargeMessage);

        ACK ack = (ACK) initiator.sendAndReceive(adt_a03);

        Stay stay = stayService.findByHl7Id("476711");
        assertNotNull(stay);
        assertFalse(stay.isActive());
        assertEquals(LocalDateTime.of(2013, 4, 24, 16, 31, 0), stay.getDischargeDate());
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }

    private void createAndSaveAdamKowalskisStay() {
        Stay adamKowalskiStay = Stays.createAdamKowalskiStay();
        patientService.save(adamKowalskiStay.getPatient());
        stayService.save(adamKowalskiStay);
    }
}
