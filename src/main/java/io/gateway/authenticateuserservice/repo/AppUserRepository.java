package io.gateway.authenticateuserservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gateway.authenticateuserservice.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	AppUser findByUsername(String username);
}
