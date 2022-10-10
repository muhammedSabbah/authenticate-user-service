package io.gateway.authenticateuserservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.gateway.authenticateuserservice.entities.AppUser;
import io.gateway.authenticateuserservice.entities.Role;
import io.gateway.authenticateuserservice.exception.GatewayBusinessException;
import io.gateway.authenticateuserservice.repo.AppUserRepository;
import io.gateway.authenticateuserservice.repo.RoleRepository;
import io.gateway.authenticateuserservice.utils.StatusCode;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {
	
	@Autowired
	private AppUserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public AppUser saveUser(AppUser user) throws GatewayBusinessException {
		return userRepository.save(user);
	}

	@Override
	public void addRoleToUser(String username, String roleName) throws GatewayBusinessException {
		AppUser user = userRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		if (!user.getRoles().contains(role))
			user.getRoles().add(role);
		else
			throw new GatewayBusinessException(StatusCode.ROLE_ALREADY_ASSIGNED);
	}

	@Override
	public AppUser getUser(String username) throws GatewayBusinessException {
		AppUser user = userRepository.findByUsername(username);
		if (user == null) {
			throw new GatewayBusinessException(StatusCode.USER_NOT_EXIST);
		}
		return user;
	}

	@Override
	public List<AppUser> getUsers() throws GatewayBusinessException {
		List<AppUser> users = userRepository.findAll();
		if (users != null && users.size() > 0) {
			return users;
		}
		throw new GatewayBusinessException(StatusCode.USERS_EMPTY);
	}

}
