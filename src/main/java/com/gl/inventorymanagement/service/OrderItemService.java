package com.gl.inventorymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.inventorymanagement.entity.OrderItem;
import com.gl.inventorymanagement.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	OrderItemRepository orderProductRepository;
	public  OrderItem addOrderProduct(OrderItem orderProduct) {
		return orderProductRepository.save(orderProduct);
	}
	public  OrderItem getOrderProductById(int id) {
		return orderProductRepository.findById(id).get();
	} 
	public  List<OrderItem> getAllOrderProduct() {
		return orderProductRepository.findAll();
	}
	
//	public List<Object[]> findTotalPrice(){
//		return orderProductRepository.findProductCountsByCustomer();
//	}
}
