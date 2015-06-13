package com.icupad.hl7_gateway.test_type_module.blood_gas;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import com.icupad.hl7_gateway.core.Application;
import com.icupad.hl7_gateway.core.domain.TestMapping;
import com.icupad.hl7_gateway.core.service.TestMappingService;
import com.icupad.hl7_gateway.core.service.TestTypeService;
import com.icupad.hl7_gateway.core.service.hl7_server.handler.MessageHandler;
import com.icupad.hl7_gateway.test_data.HL7Messages;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.BloodSource;
import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.TestPanelResult;
import com.icupad.hl7_gateway.test_type_module.blood_gas.service.TestPanelResultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@Transactional
public class TestResultsTest {
    @Autowired
    private HapiContext hapiContext;

    @Autowired
    private MessageHandler<ORU_R01> testResultsHandler;

    @Autowired
    private TestPanelResultService testPanelResultService;

    @Autowired
    private TestTypeService testTypeService;

    @Autowired
    private TestMappingService testMappingService;

    @Test
    public void shouldSetUnknownBloodSourceIfSuchSituationHappen() throws HL7Exception {
        createAndSaveTestMappingForUnknownBloodSource("Gazometria (krew żylna z uda) - pH");
        ORU_R01 oru_r01 =
                (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.bloodGasUnknownBloodSource);

        testResultsHandler.handle(oru_r01);

        TestPanelResult testPanelResult = testPanelResultService.findAll().stream().findFirst().get();
        assertNotNull(testPanelResult);
        assertEquals(BloodSource.UNKNOWN, testPanelResult.getBloodSource());
    }

    @Test
    public void shouldSetArteryAsDefaultBloodSourceIfItIsMissing() throws HL7Exception {
        ORU_R01 oru_r01 =
                (ORU_R01) hapiContext.getGenericParser().parse(HL7Messages.bloodGasMissingBloodSource);

        testResultsHandler.handle(oru_r01);

        TestPanelResult testPanelResult = testPanelResultService.findAll().stream().findFirst().get();
        assertNotNull(testPanelResult);
        assertEquals(BloodSource.ARTERY, testPanelResult.getBloodSource());
    }

    private void createAndSaveTestMappingForUnknownBloodSource(String rawTestName) {
        TestMapping testMapping = new TestMapping();
        testMapping.setRawTestName(rawTestName);
        testMapping.setTestName("ph");
        testMapping.setUnit("");
        testMapping.setTestType(testTypeService.findByName("Gazometria"));

        testMappingService.save(testMapping);
    }
}
