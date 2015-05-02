package com.icupad.test_results.config;

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
@EnableJpaRepositories(value = {"com.icupad.test_results.repository", "com.icupad.repository"},
        repositoryFactoryBeanClass = RepositoryFactoryBean.class)
@ComponentScan(basePackages = {"com.icupad.security", "com.icupad.service", "com.icupad.repository",
        "com.icupad.test_results"})
@EntityScan(basePackages = {"com.icupad.security", "com.icupad.domain", "com.icupad.test_results.model"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
