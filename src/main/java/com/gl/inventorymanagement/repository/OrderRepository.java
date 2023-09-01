package com.gl.inventorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.gl.inventorymanagement.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

//	@Query("select p.customer_id from order_product p")
//	List<Long> getAllCustomerIds();
//	@Query("select p.product_id from order_product p")
//	List<Long> getAllProductId();
}
