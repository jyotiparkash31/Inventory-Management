package com.gl.inventorymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.inventorymanagement.entity.Customer;
import com.gl.inventorymanagement.entity.CustomerAddress;
import com.gl.inventorymanagement.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	public Customer authenticate(String name, String password) {
		return customerRepository.findUserByUsernameAndPassword(name, password);
	}

//	public Customer addCustomer(Customer customer) {
//		return customerRepository.save(customer);
//	}

	public Optional<Customer> findCustomerById(int id) {

		return customerRepository.findById(id);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	public List<Customer> getCustomerByGender(String gender) {

		return customerRepository.getCustomerByGender(gender);
	}
	
	public void deleteCustomer(int id) {
		 customerRepository.deleteById(id);
	}
	public Customer registerCustomer(Customer customer) {
		String password=  passwordEncoder.encode(customer.getPassword());
		customer.setPassword(password);
		
		for (CustomerAddress address : customer.getCustomerAddresses()) {
	        address.setCustomer(customer);
	    }
		return customerRepository.save(customer);
	}
}
