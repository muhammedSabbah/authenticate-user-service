package io.gateway.authenticateuserservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.gateway.authenticateuserservice.entities.AppUser;
import io.gateway.authenticateuserservice.entities.GatewayResponse;
import io.gateway.authenticateuserservice.entities.Header;
import io.gateway.authenticateuserservice.service.AppUserService;

@RestController
@RequestMapping("/api/v1.0")
public class UserController {

	@Autowired
	private AppUserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<GatewayResponse<List<AppUser>>> getUsers() {
		GatewayResponse<List<AppUser>> response = new GatewayResponse<>();
		List<AppUser> users = userService.getUsers();
		Header header = new Header(101L, "SUCCESS");
		response.setHeader(header);
		response.setBody(users);
		return ResponseEntity.ok().body(response);
	}
}
