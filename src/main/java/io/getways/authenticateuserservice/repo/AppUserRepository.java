package io.getways.authenticateuserservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.getways.authenticateuserservice.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	AppUser findByUsername(String username);
}
