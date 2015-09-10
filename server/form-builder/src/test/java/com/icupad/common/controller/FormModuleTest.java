package com.icupad.common.controller;

import com.icupad.form.FormBuilderApplication;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.config.ObjectMapperConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.form;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FormBuilderApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class FormModuleTest {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void shouldReturn200WhenRequestForExistingClassDescription() {
        when().
            get("/model/{module}/{modelName}", "test_module", "TestModel").
        then().
            statusCode(200);
    }

    // TODO

    @Before
    public void restAssuredConfig() {
        RestAssured.port = port;
    }

}
