package com.icupad.hl7_gateway.service.hl7_server;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import com.icupad.hl7_gateway.Application;
import com.icupad.hl7_gateway.domain.Stay;
import com.icupad.hl7_gateway.service.*;
import com.icupad.test_data.HL7Messages;
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

import static com.icupad.hl7_gateway.service.hl7_server.HL7TestUtils.getAcknowledgmentCode;
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

    @Autowired
    private TestService testService;

    @Before
    public void before() throws HL7Exception {
        connection = hapiContext.newClient(host, port, useSSL);
        initiator = connection.getInitiator();
    }

    @After
    public void after() {
        connection.close();

        testResultsService.deleteAll();
        testRequestService.deleteAll();
        testService.deleteAll();
        stayService.deleteAll();
        patientService.deleteAll();
    }

    @Test
    public void shouldSaveTestRequests() throws HL7Exception, LLPException, IOException {
        createAndSaveAdamKowalskisStay();
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage);

        ACK ack = (ACK) initiator.sendAndReceive(oru_r01);

        assertEquals(2, testRequestService.count());
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }

    @Test
    public void shouldSaveNewTypeOfTest() throws HL7Exception, LLPException, IOException {
        createAndSaveAdamKowalskisStay();
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage);

        ACK ack = (ACK) initiator.sendAndReceive(oru_r01);

        assertEquals(2, testService.count());
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }

    @Test
    public void shouldNotDuplicateTests() throws HL7Exception, LLPException, IOException {
        createAndSaveAdamKowalskisStay();
        createAndSaveTest();
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage);

        ACK ack = (ACK) initiator.sendAndReceive(oru_r01);

        assertEquals(2, testService.count());
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }

    @Test
    public void shouldSaveTestResults() throws HL7Exception, LLPException, IOException {
        createAndSaveAdamKowalskisStay();
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage);

        ACK ack = (ACK) initiator.sendAndReceive(oru_r01);

        assertEquals(2, testResultsService.count());
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }

    @Test
    public void shouldResponseErrorACKIfStayDoesNotExist() throws HL7Exception, LLPException, IOException {
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage);

        ACK ack = (ACK) initiator.sendAndReceive(oru_r01);

        assertEquals(AcknowledgmentCode.CE, getAcknowledgmentCode(ack));
    }

    @Test
    public void shouldIgnoreMissingResults() throws HL7Exception, IOException, LLPException {
        createAndSaveAdamKowalskisStay();
        ORU_R01 oru_r01 =
                (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessageWithOneMissingResult);

        ACK ack = (ACK) initiator.sendAndReceive(oru_r01);

        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
        assertEquals(1, testRequestService.count());
        assertEquals(1, testResultsService.count());
    }

    @Test
    public void shouldNotDuplicateTestResults() throws HL7Exception, IOException, LLPException {
        createAndSaveAdamKowalskisStay();
        ORU_R01 missingResult =
                (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessageWithOneMissingResult);
        ORU_R01 completeResults = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage);

        initiator.sendAndReceive(missingResult);
        ACK ack = (ACK) initiator.sendAndReceive(completeResults);

        assertEquals(2, testRequestService.count());
        assertEquals(2, testResultsService.count());
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }

    private void createAndSaveTest() {
        com.icupad.hl7_gateway.domain.Test test = new com.icupad.hl7_gateway.domain.Test();
        test.setName("Morfologia - hematokryt");
        testService.save(test);
    }

    private void createAndSaveAdamKowalskisStay() {
        Stay adamKowalskiStay = Stays.createAdamKowalskiStay();
        patientService.save(adamKowalskiStay.getPatient());
        stayService.save(adamKowalskiStay);
    }
}
