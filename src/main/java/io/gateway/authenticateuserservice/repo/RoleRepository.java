package io.gateway.authenticateuserservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gateway.authenticateuserservice.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
