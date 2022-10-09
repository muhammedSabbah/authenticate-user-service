package io.getways.authenticateuserservice.service;

import java.util.List;

import io.getways.authenticateuserservice.entities.AppUser;

public interface AppUserService {

	AppUser saveUser(AppUser user);
	
	void addRoleToUser(String username, String roleName);
	
	AppUser getUser(String username);
	
	List<AppUser> getUsers();
}
