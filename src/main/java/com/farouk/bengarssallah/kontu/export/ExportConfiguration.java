package com.farouk.bengarssallah.kontu.export;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ExportConfiguration extends WebMvcConfigurerAdapter
{
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON).favorPathExtension(true);
    }
    
    @Bean
    public ViewResolver resourceBundleViewResolver() {
        final ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setBasename("views");
        viewResolver.setOrder(1);
        return (ViewResolver)viewResolver;
    }
}
