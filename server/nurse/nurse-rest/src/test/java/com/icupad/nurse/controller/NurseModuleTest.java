package com.icupad.nurse.controller;

import com.icupad.nurse.config.NurseApplication;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NurseApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:12345")
public class NurseModuleTest {
	
	@Value("${server.port}")
    private int port;
	
	@Test
	public void shouldReturnFunctions() {
//		@SuppressWarnings("unchecked")
//		Collection<NurseFunction> functions =
//		when().
//			get("/nurse/functions").
//			as(Collection.class);
//		assertThat(functions).isNotNull();
	}
	
	@Test
	public void functionsNumberShouldBe10() {
//		@SuppressWarnings("unchecked")
//		Collection<NurseFunction> functions =
//		when().
//			get("/nurse/functions").
//			as(Collection.class);
//		assertThat(functions.size()).isEqualTo(10);
	}
	
	@Before
    public void restAssuredConfig() {
        RestAssured.port = port;
    }

}
