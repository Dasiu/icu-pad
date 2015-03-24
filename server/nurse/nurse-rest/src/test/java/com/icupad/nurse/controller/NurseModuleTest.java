package com.icupad.nurse.controller;

import static com.jayway.restassured.RestAssured.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.icupad.nurse.config.NurseApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NurseApplication.class)
@WebAppConfiguration
public class NurseModuleTest {
	
	@Test
	public void shoudStartServer() {
		when().
			get("/nurse/").
		then().
			statusCode(500);
	}

}
