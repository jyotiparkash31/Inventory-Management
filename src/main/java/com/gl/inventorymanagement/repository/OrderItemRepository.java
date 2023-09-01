package com.gl.inventorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.inventorymanagement.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

//	@Query("SELECT o.customer.id, o.product.id, COUNT(o) as count, SUM(o.price*o.quantity) as totalPrice " +
//            "FROM OrderItem o " +
//            "GROUP BY o.customer.id, o.product.id")
//    List<Object[]> findProductCountsByCustomer();

}
