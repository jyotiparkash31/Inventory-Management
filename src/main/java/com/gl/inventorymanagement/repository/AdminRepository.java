package com.gl.inventorymanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.inventorymanagement.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	
	@Query(value = "SELECT * FROM admin a WHERE a.username = ?1 AND a.password = ?2", nativeQuery = true)
	public Admin findUserByUsernameAndPassword(String username ,String password);
	  Optional<Admin> findByUsername(String username);
	  
	  public Optional<Admin> findAdminByLocation(String location);
}
