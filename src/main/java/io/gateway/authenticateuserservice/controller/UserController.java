package io.gateway.authenticateuserservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.gateway.authenticateuserservice.entities.AppUser;
import io.gateway.authenticateuserservice.entities.GatewayResponse;
import io.gateway.authenticateuserservice.service.AppUserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private AppUserService userService;
	
	public ResponseEntity<GatewayResponse<List<AppUser>>> getUsers() {
		GatewayResponse<List<AppUser>> response = new GatewayResponse<>();
		List<AppUser> users = userService.getUsers();
		response.setBody(users);
		return ResponseEntity.ok().body(response);
	}
}
