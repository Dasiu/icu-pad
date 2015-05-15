package com.icupad.hl7_gateway.test_type_module.default_test_type.config;

import com.icupad.hl7_gateway.test_type_module.default_test_type.domain.Default;
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
public class DefaultProductionSeed {
    private final List<TestMapping> testMappings;
    private final TestTypeService testTypeService;
    private final TestMappingService testMappingService;

    @Autowired
    public DefaultProductionSeed(TestTypeService testTypeService, TestMappingService testMappingService) {
        this.testTypeService = testTypeService;
        this.testMappingService = testMappingService;

        testMappings = Arrays.asList(
                new TestMapping("Białko C-reaktywne", "Białko C-reaktywne", "mg/dl"),
                new TestMapping("Kreatynina", "Kreatynina", "mg/dl"),
                new TestMapping("Mocznik", "Mocznik", "mg/dl"),
                new TestMapping("Sód", "Sód", "mmol/L"),
                new TestMapping("Potas", "Potas", "mmol/L"),
                new TestMapping("Wapń  zjonizowany", "Wapń zjonizowany - Wapń  zjonizowany", "mmol/L")
        );
    }

    @PostConstruct
    public void init() {
        TestType other = testType();
        testMappings(other);
    }

    private TestType testType() {
        Default testType = new Default();
        testType.setName("Pozostałe wyniki");
        return saveIfNotExists(testType);
    }

    private void testMappings(TestType testType) {
        testMappings.stream()
                .map(testMapping -> setTestType(testMapping, testType))
                .forEach(testMappingService::saveIfNotExistsByRawTestName);
    }

    private TestMapping setTestType(TestMapping testMapping, TestType testType) {
        testMapping.setTestType(testType);
        return testMapping;
    }

    private TestType saveIfNotExists(TestType testType) {
        return testTypeService.findByName(testType.getName()) == null ? testTypeService.save(testType) : testType;
    }
}