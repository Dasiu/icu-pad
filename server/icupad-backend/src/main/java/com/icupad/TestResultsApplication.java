package com.icupad;

import com.icupad.repository.RepositoryFactoryBean;
import org.apache.catalina.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;

@Configuration
@EnableAutoConfiguration
@EnableJpaAuditing
@EnableJpaRepositories(value = {"com.icupad"},
        repositoryFactoryBeanClass = RepositoryFactoryBean.class)
@ComponentScan(basePackages = {"com.icupad"})
@EntityScan(basePackages = {"com.icupad"})
public class TestResultsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestResultsApplication.class, args);
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setTomcatContextCustomizers(Collections.singletonList(context -> context.setUseHttpOnly(false)));
        return factory;
    }

}
