package com.gl.inventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.inventorymanagement.entity.Order;
import com.gl.inventorymanagement.entity.OrderItem;
import com.gl.inventorymanagement.entity.ProductSeller;
import com.gl.inventorymanagement.exception.SellerNotFoundException;
import com.gl.inventorymanagement.service.CustomerAddressService;
import com.gl.inventorymanagement.service.CustomerService;
import com.gl.inventorymanagement.service.OrderItemService;
import com.gl.inventorymanagement.service.OrderService;
import com.gl.inventorymanagement.service.ProductSellerService;
import com.gl.inventorymanagement.service.ProductService;

import jakarta.transaction.Transactional;

@RestController
@Transactional
public class OrderItemController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	CustomerAddressService customerAddressService;
	

	@Autowired
	ProductSellerService productSellerService;

	@PostMapping("api-customer/placeorder")
public ResponseEntity<String> placeOrder(@RequestBody List<OrderItem> orderItems) {
    Order order = new Order();
    int customerId=orderItems.get(0).getCustomerId();
    order.setCustomerId(customerId);
    int id=customerAddressService.getAddressIdFromCustomerAddress(customerId);
    order.setAddressId(id);
    orderService.addOrder(order);
    Long totalPrice = 0L;
    for(int i=0;i<orderItems.size();i++) {
    	int productId=orderItems.get(i).getProductId();
    	int sellerId=orderItems.get(i).getSellerId();
            int orderedQuantity=orderItems.get(i).getQuantity();
            ProductSeller productSeller = productSellerService.getProductSellerUsingProductIdAndSellerId(productId, sellerId);
            if (productSeller.getQuantity()< orderedQuantity) {
                throw new IllegalArgumentException("Product " + productId + " doesn't have enough quantity");
            }
           Long price = productSeller.getPrice() * orderedQuantity;       
           totalPrice += price;
           productSeller.setQuantity(productSeller.getQuantity() - orderedQuantity);
//           productService.addProduct(product);
           
            orderItems.get(i).setOrder(order);
            orderItems.get(i).setPrice(productSeller.getPrice());
            orderItemService.addOrderProduct(orderItems.get(i));
//            orderItems.add(orderItem);
    }
    
    
    order.setOrderItems(orderItems);
    order.setTotalPrice(totalPrice);
    orderService.addOrder(order);
    
    
    return ResponseEntity.ok("Order placed successfully");
}

//for(int id : ids) {
//	System.out.println(id);
//	Product product = productService.findProductById(id).get();
//	int orderedQuantity = orderItem.getQuantity();
//	if(product.getQuantity()>orderedQuantity) {
//	int remaningQuantity = product.getQuantity()-orderedQuantity;
//	product.setQuantity(remaningQuantity);
//	}
//	orderItem.setProduct(product);
//	productService.addProduct(product);
//}
//orderItem.setCustomer(customer);
//orderProductService.addOrderProduct(orderItem);
//	System.out.println("done");
//	return new ResponseEntity<String>("Completed " , HttpStatus.ACCEPTED);
	

@GetMapping("api-order/getAllOrderItems")
public ResponseEntity<List<OrderItem>> getAllOrderProducts() {
	try {
		return new ResponseEntity<List<OrderItem>>(orderItemService.getAllOrderProduct(), HttpStatus.OK);
	} catch (Exception e) {
		if(e!=null)
			throw new SellerNotFoundException();
	}
	return new ResponseEntity<List<OrderItem>>(orderItemService.getAllOrderProduct(), HttpStatus.OK);
}
}
