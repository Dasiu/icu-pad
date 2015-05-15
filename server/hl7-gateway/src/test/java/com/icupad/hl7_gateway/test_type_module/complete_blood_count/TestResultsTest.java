package com.icupad.hl7_gateway.test_type_module.complete_blood_count;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import com.icupad.hl7_gateway.core.Application;
import com.icupad.hl7_gateway.core.domain.Stay;
import com.icupad.hl7_gateway.core.service.PatientService;
import com.icupad.hl7_gateway.core.service.StayService;
import com.icupad.hl7_gateway.core.service.hl7_server.handler.MessageHandler;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.BloodSource;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.TestPanelResult;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.TestResult;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.service.TestPanelResultService;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.service.TestResultService;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.service.TestService;
import com.icupad.hl7_gateway.test_data.HL7Messages;
import com.icupad.hl7_gateway.test_data.Stays;
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
    private MessageHandler<ORU_R01> testResultsHandler;

    @Autowired
    private TestService testService;

    @Autowired
    private TestPanelResultService testPanelResultService;

    @Test
    public void shouldAssignTestResultToPDWTest() throws HL7Exception {
        createAndSaveAdamKowalskisStay();
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.completeBloodCountPDWResult);

        testResultsHandler.handle(oru_r01);

        assertThatTestResultIsAssociatedWithTest(getTestResult());
    }

    @Test
    public void shouldCreateTestPanelResult() throws HL7Exception {
        createAndSaveAdamKowalskisStay();
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.completeBloodCountPDWResult);

        testResultsHandler.handle(oru_r01);

        assertEquals(1, testPanelResultService.count());
    }

    @Test
    public void shouldExtractBloodSource() throws HL7Exception {
        createAndSaveAdamKowalskisStay();
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.completeBloodCountPDWResult);

        testResultsHandler.handle(oru_r01);

        TestPanelResult testPanelResult = testPanelResultService.findAll().stream().findFirst().get();
        assertNotNull(testPanelResult);
        assertEquals(BloodSource.VEIN, testPanelResult.getBloodSource());
    }

    private TestResult getTestResult() {
        List<TestResult> testResults = testResultsService.findAll();
        assertEquals(1, testResults.size());
        return testResults.get(0);
    }

    private void assertThatTestResultIsAssociatedWithTest(TestResult testResult) {
        assertNotNull(testResult);
        assertNotNull(testResult.getTestRequest());
        assertNotNull(testResult.getTestRequest().getTest());
        assertEquals(testService.findByNameAndUnit("PDW", "fl").getId(), testResult.getTestRequest().getTest().getId());
    }

    private void createAndSaveAdamKowalskisStay() {
        Stay adamKowalskiStay = Stays.createAdamKowalskiStay();
        patientService.save(adamKowalskiStay.getPatient());
        stayService.save(adamKowalskiStay);
    }
}
