package com.icupad.nurse.controller;

import static com.icupad.nurse.provider.PatientProvider.idOfExistingUser;
import static com.jayway.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icupad.common.controller.RestDateFormat;
import com.icupad.nurse.config.NurseApplication;
import com.icupad.nurse.model.*;
import com.icupad.nurse.model.external.Patient;
import com.icupad.nurse.service.*;
import com.jayway.restassured.RestAssured;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NurseApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:12345")
public class NurseModuleTest {
	
	@Autowired
	private ExecutedActivityService executedActivityService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ExecutedActivityTypeService executedActivityTypeService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	private Random random = new Random();
	
	@Value("${server.port}")
    private int port;
	
	@Test
	@SuppressWarnings("unchecked")
	public void shouldReturnFunctions() {
		Collection<NurseFunction> functions =
		when().
			get("/nurse/functions").
			as(Collection.class);
		assertThat(functions).isNotNull();
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void functionsNumberShouldBe10() {
		Collection<NurseFunction> functions =
		when().
			get("/nurse/functions").
			as(Collection.class);
		assertThat(functions.size()).isEqualTo(10);
	}
	
	@Test
	public void shouldReturn200WhenRequestForExistingExecutedActivities() {
		//given
			Patient patient = existingPatinetWithExecutedActivities();
			String today = RestDateFormat.format(LocalDateTime.now());
		when().
			get("/nurse/functions/executed?patient_id={id}&date={date}", patient.getId(), today).
		then().
			statusCode(200);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void shouldReturnExecutedNurseFunctions() {
		//given
			Patient patient = existingPatinetWithExecutedActivities();
			String today = RestDateFormat.format(LocalDateTime.now());
		//when
			Collection<ExecutedNurseFunction> executedFunctions = 
				when().
					get("/nurse/functions/executed?patient_id={id}&date={date}", patient.getId(), today).as(Collection.class);
		// then
			assertThat(executedFunctions).isNotNull();
			assertThat(executedFunctions).isNotEmpty();
	}
	
	private Patient existingPatinetWithExecutedActivities() {
		Patient patient = new Patient();
		patient.setId(idOfExistingUser());
		generateExecutedActivities(patient, LocalDateTime.now(), 5);
		return patient;
	}

	private void generateExecutedActivities(Patient patient, LocalDateTime date, int count) {
		for (int i = 0; i < count; i++) {
			ExecutedActivity executedActivity = new ExecutedActivity();
			executedActivity.setActivity(randomActivity());
			executedActivity.setType(randomExecutedActivityType());
			executedActivity.setPatient(patient);
			executedActivity.setDate(new Date()); // TODO change to LocalDateTime if possible
			executedActivity.setHour((short) (random.nextInt(24) + 1));
			executedActivityService.create(executedActivity);
		}
	}

	private ExecutedActivityType randomExecutedActivityType() {
		List<ExecutedActivityType> types = executedActivityTypeService.findAll();
		return types.get(random.nextInt(types.size()));
	}

	private Activity randomActivity() {
		List<Activity> activities = activityService.findAll();
		return activities.get(random.nextInt(activities.size()));
	}

	@Before
    public void restAssuredConfig() {
		RestAssured.port = port;
    }

}
