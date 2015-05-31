package com.icupad.test_results.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icupad.test_results.default_test_type.config.Application;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.config.ObjectMapperConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.form;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class TestResultsTest {
    @Value("${local.server.port}")
    int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        restAssuredConfig();
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
