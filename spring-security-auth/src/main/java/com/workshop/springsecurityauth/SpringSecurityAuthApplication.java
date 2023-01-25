package com.workshop.springsecurityauth;

import com.workshop.springsecurityauth.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@SpringBootApplication
public class SpringSecurityAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAuthApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (UserRepository userRepository) {
		return args -> {
			userRepository.addUser(
					new User(
							"admin",
							"$2a$12$E5uh7jiokIBMXbDdoHhxMecMohcXDDI0w51jMAF0Y7VRRXMZ8AKLC",
							Collections.singleton( new SimpleGrantedAuthority("ROLE_ADMIN")))
			);
		};
	}

}
