package com.gl.inventorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event.ID;

import com.gl.inventorymanagement.entity.Customer;
import com.gl.inventorymanagement.entity.CustomerAddress;
@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {

	@Query("SELECT e.id FROM CustomerAddress e WHERE e.defaultAddress = 1 and e.customer.id= :customer_id ")
	int getAddressIdFromCustomerAddress(@Param("customer_id") int customer_id);
}
