package com.icupad.hl7_gateway.config;

import com.icupad.hl7_gateway.domain.RawTestName;
import com.icupad.hl7_gateway.domain.Test;
import com.icupad.hl7_gateway.domain.TestGroup;
import com.icupad.hl7_gateway.service.RawTestNameService;
import com.icupad.hl7_gateway.service.TestGroupService;
import com.icupad.hl7_gateway.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProductionSeed {
    @Autowired
    private TestGroupService testGroupService;
    @Autowired
    private TestService testService;
    @Autowired
    private RawTestNameService rawTestNameService;

    @PostConstruct
    public void init() {
        testGroups();
        tests();
        rawTestNames();
    }

    private void rawTestNames() {
        rawTestNameService.saveIfNotExists(createRawTestName("Gazometria (krew żylna) - Be-Ecf",
                testService.findByName("Be-Ecf")));
        rawTestNameService.saveIfNotExists(createRawTestName("Morfologia (krew żylna) - Hematokryt",
                testService.findByName("Hematokryt")));
        rawTestNameService.saveIfNotExists(createRawTestName("Morfologia (krew żylna) - Erytrocyty",
                testService.findByName("Erytrocyty")));
    }

    private RawTestName createRawTestName(String rawName, Test test) {
        RawTestName rawTestName = new RawTestName();
        rawTestName.setRawName(rawName);
        rawTestName.setTest(test);
        return rawTestName;
    }

    private void tests() {
        saveIfNotExists(createTest("Be-Ecf", "mmol/L", testGroupService.findByName("Gazometria")));
        saveIfNotExists(createTest("Hematokryt", "%", testGroupService.findByName("Morfologia")));
        saveIfNotExists(createTest("Erytrocyty", "10^6/µl", testGroupService.findByName("Morfologia")));
    }

    private void saveIfNotExists(Test test) {
        if (testService.findByName(test.getName()) == null) {
            testService.save(test);
        }
    }

    private Test createTest(String name, String unit, TestGroup testGroup) {
        Test test = new Test();
        test.setName(name);
        test.setUnit(unit);
        test.setTestGroup(testGroup);
        return test;
    }

    public void testGroups() {
        saveIfNotExists(createTestGroup("Gazometria"));
        saveIfNotExists(createTestGroup("Morfologia"));
        saveIfNotExists(createTestGroup("Pozostałe wyniki"));
    }

    private void saveIfNotExists(TestGroup testGroup) {
        if (testGroupService.findByName(testGroup.getName()) == null) {
            testGroupService.save(testGroup);
        }
    }

    private TestGroup createTestGroup(String name) {
        TestGroup testGroup = new TestGroup();
        testGroup.setName(name);
        return testGroup;
    }
}