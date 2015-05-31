package com.icupad.hl7_gateway.core.service.hl7_server;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import com.icupad.hl7_gateway.core.Application;
import com.icupad.hl7_gateway.core.domain.Stay;
import com.icupad.hl7_gateway.core.service.PatientService;
import com.icupad.hl7_gateway.core.service.StayService;
import com.icupad.hl7_gateway.core.service.TestMappingService;
import com.icupad.hl7_gateway.core.service.TestTypeService;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.config.CompleteBloodCountProductionSeed;
import com.icupad.hl7_gateway.test_type_module.default_test_type.config.DefaultProductionSeed;
import com.icupad.hl7_gateway.test_type_module.default_test_type.service.TestPanelResultService;
import com.icupad.hl7_gateway.test_type_module.default_test_type.service.TestRequestService;
import com.icupad.hl7_gateway.test_type_module.default_test_type.service.TestResultService;
import com.icupad.hl7_gateway.test_type_module.default_test_type.service.TestService;
import com.icupad.hl7_gateway.test_data.HL7Messages;
import com.icupad.hl7_gateway.test_data.Stays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static com.icupad.hl7_gateway.core.service.hl7_server.HL7TestUtils.getAcknowledgmentCode;
import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestResultsTest {
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

    @Autowired
    private TestMappingService testMappingService;

    @Autowired
    private TestTypeService testTypeService;

    @Autowired
    private DefaultProductionSeed defaultProductionSeed;

    @Autowired
    private CompleteBloodCountProductionSeed completeBloodCountProductionSeed;

    @Autowired
    private TestPanelResultService testPanelResultService;

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
        testPanelResultService.deleteAll();
        testMappingService.deleteAll();
        testService.deleteAll();
        testTypeService.deleteAll();
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
    public void shouldSaveTestResults() throws HL7Exception, LLPException, IOException {
        createAndSaveAdamKowalskisStay();
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage);

        ACK ack = (ACK) initiator.sendAndReceive(oru_r01);

        assertEquals(2, testResultsService.count());
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }

    @Test
    public void shouldRegisterPatientIfHasNotBeenRegisteredAlready() throws HL7Exception, LLPException, IOException {
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage);

        ACK ack = (ACK) initiator.sendAndReceive(oru_r01);

        assertEquals(1, patientService.count());
        assertEquals(1, stayService.count());
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
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

    @Test
    public void shouldNotDuplicateTestPanelResults() throws HL7Exception, IOException, LLPException {
        createAndSaveAdamKowalskisStay();
        ORU_R01 missingResult =
                (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessageWithOneMissingResult);
        ORU_R01 completeResults = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.testResultsMessage);

        initiator.sendAndReceive(missingResult);
        ACK ack = (ACK) initiator.sendAndReceive(completeResults);

        assertEquals(1, testPanelResultService.count());
        assertEquals(AcknowledgmentCode.CA, getAcknowledgmentCode(ack));
    }

    private void createAndSaveAdamKowalskisStay() {
        Stay adamKowalskiStay = Stays.createAdamKowalskiStay();
        patientService.save(adamKowalskiStay.getPatient());
        stayService.save(adamKowalskiStay);
    }
}
