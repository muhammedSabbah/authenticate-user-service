package io.gateway.authenticateuserservice.service;

import java.util.List;

import io.gateway.authenticateuserservice.entities.AppUser;

public interface AppUserService {

	AppUser saveUser(AppUser user);
	
	void addRoleToUser(String username, String roleName);
	
	AppUser getUser(String username);
	
	List<AppUser> getUsers();
}
