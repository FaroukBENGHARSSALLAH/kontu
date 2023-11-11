package com.farouk.bengarssallah.kontu;

import com.farouk.bengarssallah.kontu.domain.User;
import com.farouk.bengarssallah.kontu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCrypt;

@ComponentScan(value={"com.farouk.bengarssallah.kontu"})
@EntityScan(value={"com.farouk.bengarssallah.kontu.domain"})
@EnableJpaRepositories(value={"com.farouk.bengarssallah.kontu.repository"})
@SpringBootApplication
public class Application{
	
    @Autowired
    UserRepository userRepository;

   

    public static void main(String[] args) {
        SpringApplication.run(Application.class, (String[])args);
    }
    
    
    
    @Bean
    CommandLineRunner start(UserRepository userRepository){
        return args->{
        	
        	  System.out.println("starting");
              User user = new User();
              user.setLogin("admin");
              user.setPassword(BCrypt.hashpw("admin", (String)BCrypt.gensalt((int)10)));
              user.setRole("ADMIN");
              this.userRepository.save(user);
              
              User user2 = new User();
              user2.setLogin("employee");
              user2.setPassword(BCrypt.hashpw("employee", (String)BCrypt.gensalt((int)10)));
              user2.setRole("EMPLOYEE");
              this.userRepository.save(user2);
              
              User user3 = new User();
              user3.setLogin("employee1");
              user3.setPassword(BCrypt.hashpw("employee1", (String)BCrypt.gensalt((int)10)));
              user3.setRole("EMPLOYEE");
              this.userRepository.save(user3);
        
          };
    }

    
}