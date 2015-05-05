package com.icupad.hl7_gateway.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import com.icupad.hl7_gateway.Application;
import com.icupad.hl7_gateway.domain.Stay;
import com.icupad.hl7_gateway.domain.TestResult;
import com.icupad.hl7_gateway.service.PatientService;
import com.icupad.hl7_gateway.service.StayService;
import com.icupad.hl7_gateway.service.TestResultService;
import com.icupad.hl7_gateway.service.TestService;
import com.icupad.hl7_gateway.service.hl7_server.MissingTestNameMappingException;
import com.icupad.test_data.HL7Messages;
import com.icupad.test_data.Stays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@Transactional
public class TestResultsTest {
    @Autowired
    private StayService stayService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private HapiContext hapiContext;

    @Autowired
    private TestResultService testResultsService;

    @Autowired
    private TestService testService;

    @Autowired
    private TestResultsHandler testResultsHandler;

    private com.icupad.hl7_gateway.domain.Test beEcfResult;

    @Before
    public void before() throws HL7Exception {
        beEcfResult = testService.findByName("Be-Ecf");
    }

    @Test
    public void shouldAssignTestResultToBeEcfTest() throws HL7Exception {
        createAndSaveAdamKowalskisStay();
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.bloodGasBeEcfResult);

        testResultsHandler.handle(oru_r01);

        assertThatTestResultIsAssociatedWithTestGroup(getTestResult());
    }

    @Test(expected = MissingTestNameMappingException.class)
    public void shouldDetectSituationWhenTestNameMappingIsMissing() throws HL7Exception {
        createAndSaveAdamKowalskisStay();
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.messageWithUnknownTest);

        testResultsHandler.handle(oru_r01);

        assertThatTestResultIsAssociatedWithTestGroup(getTestResult());
    }

    @Test
    public void shouldExtractBloodSource() {
        fail("df");
    }

    private TestResult getTestResult() {
        List<TestResult> testResults = testResultsService.findAll();
        assertEquals(1, testResults.size());
        return testResults.get(0);
    }

    private void assertThatTestResultIsAssociatedWithTestGroup(TestResult testResult) {
        assertNotNull(testResult);
        assertNotNull(testResult.getTestRequest());
        assertNotNull(testResult.getTestRequest().getTest());
        assertEquals(beEcfResult.getId(), testResult.getTestRequest().getTest().getId());
    }

    private void createAndSaveAdamKowalskisStay() {
        Stay adamKowalskiStay = Stays.createAdamKowalskiStay();
        patientService.save(adamKowalskiStay.getPatient());
        stayService.save(adamKowalskiStay);
    }
}
