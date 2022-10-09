package io.gateway.authenticateuserservice.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.gateway.authenticateuserservice.entities.GatewayResponse;
import io.gateway.authenticateuserservice.entities.Header;
import io.gateway.authenticateuserservice.entities.Role;
import io.gateway.authenticateuserservice.service.RoleService;

@RestController
@RequestMapping("${app.version}")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping("/role/save")
	public ResponseEntity<GatewayResponse<Role>> saveRole(@RequestBody Role role) {
		GatewayResponse<Role> response = new GatewayResponse<>();
		Role savedRole = roleService.saveRole(role);
		Header header = new Header(101L, "SUCCESS");
		response.setHeader(header);
		response.setBody(savedRole);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(
				"${app.version}" + "/role/save").toUriString());
		return ResponseEntity.created(uri).body(response);
	}
}
