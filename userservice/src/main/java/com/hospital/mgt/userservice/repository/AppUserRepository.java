package com.hospital.mgt.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.mgt.userservice.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	Optional<AppUser> findByUsername(String username);
}
