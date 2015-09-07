package com.icupad.nurse.config;

import com.icupad.nurse.repository.NurseFunctionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories(basePackageClasses = { NurseFunctionRepository.class })
@ComponentScan(basePackages = { "com.icupad.nurse.*", "com.icupad.common.*" })
@EntityScan(basePackages = { "com.icupad.nurse.model", "com.icupad.nurse.diagnosis.model", "com.icupad.domain " })
public class NurseApplication {

	public static void main(String[] args) {
		SpringApplication.run(NurseApplication.class, args);
	}

}
