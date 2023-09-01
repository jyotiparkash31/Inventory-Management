package com.gl.inventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.inventorymanagement.entity.Order;
import com.gl.inventorymanagement.entity.Seller;
import com.gl.inventorymanagement.exception.SellerNotFoundException;
import com.gl.inventorymanagement.service.OrderItemService;
import com.gl.inventorymanagement.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderItemService orderProductService;
	@Autowired
	OrderService orderService;
//	@PostMapping("/add/order")
//	public ResponseEntity<String> addOrder(@RequestBody Order order) {
//		
//		List<Object[]> result = orderProductService.findTotalPrice();
//		Long price=0L;
//		int customerId=0;
//		for (Object[] row : result) {
//			 price=(Long) row[3];
//			 customerId = (int)row[0];
//		}
////		order.setTotalPrice(price);
//		order.setCustomerId(customerId);
//		orderService.addOrder(order);
//		return new ResponseEntity<String>("Order Created ",HttpStatus.CREATED);
//	}
//	
	@GetMapping("api-admin/getAllOrders")
	public ResponseEntity<List<Order>> getAllOrders() {
		try {
			return new ResponseEntity<List<Order>>(orderService.getAllOrder(), HttpStatus.OK);
		} catch (Exception e) {
			if(e!=null)
				throw new SellerNotFoundException();
		}
		return new ResponseEntity<List<Order>>(orderService.getAllOrder(), HttpStatus.OK);
	}
	
	@GetMapping("api-admin/getOrderById/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable int id) {
		try {
			return new ResponseEntity<Order>(orderService.getAllOrderById(id).get(), HttpStatus.OK);
		} catch (Exception e) {
			if(e!=null)
				throw new SellerNotFoundException();
		}
		return new ResponseEntity<Order>(orderService.getAllOrderById(id).get(), HttpStatus.OK);
	}
}
