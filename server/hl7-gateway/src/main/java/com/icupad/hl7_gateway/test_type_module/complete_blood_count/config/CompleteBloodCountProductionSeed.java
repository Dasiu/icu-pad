package com.icupad.hl7_gateway.test_type_module.complete_blood_count.config;

import com.icupad.hl7_gateway.core.domain.TestMapping;
import com.icupad.hl7_gateway.core.domain.TestType;
import com.icupad.hl7_gateway.core.service.TestMappingService;
import com.icupad.hl7_gateway.core.service.TestTypeService;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.CompleteBloodTestCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Component
@Transactional
public class CompleteBloodCountProductionSeed {
    private final List<TestMapping> testMappings;
    private final TestTypeService testTypeService;
    private final TestMappingService testMappingService;

    @Autowired
    public CompleteBloodCountProductionSeed(TestTypeService testTypeService,
                                            TestMappingService testMappingService) {
        this.testTypeService = testTypeService;
        this.testMappingService = testMappingService;

        testMappings = Arrays.asList(
                new TestMapping("Hematokryt", "Morfologia (krew żylna) - Hematokryt", "%"),
                new TestMapping("Erytrocyty", "Morfologia (krew żylna) - Erytrocyty", "10^6/µl"),
                new TestMapping("Hemoglobina", "Morfologia (krew żylna) - Hemoglobina", "g/dl"),
                new TestMapping("MCV", "Morfologia (krew żylna) - MCV", "fl"),
                new TestMapping("MCH", "Morfologia (krew żylna) - MCH", "pg"),
                new TestMapping("MCHC", "Morfologia (krew żylna) - MCHC", "g/dl"),
                new TestMapping("Płytki", "Morfologia (krew żylna) - Płytki", "10^3/ul"),
                new TestMapping("Leukocyty", "Morfologia (krew żylna) - Leukocyty", "10^3/ul"),
                new TestMapping("RDW-SD", "Morfologia (krew żylna) - RDW-SD", "fl"),
                new TestMapping("RDW-CV", "Morfologia (krew żylna) - RDW-CV", "%"),
                new TestMapping("PDW", "Morfologia (krew żylna) - PDW", "fl"),
                new TestMapping("MPV", "Morfologia (krew żylna) - MPV", "fl"),
                new TestMapping("P-LCR", "Morfologia (krew żylna) - P-LCR", "%"),
                new TestMapping("PCT", "Morfologia (krew żylna) - PCT", "%"),
                new TestMapping("pH", "Gazometria (krew tętnicza) - pH", "brak")
        );
    }

    @PostConstruct
    public void init() {
        TestType completeBloodTestCount = testGroup();
        testMappings(completeBloodTestCount);
        tests();
    }

    private void tests() {
        testMappings.forEach(testMappingService::saveIfNotExistsByRawTestName);
    }

    private TestType testGroup() {
        CompleteBloodTestCount testGroup = new CompleteBloodTestCount();
        testGroup.setName("Morfologia");
        return saveIfNotExists(testGroup);
    }

    private void testMappings(TestType completeBloodTestCount) {
        testMappings.stream()
                .map(testMapping -> setTestGroup(testMapping, completeBloodTestCount))
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