package com.icupad.hl7_gateway;

import com.icupad.hl7_gateway.repository.RepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.icupad")
@EnableJpaAuditing
@EnableJpaRepositories(value = {"com.icupad"}, repositoryFactoryBeanClass = RepositoryFactoryBean.class)
@EntityScan(basePackages = "com.icupad")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}