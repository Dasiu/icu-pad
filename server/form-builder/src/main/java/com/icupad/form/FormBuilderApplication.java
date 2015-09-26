package com.icupad.form;

import com.icupad.form.repository.FormTemplateRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = { FormTemplateRepository.class })
@ComponentScan(basePackages = { "com.icupad.form.*" })
@EntityScan(basePackages = { "com.icupad.form.model" })
public class FormBuilderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormBuilderApplication.class, args);
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setTomcatContextCustomizers(Collections.singletonList(context -> context.setUseHttpOnly(false)));
        return factory;
    }
}
