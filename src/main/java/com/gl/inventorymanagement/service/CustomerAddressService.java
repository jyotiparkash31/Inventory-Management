package com.gl.inventorymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.inventorymanagement.repository.CustomerAddressRepository;

@Service
public class CustomerAddressService {
	@Autowired
	CustomerAddressRepository customerAddressRepository;
 public int getAddressIdFromCustomerAddress(int customer_id) {
	return customerAddressRepository.getAddressIdFromCustomerAddress(customer_id);
}
}
