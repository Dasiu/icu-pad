package com.icupad.hl7_gateway.test_type_module.complete_blood_count.config;

import com.icupad.hl7_gateway.domain.TestMapping;
import com.icupad.hl7_gateway.domain.TestType;
import com.icupad.hl7_gateway.core.service.TestMappingService;
import com.icupad.hl7_gateway.core.service.TestTypeService;
import com.icupad.hl7_gateway.test_type_module.complete_blood_count.domain.CompleteBloodCount;
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
                new TestMapping("Erytrocyty", "Morfologia (krew żylna) - Erytrocyty", "10^6/µl"),
                new TestMapping("Hemoglobina", "Morfologia (krew żylna) - Hemoglobina", "g/dl"),
                new TestMapping("Hematokryt", "Morfologia (krew żylna) - Hematokryt", "%"),
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
                new TestMapping("Erytroblasty %", "Morfologia (krew żylna) - Erytroblasty %", "%"),
                new TestMapping("Erytroblasty #", "Morfologia (krew żylna) - Erytroblasty #", "10^3/µl"),

                new TestMapping("Erytrocyty", "Morfologia (krew włośniczkowa) -  Erytrocyty", "10^6/µl"),
                new TestMapping("Hemoglobina", "Morfologia (krew włośniczkowa) -  Hemoglobina", "g/dl"),
                new TestMapping("Hematokryt", "Morfologia (krew włośniczkowa) -  Hematokryt", "%"),
                new TestMapping("MCV", "Morfologia (krew włośniczkowa) -  M C V", "fl"),
                new TestMapping("MCH", "Morfologia (krew włośniczkowa) -  M C H", "pg"),
                new TestMapping("MCHC", "Morfologia (krew włośniczkowa) -  M C H C", "g/dl"),
                new TestMapping("Płytki", "Morfologia (krew włośniczkowa) -  Płytki", "10^3/ul"),
                new TestMapping("Leukocyty", "Morfologia (krew włośniczkowa) -  Leukocyty", "10^3/ul"),
                new TestMapping("RDW-SD", "Morfologia (krew włośniczkowa) -  RDW-SD", "fl"),
                new TestMapping("RDW-CV", "Morfologia (krew włośniczkowa) -  RDW-CV", "%"),
                new TestMapping("PDW", "Morfologia (krew włośniczkowa) -  P D W", "fl"),
                new TestMapping("MPV", "Morfologia (krew włośniczkowa) -  MPV", "fl"),
                new TestMapping("P-LCR", "Morfologia (krew włośniczkowa) -  P-LCR", "%"),
                new TestMapping("PCT", "Morfologia (krew włośniczkowa) -  P C T", "%"),
                new TestMapping("Erytroblasty %", "Morfologia (krew włośniczkowa) - Erytroblasty %", "%"),
                new TestMapping("Erytroblasty #", "Morfologia (krew włośniczkowa) - Erytroblasty #", "10^3/µl"),

                new TestMapping("Erytrocyty", "Morfologia (krew tętnicza) - Erytrocyty", "10^6/µl"),
                new TestMapping("Hemoglobina", "Morfologia (krew tętnicza) - Hemoglobina", "g/dl"),
                new TestMapping("Hematokryt", "Morfologia (krew tętnicza) - Hematokryt", "%"),
                new TestMapping("MCV", "Morfologia (krew tętnicza) - MCV", "fl"),
                new TestMapping("MCH", "Morfologia (krew tętnicza) - MCH", "pg"),
                new TestMapping("MCHC", "Morfologia (krew tętnicza) - MCHC", "g/dl"),
                new TestMapping("Płytki", "Morfologia (krew tętnicza) - Płytki", "10^3/ul"),
                new TestMapping("Leukocyty", "Morfologia (krew tętnicza) - Leukocyty", "10^3/ul"),
                new TestMapping("Erytroblasty %", "Morfologia (krew tętnicza) - Erytroblasty %", "%"),
                new TestMapping("Erytroblasty #", "Morfologia (krew tętnicza) - Erytroblasty #", "10^3/µl")
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
        CompleteBloodCount testGroup = new CompleteBloodCount();
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
        TestType testTypeWithId = testTypeService.findByName(testType.getName());
        return testTypeWithId == null ? testTypeService.save(testType) : testTypeWithId;
    }
}