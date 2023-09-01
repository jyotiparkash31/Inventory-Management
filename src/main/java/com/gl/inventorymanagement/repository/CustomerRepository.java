package com.gl.inventorymanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gl.inventorymanagement.entity.Admin;
import com.gl.inventorymanagement.entity.Customer;
import com.gl.inventorymanagement.entity.Seller;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Modifying
	@Query("SELECT e FROM Customer e WHERE e.gender = :gender")
	List<Customer> getCustomerByGender(@Param("gender") String gender);
	
	@Query(value = "SELECT * FROM customer a WHERE a.username = ?1 AND a.password =?2", nativeQuery = true)
	public Customer findUserByUsernameAndPassword(String username ,String password);
	  Optional<Customer> findByUsername(String username);
	  


}
