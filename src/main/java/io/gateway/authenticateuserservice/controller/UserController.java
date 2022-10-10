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
import io.gateway.authenticateuserservice.entities.model.RoleUserForm;
import io.gateway.authenticateuserservice.entities.request.GatewayResponse;
import io.gateway.authenticateuserservice.entities.request.Header;
import io.gateway.authenticateuserservice.exception.GatewayBusinessException;
import io.gateway.authenticateuserservice.service.AppUserService;
import io.gateway.authenticateuserservice.utils.StatusCode;

@RestController
@RequestMapping("${app.version}")
public class UserController {

	@Autowired
	private AppUserService userService;

	@GetMapping("/users")
	public ResponseEntity<GatewayResponse<List<AppUser>>> getUsers() {
		GatewayResponse<List<AppUser>> response = new GatewayResponse<>();
		Header header = new Header(StatusCode.SUCCESS);
		List<AppUser> users = null;
		try {
			users = userService.getUsers();
		} catch (GatewayBusinessException e) {
			header = new Header((long)e.getErrorCode(), e.getMessage());
		}
		response.setHeader(header);
		response.setBody(users);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/user/save")
	public ResponseEntity<GatewayResponse<AppUser>> saveUser(@RequestBody AppUser user) {
		GatewayResponse<AppUser> response = new GatewayResponse<>();
		Header header = new Header(StatusCode.SUCCESS);
		AppUser savedUser = null;
		try {
			savedUser = userService.saveUser(user);
		} catch (GatewayBusinessException e) {
			header = new Header((long)e.getErrorCode(), e.getMessage());
		}
		response.setHeader(header);
		response.setBody(savedUser);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(
				"${app.version}" + "/user/save").toUriString());
		return ResponseEntity.created(uri).body(response);
	}
	
	@PostMapping("/user/add/role")
	public ResponseEntity<GatewayResponse<?>> addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
		GatewayResponse<AppUser> response = new GatewayResponse<>();
		Header header = new Header(StatusCode.SUCCESS);
		try {
			userService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
		} catch (GatewayBusinessException e) {
			header = new Header((long)e.getErrorCode(), e.getMessage());
		}
		response.setHeader(header);
		return ResponseEntity.ok().body(response);
	}
}
