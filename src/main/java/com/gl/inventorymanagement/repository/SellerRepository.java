package com.gl.inventorymanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gl.inventorymanagement.entity.Admin;
import com.gl.inventorymanagement.entity.Category;
import com.gl.inventorymanagement.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {	
//	 @Modifying 
//    @Query("UPDATE Customer c SET c.username = :username, c.email = :email, c.phoneNumber= :phoneNumber where c.customerId= :customerId")
//      void updateCustomer(@Param("customerId") int customerId,@Param("username") String username, @Param("email") String email, @Param("phoneNumber") long phoneNumber
//    		  );
	
	@Modifying
	@Query("UPDATE Seller s SET s.type = :type WHERE s.id = :id")
	void updateType(@Param("id") int id, @Param("type") String type);
	
	@Modifying
	@Query("UPDATE Seller s SET s.name = :name WHERE s.id = :id")
	void updateName(@Param("id") int id, @Param("name") String name);
	@Modifying
	@Query("UPDATE Seller s SET s.location = :location WHERE s.id = :id")
	void updateLocation(@Param("id") int id, @Param("location") String location);
	
	@Modifying
	@Query("SELECT e FROM Seller e WHERE e.type = :type")
	List<Seller> getSellerByType(@Param("type") String type);
	
	@Modifying
	@Query("SELECT e FROM Seller e WHERE e.location = :location")
	List<Seller> getSellerByLocation(@Param("location") String location);
	

	@Query(value = "SELECT * FROM seller a WHERE a.username = ?1 AND a.password = ?2", nativeQuery = true)
	public Seller findUserByUsernameAndPassword(String username ,String password);
	  Optional<Seller> findByUsername(String username);

	
	
	  
}
