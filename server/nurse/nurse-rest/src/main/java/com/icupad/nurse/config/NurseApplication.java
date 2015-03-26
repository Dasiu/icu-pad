package com.icupad.nurse.config;

import com.icupad.nurse.repository.NurseFunctionRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = { NurseFunctionRepository.class })
@ComponentScan(basePackages = { "com.icupad.nurse.*" })
@EntityScan(basePackages = "com.icupad.nurse.model")
public class NurseApplication {

	public static void main(String[] args) {
		SpringApplication.run(NurseApplication.class, args);
	}

}
