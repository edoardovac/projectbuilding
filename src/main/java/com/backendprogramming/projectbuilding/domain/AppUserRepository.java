package com.backendprogramming.projectbuilding.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AppUserRepository extends CrudRepository<AppUser, Long>{
	// single result
	AppUser findByUsername(String username);
	
	// multiple results
	// AppUser findByRole(String role);
	List<AppUser> findByRole(@Param("role") String role);
}
