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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class HL7ServerGeneralTests {
    private static final String host = "localhost";

    @Value("${hl7_server.port}")
    private int port;

    @Value("${hl7_server.use_ssl}")
    private boolean useSSL;

    @Qualifier("patientRegistrationMessage")
    @Autowired
    private String patientRegistrationMessage;

    private HapiContext context;

    private Connection connection;

    private Initiator initiator;

    @Before
    public void before() throws HL7Exception {
        context = new DefaultHapiContext();
        connection = context.newClient(host, port, useSSL);
        initiator = connection.getInitiator();
    }

    @After
    public void after() {
        connection.close();
    }

    @Test
    public void shouldResponseValidMSASegment() throws HL7Exception, IOException, LLPException {
        Message messageWithMSHSegment = context.getGenericParser().parse(patientRegistrationMessage);

        ACK response = (ACK) initiator.sendAndReceive(messageWithMSHSegment);

        assertMessageControlId(response, messageWithMSHSegment);
        assertAcknowledgmentCode(response);
    }

    @Test
    public void shouldResponseErrorACKWhenInvalidPatientRegistrationMessageReceived() {
        fail("not implemented yet");
    }

    @Test
    public void shouldResponseErrorACKWhenAlreadyCorrectlyHandledMessageHasBeenReceivedAgain() {
        fail("not implemented yet");
    }

    @Test
    public void shouldResponseErrorACKWhenPatientDoesNotExist() {
        fail("not implemented yet");
    }

    @Test
    public void shouldResponseErrorACKWhenStayDoesNotExist() {
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
