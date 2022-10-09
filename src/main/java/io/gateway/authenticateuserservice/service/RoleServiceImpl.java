package io.gateway.authenticateuserservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.gateway.authenticateuserservice.entities.Role;
import io.gateway.authenticateuserservice.repo.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

}
