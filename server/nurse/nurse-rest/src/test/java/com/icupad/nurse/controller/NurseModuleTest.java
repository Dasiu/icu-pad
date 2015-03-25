package com.icupad.nurse.controller;

import com.icupad.nurse.config.NurseApplication;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NurseApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class NurseModuleTest {
    @Value("${local.server.port}")
    int port;

    @Before
    public void setup() {
        restAssuredConfig();
    }

    @Test
    public void shouldStartServer() {
        when().
                get("/nurse/").
        then().
                statusCode(401);
    }

    private void restAssuredConfig() {
        RestAssured.port = port;
    }
}
