 package com.gl.inventorymanagement.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gl.inventorymanagement.entity.Admin;
import com.gl.inventorymanagement.entity.Customer;
import com.gl.inventorymanagement.entity.Seller;
import com.gl.inventorymanagement.repository.AdminRepository;
import com.gl.inventorymanagement.repository.CustomerRepository;
import com.gl.inventorymanagement.repository.SellerRepository;

@Component
	public class UserInfoUserDetailsService implements UserDetailsService {

	    @Autowired
	    private AdminRepository repository;
	    
	    @Autowired
	    private SellerRepository sellerRepository;
	    
	    @Autowired
	    private CustomerRepository customerRepository;

	    @Override
	    public UserDetails loadUserByUsername(String username) {
	    	System.out.println(username);
	        Optional<Admin> adminInfo =repository.findByUsername(username);
	        Optional<Seller> sellerInfo = sellerRepository.findByUsername(username);
	        Optional<Customer> customerInfo = customerRepository.findByUsername(username);
 	        if(!adminInfo.isEmpty()) {
 	        	System.out.println(adminInfo.get().getUsername());
 		        System.out.println(adminInfo.get().getPassword());
 		        return adminInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
 	        }else  if(!sellerInfo.isEmpty()) {
 	        	System.out.println(sellerInfo.get().getUsername());
 		        System.out.println(sellerInfo.get().getPassword());
 		        return sellerInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
 	        }else if(!customerInfo.isEmpty()) {
 	        	
 	       	System.out.println(customerInfo.get().getUsername());
		        System.out.println(customerInfo.get().getPassword());
		        return customerInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
 	        }
	        
	                return null;

	    }
	}
