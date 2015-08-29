package com.icupad.test_results.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icupad.patient.domain.Patient;
import com.icupad.patient.domain.Stay;
import com.icupad.patient.service.PatientService;
import com.icupad.patient.service.StayService;
import com.icupad.service.UserService;
import com.icupad.test_data.Patients;
import com.icupad.test_data.Stays;
import com.icupad.test_data.Users;
import com.icupad.test_results.blood_gas.config.Application;
import com.icupad.test_results.blood_gas.domain.TestPanelResult;
import com.icupad.test_results.blood_gas.domain.TestRequest;
import com.icupad.test_results.blood_gas.domain.TestResult;
import com.icupad.test_results.blood_gas.service.TestPanelResultService;
import com.icupad.test_results.blood_gas.service.TestRequestService;
import com.icupad.test_results.blood_gas.service.TestResultService;
import com.icupad.test_results.blood_gas.service.TestService;
import com.icupad.test_results.test_date.blood_gas.TestPanelResults;
import com.icupad.test_results.test_date.blood_gas.TestRequests;
import com.icupad.test_results.test_date.blood_gas.TestResults;
import com.icupad.test_results.test_date.blood_gas.Tests;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.config.ObjectMapperConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;

import static com.jayway.restassured.RestAssured.form;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class TestPanelResultTest {
    @Value("${local.server.port}")
    int port;

    @Autowired
    private TestResultService testResultService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PatientService patientService;

    @Autowired
    private StayService stayService;

    @Autowired
    private TestRequestService testRequestService;

    @Autowired
    private TestService testService;

    @Autowired
    private TestPanelResultService testPanelResultService;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        userService.save(Users.admin());
        restAssuredConfig();
    }

    @After
    public void clean() {
        deleteEntityGraph();
        userService.deleteAll();
    }

    @Test
    public void getTestPanelResultShouldReturnResultsForGivenPatientStay() {
        TestResult testResult = buildEntityGraph();
        Long otherStayId = testResult.getStay().getId() + 1;

        given()
                .pathParam("stayId", otherStayId)

                .when()
                .get("/stay/{stayId}/test-panel-result")
                .then()

                .statusCode(200)
                .body("$", hasSize(0));
    }

    @Test
    public void getTestPanelResultShouldReturnTestPanelResultsBetweenGivenDates() {
        TestResult testResult = buildEntityGraph();
        setTestPanelResultRequestDate(testResult, "9000-10-10T10:10:10");

        given()
                .pathParam("stayId", testResult.getStay().getId())
                .param("fromRequestDate", "1000-06-20T10:20:30")
                .param("toRequestDate", "9999-06-21T10:20:31")

                .when()
                .get("/stay/{stayId}/test-panel-result")
                .then()

                .statusCode(200)
                .body("$", hasSize(1))
                .body("id", hasItem(testResult.getTestRequest().getTestPanelResult().getId().intValue()));
    }

    @Test
    public void getTestPanelResultShouldFilterOutTestPanelResultsOutsideGivenDates() {
        TestResult testResult = buildEntityGraph();
        setTestPanelResultRequestDate(testResult, "9000-10-10T10:10:10");

        given()
                .pathParam("stayId", testResult.getStay().getId())
                .param("fromRequestDate", "1000-06-20T10:20:30")
                .param("toRequestDate", "2000-06-21T10:20:31")

                .when()
                .get("/stay/{stayId}/test-panel-result")

                .then()
                .statusCode(200)
                .body("$", hasSize(0));
    }

    private void deleteEntityGraph() {
        testResultService.deleteAll();
        testRequestService.deleteAll();
        testPanelResultService.deleteAll();
        testService.deleteAll();
        stayService.deleteAll();
        patientService.deleteAll();
    }

    private void setTestPanelResultRequestDate(TestResult testResult, String dateTimeStr) {
        TestPanelResult testPanelResult = testResult.getTestRequest().getTestPanelResult();
        testPanelResult.setRequestDate(LocalDateTime.parse(dateTimeStr));
        testPanelResultService.save(testPanelResult);
    }

    private TestResult buildEntityGraph() {
        Patient patient = patientService.save(Patients.patient());
        Stay stay = Stays.stay();
        stay.setPatient(patient);
        stay = stayService.save(stay);

        com.icupad.test_results.blood_gas.domain.Test test = Tests.test();
        test = testService.save(test);

        TestPanelResult testPanelResult = TestPanelResults.testPanelResult();
        testPanelResult = testPanelResultService.save(testPanelResult);

        TestRequest testRequest = TestRequests.testRequest();
        testRequest.setTest(test);
        testRequest.setTestPanelResult(testPanelResult);
        testRequest = testRequestService.save(testRequest);

        TestResult testResult = TestResults.testResult();
        testResult.setStay(stay);
        testResult.setTestRequest(testRequest);
        testResultService.save(testResult);

        return testResult;
    }

    private void restAssuredConfig() {
        RestAssured.port = port;
        RestAssured.authentication = form("admin", "admin", new FormAuthConfig("/login", "username", "password"));
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        (cls, charset) -> objectMapper
                ));
    }
}
