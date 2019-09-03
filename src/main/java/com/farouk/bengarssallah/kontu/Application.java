package com.farouk.bengarssallah.kontu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan("com.farouk.bengarssallah.kontu")
@EntityScan("com.farouk.bengarssallah.kontu.domain")
@EnableJpaRepositories("com.farouk.bengarssallah.kontu.repository")
@SpringBootApplication
public class Application  extends SpringBootServletInitializer{

	      @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(Application.class);
	    }
	 

	
	public static void main(String[] args) {
		     SpringApplication.run(Application.class, args);
	    }


	  
	
}
