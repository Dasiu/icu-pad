package com.icupad.service.hl7_server;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import com.icupad.Application;
import com.icupad.service.PatientService;
import com.icupad.service.StayService;
import com.icupad.service.TestRequestService;
import com.icupad.service.TestResultService;
import com.icupad.test_data.HL7Messages;
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

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class TestResultTest {
    private static final String host = "localhost";

    @Value("${hl7_server.port}")
    private int port;

    @Value("${hl7_server.use_ssl}")
    private boolean useSSL;

    private Connection connection;

    private Initiator initiator;

    @Autowired
    private StayService stayService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private HapiContext hapiContext;

    @Autowired
    private TestResultService testResultsService;

    @Autowired
    private TestRequestService testRequestService;

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
    public void shouldSaveTestRequest() throws HL7Exception, IOException, LLPException {
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage);

        initiator.sendAndReceive(oru_r01);

        assertEquals(1, testRequestService.count());
    }

//    @Test
//    public void shouldSaveTestRequest2() throws HL7Exception, IOException, LLPException {
//        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage2);
//
//        initiator.sendAndReceive(oru_r01);
//
//        assertEquals(1, testRequestService.count());
//    }
//
//    @Test
//    public void shouldSaveTestRequest3() throws HL7Exception, IOException, LLPException {
//        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage3);
////        Message parse = hapiContext.getPipeParser().parse(HL7Messages.testResultsMessage);
//
//        initiator.sendAndReceive(oru_r01);
//
//        assertEquals(1, testRequestService.count());
//    }
//
//    @Test
//    public void shouldSaveTestRequest4() throws HL7Exception, IOException, LLPException {
//        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage4);
//
//        initiator.sendAndReceive(oru_r01);
//
//        assertEquals(1, testRequestService.count());
//    }
}
