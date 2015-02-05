package com.icupad.service.hl7_server;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ADT_A01;
import com.icupad.Application;
import com.icupad.service.PatientService;
import com.icupad.service.StayService;
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

import static com.icupad.test_data.HL7Messages.patientRegistrationMessage;
import static com.icupad.test_data.HL7Messages.patientRegistrationMessageWithInvalidPesel;
import static org.junit.Assert.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class HL7ServerTest {
    private static final String host = "localhost";

    @Value("${hl7_server.port}")
    private int port;

    @Value("${hl7_server.use_ssl}")
    private boolean useSSL;

    private HapiContext context;

    private Connection connection;

    private Initiator initiator;

    @Autowired
    private StayService stayService;

    @Autowired
    private PatientService patientService;

    @Before
    public void before() throws HL7Exception {
        context = new DefaultHapiContext();
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
    public void shouldResponseValidMSASegment() throws HL7Exception, IOException, LLPException {
        Message messageWithMSHSegment = context.getGenericParser().parse(patientRegistrationMessage);

        ACK response = (ACK) initiator.sendAndReceive(messageWithMSHSegment);

        assertMessageControlId(response, messageWithMSHSegment);
        assertAcknowledgmentCode(response);
    }

    @Test
    public void shouldResponseACKWithoutTriggerEventInCaseOfSuccess() throws HL7Exception, IOException, LLPException {
        Message messageWhichWillRaiseException =
                context.getGenericParser().parse(patientRegistrationMessageWithInvalidPesel);

        ACK response = (ACK) initiator.sendAndReceive(messageWhichWillRaiseException);

        assertNull(response.getMSH().getMessageType().getTriggerEvent().getValue());
    }

    @Test
    public void shouldResponseACKWithoutTriggerEventInCaseOfError() throws HL7Exception, IOException, LLPException {
        Message messageWithMSHSegment = context.getGenericParser().parse(patientRegistrationMessage);

        ACK response = (ACK) initiator.sendAndReceive(messageWithMSHSegment);

        assertNull(response.getMSH().getMessageType().getTriggerEvent().getValue());
    }

    @Test
    public void shouldResponseErrorACKWhenInvalidPatientRegistrationMessageReceived() {
        fail("not implemented yet");
    }

    @Test
    public void shouldResponseErrorACKWhenAlreadyCorrectlyHandledMessageHasBeenReceivedAgain() {
        fail("not implemented yet");
    }

    private void assertAcknowledgmentCode(ACK response) {
        String actualAcknowledgementCode = response.getMSA().getAcknowledgementCode().getValue();
        assertEquals("CA", actualAcknowledgementCode);
    }

    private void assertMessageControlId(ACK response, Message messageWithMSHSegment) {
        ADT_A01 adt = (ADT_A01) messageWithMSHSegment;
        String expectedMessageId = adt.getMSH().getMessageControlID().getValue();
        String actualMessageId = response.getMSA().getMessageControlID().getValue();
        assertEquals(expectedMessageId, actualMessageId);
    }
}
