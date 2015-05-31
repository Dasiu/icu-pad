package com.icupad.hl7_gateway.test_type_module.blood_gas.config;

import com.icupad.hl7_gateway.test_type_module.blood_gas.domain.BloodGas;
import com.icupad.hl7_gateway.core.domain.TestMapping;
import com.icupad.hl7_gateway.core.domain.TestType;
import com.icupad.hl7_gateway.core.service.TestMappingService;
import com.icupad.hl7_gateway.core.service.TestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Component
@Transactional
public class BloodGasProductionSeed {
    private final List<TestMapping> testMappings;
    private final TestTypeService testTypeService;
    private final TestMappingService testMappingService;

    @Autowired
    public BloodGasProductionSeed(TestTypeService testTypeService,
                                  TestMappingService testMappingService) {
        this.testTypeService = testTypeService;
        this.testMappingService = testMappingService;

        testMappings = Arrays.asList(
                new TestMapping("pH", "Gazometria (krew tętnicza) - pH", ""),
                new TestMapping("pCO2", "Gazometria (krew tętnicza) - pCO2", "mmHg"),
                new TestMapping("pO2", "Gazometria (krew tętnicza) - pO2", "mmHg"),
                new TestMapping("SBC", "Gazometria (krew tętnicza) - SBC", "mmol/L"),
                new TestMapping("HCO3a", "Gazometria (krew tętnicza) - HCO3a", "mmol/L"),
                new TestMapping("tCO2", "Gazometria (krew tętnicza) - tCO2", "mmol/L"),
                new TestMapping("%SAT", "Gazometria (krew tętnicza) - %Sat", "%"),
                new TestMapping("BE-B", "Gazometria (krew tętnicza) - BE-B", "mmol/L"),
                new TestMapping("BE-ECF", "Gazometria (krew tętnicza) - BE-ECF", "mmol/L"),
                new TestMapping("pH", "Gazometria (krew żylna) - pH", ""),
                new TestMapping("pCO2", "Gazometria (krew żylna) - pCO2", "mmHg"),
                new TestMapping("pO2", "Gazometria (krew żylna) - pO2", "mmHg"),
                new TestMapping("SBC", "Gazometria (krew żylna) - SBC", "mmol/L"),
                new TestMapping("HCO3a", "Gazometria (krew żylna) - Hco3a", "mmol/L"),
                new TestMapping("tCO2", "Gazometria (krew żylna) - tCO2", "mmol/L"),
                new TestMapping("%SAT", "Gazometria (krew żylna) - SAT", "%"),
                new TestMapping("BE-B", "Gazometria (krew żylna) - BE-B", "mmol/L"),
                new TestMapping("BE-ECF", "Gazometria (krew żylna) - Be-Ecf", "mmol/L"),
                new TestMapping("pH", "Gazometria (krew włośniczkowa) - pH", ""),
                new TestMapping("pCO2", "Gazometria (krew włośniczkowa) - pCO2", "mmHg"),
                new TestMapping("pO2", "Gazometria (krew włośniczkowa) - pO2", "mmHg"),
                new TestMapping("SBC", "Gazometria (krew włośniczkowa) - SBC", "mmol/L"),
                new TestMapping("HCO3a", "Gazometria (krew włośniczkowa) - HCO3a", "mmol/L"),
                new TestMapping("tCO2", "Gazometria (krew włośniczkowa) - tCO2", "mmol/L"),
                new TestMapping("%SAT", "Gazometria (krew włośniczkowa) - Sat", "%"),
                new TestMapping("BE-B", "Gazometria (krew włośniczkowa) - BE-B", "mmol/L"),
                new TestMapping("BE-ECF", "Gazometria (krew włośniczkowa) - Be-ECF", "mmol/L"),
                new TestMapping("FO2Hb", "Gazometria (krew włośniczkowa) - FO2Hb", "%"),
                new TestMapping("FCOHb", "Gazometria (krew włośniczkowa) - FCOHb", "%"),
                new TestMapping("FMetHb", "Gazometria (krew włośniczkowa) - FMetHb", "%"),
                new TestMapping("FHHb", "Gazometria (krew włośniczkowa) - FHHb", "%"),
                new TestMapping("Hb", "Gazometria (krew włośniczkowa) - Hb", "g/dL"),
                new TestMapping("HCT", "Gazometria (krew włośniczkowa) - Hct", "%"),
                new TestMapping("FO2Hb", "Gazometria (krew tętnicza) - FO2Hb", "%"),
                new TestMapping("FCOHb", "Gazometria (krew tętnicza) - FCOHb", "%"),
                new TestMapping("FMetHb", "Gazometria (krew tętnicza) - FMetHb", "%"),
                new TestMapping("Hb", "Gazometria (krew tętnicza) - Hb", "g/dL"),
                new TestMapping("HCT", "Gazometria (krew tętnicza) - HCT", "%")
        );
    }

    @PostConstruct
    public void init() {
        TestType completeBloodTestCount = testGroup();
        rawTestNames(completeBloodTestCount);
    }

    private TestType testGroup() {
        BloodGas testGroup = new BloodGas();
        testGroup.setName("Gazometria");
        return saveIfNotExists(testGroup);
    }

    private void rawTestNames(TestType completeBloodTestCount) {
        testMappings.stream()
                .map(rawTestName -> setTestGroup(rawTestName, completeBloodTestCount))
                .forEach(testMappingService::saveIfNotExistsByRawTestName);
    }

    private TestMapping setTestGroup(TestMapping testMapping, TestType testType) {
        testMapping.setTestType(testType);
        return testMapping;
    }

    private TestType saveIfNotExists(TestType testType) {
        return testTypeService.findByName(testType.getName()) == null ? testTypeService.save(testType) : testType;
    }
}