package com.icupad.hl7_gateway.test_type_module.default_test_type.config;

import com.icupad.hl7_gateway.test_type_module.default_test_type.domain.Default;
import com.icupad.hl7_gateway.domain.TestMapping;
import com.icupad.hl7_gateway.domain.TestType;
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
                new TestMapping("P-ciała anty-HBs", "P-ciała anty-HBs", "mIU/mL"),
                new TestMapping("P-ciała anty-HCV", "P-ciała anty-HCV", "S/CO"),
                new TestMapping("Badanie ogólne moczu - Ciężar właściwy", "Badanie ogólne moczu - Ciężar właściwy", "brak"),
                new TestMapping("Badanie ogólne moczu - Urobilinogen", "Badanie ogólne moczu - Urobilinogen", "E.U./dL"),
                new TestMapping("Badanie ogólne moczu - Odczyn(pH)", "Badanie ogólne moczu - Odczyn(pH)", null),
                new TestMapping("Białko C-reaktywne", "Białko C-reaktywne", "mg/dl"),
                new TestMapping("Kreatynina", "Kreatynina", "mg/dl"),
                new TestMapping("Mocznik", "Mocznik", "mg/dl"),
                new TestMapping("Kwas walproinowy", "Kwas walproinowy", "ug/mL"),
                new TestMapping("Troponina I", "Troponina I", "ng/l"),
                new TestMapping("Wankomycyna", "Wankomycyna", "ug/mL"),

                new TestMapping("Badanie płynu z jam ciała-ogólne - Erytrocyty", "Badanie płynu z jam ciała-ogólne - Erytrocyty", "/ µl"),
                new TestMapping("Badanie płynu z jam ciała-ogólne - Leukocyty", "Badanie płynu z jam ciała-ogólne - Leukocyty", "/ µl"),
                new TestMapping("Badanie płynu z jam ciała-ogólne - Białko Całkowite", "Badanie płynu z jam ciała-ogólne - Białko Całkowite", "mg/dl"),
                new TestMapping("Badanie płynu z jam ciała-ogólne - Ciężar Właściwy", "Badanie płynu z jam ciała-ogólne - Ciężar Właściwy", "g/ml"), // FIXME check if it's true
                new TestMapping("Badanie płynu z jam ciała-ogólne - pH", "Badanie płynu z jam ciała-ogólne - pH", null),
                new TestMapping("Badanie płynu z jam ciała-ogólne - Glukoza", "Badanie płynu z jam ciała-ogólne - Glukoza", "mg/dl"),
                new TestMapping("Triglicerydy w płynie z jam ciała", "Triglicerydy w płynie z jam ciała", "mg/dl"),

                new TestMapping("Prokalcytonina", "Prokalcytonina", "ng/ml"),
                new TestMapping("Kinaza kreatynowa", "Kinaza kreatynowa", "U/l"),
                new TestMapping("Triglicerydy", "Triglicerydy", "mg/dl"),
                new TestMapping("Wapń całkowity", "Wapń całkowity", "mmol/l"),
                new TestMapping("Alfa -1 antytrypsyna", "Alfa -1 antytrypsyna", "  g/l"),

                new TestMapping("Trójjododotyronina wolna", "Trójjododotyronina wolna","pg/mL"),
                new TestMapping("Tyroksyna wolna", "Tyroksyna wolna","ng/dL"),
                new TestMapping("Hormon tyreotropowy", "Hormon tyreotropowy","uIU/mL"),
                new TestMapping("OB", "OB","mm/h"),
                new TestMapping("Bilirubina bezpośrednia", "Bilirubina bezpośrednia","mg/dl"),
                new TestMapping("GGTP", "GGTP","IU/l"),
                new TestMapping("Antygen HBs", "Antygen HBs","S/CO"),

                new TestMapping("Amoniak (osocze)", "Amoniak (osocze)","µg/dl"),
                new TestMapping("Fosfataza zasadowa", "Fosfataza zasadowa","IU/l"),
                new TestMapping("Immunoglobulina E", "Immunoglobulina E","KU/L")
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