package io.gateway.authenticateuserservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.gateway.authenticateuserservice.entities.AppUser;
import io.gateway.authenticateuserservice.entities.Role;
import io.gateway.authenticateuserservice.repo.AppUserRepository;
import io.gateway.authenticateuserservice.repo.RoleRepository;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {
	
	@Autowired
	private AppUserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public AppUser saveUser(AppUser user) {
		return userRepository.save(user);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser user = userRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		if (!user.getRoles().contains(role))
			user.getRoles().add(role);
	}

	@Override
	public AppUser getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<AppUser> getUsers() {
		return userRepository.findAll();
	}

}
