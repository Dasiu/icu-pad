package com.icupad.form;

import com.icupad.form.repository.FormTemplateRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = { FormTemplateRepository.class })
@ComponentScan(basePackages = { "com.icupad.form.*" })
@EntityScan(basePackages = { "com.icupad.form.model" })
public class FormBuilderApplication {
}
