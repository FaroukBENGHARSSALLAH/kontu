package com.farouk.bengarssallah.kontu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.context.annotation.Configuration;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

@Configuration
public class KontuConfiguration {
	
    @Bean
    public EmbeddedDatabase dataSource() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        final EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).build();
        return db;
    }
	
	@Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/h2-console/*");
        return registration;
    }

}
