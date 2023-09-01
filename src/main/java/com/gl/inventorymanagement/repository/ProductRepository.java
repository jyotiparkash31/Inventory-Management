package com.gl.inventorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gl.inventorymanagement.entity.Customer;
import com.gl.inventorymanagement.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


	
	@Query("SELECT e FROM Product e WHERE e.category = :category")
	List<Product> getProductByCategory(@Param("category") String category);
	
	@Query("SELECT e FROM Product e WHERE e.name= :name ")
	Product getProductByName(@Param("name") String name);
}
