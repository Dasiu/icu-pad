package com.icupad.nurse.provider;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import com.icupad.common.provider.DataProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PatientProvider extends DataProvider {
	
	private static final long[] existingIds = new long[] { 1, 2, 3 };
	private static final Random random = new Random();
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PatientProvider(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public static Long idOfExistingUser() {
		return existingIds[random.nextInt(existingIds.length)];
	}
	
	public void provideTestData() {
		for (long id : existingIds) {
			jdbcTemplate.update("insert into PATIENT (id, hl7Id, pesel, name, surname) "
					+ "values (" + id + ", '"+randomAlphabetic(10)+"', '"+randomNumeric(11)+"', '"+randomAlphabetic(10)+"', '"+randomAlphabetic(10)+"')");
		}
	}

}
