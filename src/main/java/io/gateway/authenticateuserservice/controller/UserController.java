package io.gateway.authenticateuserservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.gateway.authenticateuserservice.entities.AppUser;
import io.gateway.authenticateuserservice.entities.GatewayResponse;
import io.gateway.authenticateuserservice.entities.Header;
import io.gateway.authenticateuserservice.model.RoleUserForm;
import io.gateway.authenticateuserservice.service.AppUserService;

@RestController
@RequestMapping("${app.version}")
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

	@PostMapping("/user/save")
	public ResponseEntity<GatewayResponse<AppUser>> saveUser(@RequestBody AppUser user) {
		GatewayResponse<AppUser> response = new GatewayResponse<>();
		AppUser savedUser = userService.saveUser(user);
		Header header = new Header(101L, "SUCCESS");
		response.setHeader(header);
		response.setBody(savedUser);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(
				"${app.version}" + "/user/save").toUriString());
		return ResponseEntity.created(uri).body(response);
	}
	
	@PostMapping("/user/add/role")
	public ResponseEntity<GatewayResponse<?>> addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
		GatewayResponse<AppUser> response = new GatewayResponse<>();
		userService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
		Header header = new Header(101L, "SUCCESS");
		response.setHeader(header);
		return ResponseEntity.ok().body(response);
	}
}
