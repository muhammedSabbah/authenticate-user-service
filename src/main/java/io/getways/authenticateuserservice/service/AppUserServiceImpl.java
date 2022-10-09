package io.getways.authenticateuserservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.getways.authenticateuserservice.entities.AppUser;
import io.getways.authenticateuserservice.entities.Role;
import io.getways.authenticateuserservice.repo.AppUserRepository;
import io.getways.authenticateuserservice.repo.RoleRepository;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {
	
	@Autowired
	private AppUserRepository userRepository;
	
	private RoleRepository roleRepository;

	@Override
	public AppUser saveUser(AppUser user) {
		return userRepository.save(user);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser user = userRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
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
