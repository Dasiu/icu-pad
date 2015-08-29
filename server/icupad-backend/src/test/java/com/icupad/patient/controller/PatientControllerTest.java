package com.icupad.patient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icupad.TestResultsApplication;
import com.icupad.patient.domain.Patient;
import com.icupad.patient.domain.Stay;
import com.icupad.patient.dto.PatientDTO;
import com.icupad.patient.service.PatientService;
import com.icupad.patient.service.StayService;
import com.icupad.service.UserService;
import com.icupad.test_data.Patients;
import com.icupad.test_data.Stays;
import com.icupad.test_data.Users;
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

import static com.jayway.restassured.RestAssured.form;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestResultsApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class PatientControllerTest {
    @Value("${local.server.port}")
    int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StayService stayService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        userService.save(Users.admin());
        restAssuredConfig();
    }

    @After
    public void clean() {
        stayService.deleteAll();
        patientService.deleteAll();
        userService.deleteAll();
    }

    @Test
    public void getPatientsActiveShouldReturnPatientWithActiveStay() {
        Stay stay = buildPatientWithActiveStay();

        PatientDTO[] as = given()
                .param("findOnlyActive", true)

                .when()
                .get("/patient")
                .then()

                .statusCode(200)
                .body("$", hasSize(1))
                .body("[0].activeStay.id", is(stay.getId().intValue()))
                .extract().body().as(PatientDTO[].class);

        stay.toString();
    }

    @Test
    public void getPatientsActiveShouldNotReturnPatientWithoutActiveStay() {
        Stay stay = buildPatientWithActiveStay();
        stay.setActive(false);
        stayService.save(stay);

        given()
                .param("findOnlyActive", true)

                .when()
                .get("/patient")
                .then()

                .statusCode(200)
                .body("$", hasSize(0));
    }

    private Stay buildPatientWithActiveStay() {
        Patient patient = Patients.patient();
        patientService.save(patient);

        Stay stay = Stays.stay();
        stay.setActive(true);
        stay.setPatient(patient);

        return stayService.save(stay);
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
