package com.icupad.nurse.test;

import com.google.common.collect.Sets;
import com.icupad.TestResultsApplication;
import com.icupad.nurse.model.Konczyna;
import com.icupad.nurse.model.NurseDiagnosis;
import com.icupad.nurse.model.StanSwiadomosci;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestResultsApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class NurseModuleTest {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void shouldReturn200WhenRequestForNurseDiagnosisData() {
        when().
            get("/nurse/diagnosis").
        then().
            statusCode(200);
    }

    @Test
    public void shouldReturn204WhenPostValidNurseDiagnosis() {
        given().
            contentType(ContentType.JSON).
            body(nurseDiagnosis()).
        when().
            post("/nurse/form").
        then().
            statusCode(204);
    }

    private NurseDiagnosis nurseDiagnosis() {
        return NurseDiagnosis.builder()
                .odruchBabinskiego(Sets.newHashSet(Konczyna.LEWA_KONCZYNA_DOLNA))
                .otwieranieOczu(1)
                .reakcjaRuchowa(2)
                .reaktywnoscKonczynyDolne(true)
                .reaktywnoscKonczynyGorne(false)
                .stanSwiadomosci(StanSwiadomosci.PRZYTOMNY)
                .zwiotczenie(false)
            .build();
    }

    // TODO

    @Before
    public void restAssuredConfig() {
        RestAssured.port = port;
    }

}
