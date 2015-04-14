package com.icupad.nurse.controller;

import static com.jayway.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icupad.nurse.config.NurseApplication;
import com.icupad.nurse.model.NurseFunction;
import com.jayway.restassured.RestAssured;

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

import java.util.Collection;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NurseApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:12345")
public class NurseModuleTest {
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Value("${server.port}")
    private int port;
	
	@Test
	public void shouldReturnFunctions() {
		@SuppressWarnings("unchecked")
		Collection<NurseFunction> functions =
		when().
			get("/nurse/functions").
			as(Collection.class);
		assertThat(functions).isNotNull();
	}
	
	@Test
	public void functionsNumberShouldBe10() {
		@SuppressWarnings("unchecked")
		Collection<NurseFunction> functions =
		when().
			get("/nurse/functions").
			as(Collection.class);
		assertThat(functions.size()).isEqualTo(10);
	}
	
	@Before
    public void restAssuredConfig() {
		RestAssured.port = port;
    }

}
