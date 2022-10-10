package io.gateway.authenticateuserservice;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.gateway.authenticateuserservice.entities.AppUser;
import io.gateway.authenticateuserservice.entities.Role;
import io.gateway.authenticateuserservice.service.AppUserService;
import io.gateway.authenticateuserservice.service.RoleService;

@SpringBootApplication
public class AuthenticateUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticateUserServiceApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner run(RoleService roleService, AppUserService userService) {
		return arg -> {
			roleService.saveRole(new Role(null, "ROLE_USER"));
			roleService.saveRole(new Role(null, "ROLE_ADMIN"));
			
			String password = new BCryptPasswordEncoder().encode("123");
			userService.saveUser(new AppUser(null, "SABBAH", "SABBAH", password, new ArrayList<>()));
			
		};

	}

}
