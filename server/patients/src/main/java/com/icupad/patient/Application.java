package com.icupad.patient;

import com.icupad.repository.RepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaAuditing
@EnableJpaRepositories(value = {"com.icupad.patient.repository", "com.icupad.repository"},
        repositoryFactoryBeanClass = RepositoryFactoryBean.class)
@ComponentScan(basePackages = {"com.icupad"})
@EntityScan(basePackages = {"com.icupad"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
