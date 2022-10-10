package io.gateway.authenticateuserservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.gateway.authenticateuserservice.entities.Role;
import io.gateway.authenticateuserservice.service.RoleService;

@SpringBootApplication
public class AuthenticateUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticateUserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleService roleService) {
		return arg -> {
			roleService.saveRole(new Role(null, "ROLE_USER"));
			roleService.saveRole(new Role(null, "ROLE_ADMIN"));
			
		};

	}

}
