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
import io.gateway.authenticateuserservice.utils.USER_ROLE;

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
			roleService.saveRole(new Role(null, USER_ROLE.ROLE_USER.value()));
			roleService.saveRole(new Role(null, USER_ROLE.ROLE_ADMIN.value()));
			roleService.saveRole(new Role(null, USER_ROLE.ROLE_SUPER_ADMIN.value()));
			
			userService.saveUser(new AppUser(null, "SABBAH", "SABBAH", "123", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "A", "A", "123", new ArrayList<>()));
			
			userService.addRoleToUser("SABBAH", USER_ROLE.ROLE_SUPER_ADMIN.value());
			userService.addRoleToUser("A", USER_ROLE.ROLE_ADMIN.value());
		};

	}

}
