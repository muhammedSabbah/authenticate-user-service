package io.gateway.authenticateuserservice.service;

import java.util.List;

import io.gateway.authenticateuserservice.entities.AppUser;
import io.gateway.authenticateuserservice.exception.GatewayBusinessException;

public interface AppUserService {

	AppUser saveUser(AppUser user) throws GatewayBusinessException;
	
	void addRoleToUser(String username, String roleName) throws GatewayBusinessException;
	
	AppUser getUser(String username) throws GatewayBusinessException;
	
	List<AppUser> getUsers() throws GatewayBusinessException;
}
