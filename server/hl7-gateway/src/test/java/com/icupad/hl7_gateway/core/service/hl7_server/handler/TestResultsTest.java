package com.icupad.hl7_gateway.core.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import com.icupad.hl7_gateway.Application;
import com.icupad.hl7_gateway.core.domain.Stay;
import com.icupad.hl7_gateway.core.service.PatientService;
import com.icupad.hl7_gateway.core.service.StayService;
import com.icupad.hl7_gateway.core.service.hl7_server.MissingTestMappingException;
import com.icupad.hl7_gateway.test_type_module.default_test_type.service.TestResultService;
import com.icupad.hl7_gateway.test_data.HL7Messages;
import com.icupad.hl7_gateway.test_data.Stays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

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
    private MessageHandler<ORU_R01> testResultsHandler;

    @Autowired
    private TestResultService testResultService;

    @Test(expected = MissingTestMappingException.class)
    public void shouldDetectSituationWhenTestMappingIsMissing() throws HL7Exception {
        createAndSaveAdamKowalskisStay();
        ORU_R01 oru_r01 = (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.messageWithUnknownTest);

        testResultsHandler.handle(oru_r01);
    }

    @Test
    public void shouldHandleUnitWithCaretSign() throws HL7Exception {
        createAndSaveAdamKowalskisStay();
        ORU_R01 oru_r01 =
                (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.messageWithUnitContainingCaretChar);

        testResultsHandler.handle(oru_r01);

        assertEquals("10^3/ul", getParsedUnit());
    }

    private String getParsedUnit() {
        return testResultService.findAll().stream()
                .findFirst()
                .get()
                .getUnit();
    }

    private void createAndSaveAdamKowalskisStay() {
        Stay adamKowalskiStay = Stays.createAdamKowalskiStay();
        patientService.save(adamKowalskiStay.getPatient());
        stayService.save(adamKowalskiStay);
    }
}
