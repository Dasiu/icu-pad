package com.icupad.nurse.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.icupad.nurse.controller" })
public class NurseApplication {

	public static void main(String[] args) {
		SpringApplication.run(NurseApplication.class, args);
	}

}
