package io.getways.authenticateuserservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.getways.authenticateuserservice.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
