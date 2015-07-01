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
                new TestMapping("Wapń zjonizowany(krew tętnicza)", "Wapń zjonizowany(krew tętnicza) - Wapń zjonizowany", "mmol/L"),
                new TestMapping("Mleczany(krew tętnicza)", "Mleczany(krew tętnicza)", "mmol/L"),
                new TestMapping("Sód (krew tętnicza)", "Sód (krew tętnicza)", "mmol/L"),
                new TestMapping("Potas (krew tętnicza)", "Potas (krew tętnicza)", "mmol/L"),
                new TestMapping("Glukoza (krew tętnicza)", "Glukoza (krew tętnicza)", "mg/dL"),
                new TestMapping("Antytrombina", "Antytrombina", "%"),
                new TestMapping("Fibrynogen", "Fibrynogen", "mg/dL"),
                new TestMapping("Wskaźnik protrombinowy", "Wskaźnik protrombinowy,INR - Wskaźnik protrombinowy", "%"),
                new TestMapping("INR", "Wskaźnik protrombinowy,INR - INR", "INR"),
                new TestMapping("Czas protrombinowy", "Wskaźnik protrombinowy,INR - Czas protrombinowy", "s"),
                new TestMapping("Czas kaolinowo-kefalinowy", "Czas kaolinowo-kefalinowy", "s"),
                new TestMapping("D-dimer", "D-dimer", "mg/L"),
                new TestMapping("Białko całkowite", "Białko całkowite", "g/dl"),
                new TestMapping("AlAT", "AlAT", "IU/l"),
                new TestMapping("AspAt", "AspAt", "IU/l"),
                new TestMapping("Magnez", "Magnez", "mg/l"),
                new TestMapping("Immunoglobulina G", "Immunoglobulina G", "mg/dl"),
                new TestMapping("Immunoglobulina A", "Immunoglobulina A", "mg/dl"),
                new TestMapping("Immunoglobulina M", "Immunoglobulina M", "mg/dl"),
                new TestMapping("Albumina", "Albumina", "mg/dl"),
                new TestMapping("Fosforany", "Fosforany", "mg/dl"),
                new TestMapping("Bilirubina całkowita", "Bilirubina całkowita", "mg/dl"),
                new TestMapping("Glukoza", "Glukoza", "mg/dl"),
                new TestMapping("P-ciała anty-HBs", "P-ciała anty-HBs", "mIU/mL"),
                new TestMapping("P-ciała anty-HCV", "P-ciała anty-HCV", "S/CO"),
                new TestMapping("Badanie ogólne moczu - Ciężar właściwy", "Badanie ogólne moczu - Ciężar właściwy", "brak"),
                new TestMapping("Badanie ogólne moczu - Urobilinogen", "Badanie ogólne moczu - Urobilinogen", "E.U./dL"),
                new TestMapping("Badanie ogólne moczu - Odczyn(pH)", "Badanie ogólne moczu - Odczyn(pH)", ""),
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
        TestType testTypeWithId = testTypeService.findByName(testType.getName());
        return testTypeWithId == null ? testTypeService.save(testType) : testTypeWithId;
    }
}