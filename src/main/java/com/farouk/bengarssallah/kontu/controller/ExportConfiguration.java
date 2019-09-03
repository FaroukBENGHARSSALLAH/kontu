package com.farouk.bengarssallah.kontu.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@Configuration
public class ExportConfiguration extends WebMvcConfigurerAdapter {

@Override
public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.defaultContentType(MediaType.APPLICATION_JSON).favorPathExtension(true);
}

@Bean
public ViewResolver resourceBundleViewResolver() {
    
    ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
    viewResolver.setBasename("views");
    viewResolver.setOrder(1);
    return viewResolver;
}
}
