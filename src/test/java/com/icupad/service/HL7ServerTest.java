package com.icupad.service;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.parser.Parser;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@Transactional
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class HL7ServerTest {
    private static final String host = "localhost";

    @Value("${hl7_server.port}")
    private int port;

    @Value("${hl7_server.use_ssl}")
    private boolean useSSL;

    @Qualifier("adtA01Message")
    @Autowired
    private String adtA01Message;

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
    public void readPatientRegistrationMessage() throws LLPException, IOException, HL7Exception {
        Parser p = context.getPipeParser();
        Message adt = p.parse(adtA01Message);
        Message response = initiator.sendAndReceive(adt);

        assertTrue(response instanceof ACK);
        fail("check ack segments");
    }
}
