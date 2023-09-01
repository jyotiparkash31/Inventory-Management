package com.gl.inventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.inventorymanagement.entity.Admin;
import com.gl.inventorymanagement.entity.Product;
import com.gl.inventorymanagement.exception.ResourceNotFoundException;
import com.gl.inventorymanagement.service.AdminService;

@RestController
public class AdminController {

	
	@Autowired
	AdminService userService;
	@PostMapping("api-all/registerAdmin")
	public ResponseEntity<String> registerAdmin( @RequestBody Admin admin) {
		//return new ResponseEntity<Admin>(adminService.registerAdmin(admin),HttpStatus.CREATED);
		userService.registerAdmin(admin);
		return new ResponseEntity<String>("Admin Registered with User Id :" + admin.getUserId(),
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping("api-admin/get/adminById/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable int id) {

		try { 
			return new ResponseEntity<Admin>(userService.findAdminById(id).get(),HttpStatus.OK);
		} catch (Exception e) {
			if(e!=null) { 
				throw new ResourceNotFoundException();
			}
		}
		return new ResponseEntity<Admin>(userService.findAdminById(id).get(),HttpStatus.OK);
//		return productService.findProductById(id).get();
	}
	
	@GetMapping("api-admin/get/adminByLocation/{location}")
	public ResponseEntity<Admin> getAdminByLocation(@PathVariable String location) {

		try { 
			return new ResponseEntity<Admin>(userService.findAdminByLocation(location) .get(),HttpStatus.OK);
		} catch (Exception e) {
			if(e!=null) { 
				throw new ResourceNotFoundException();
			}
		}
		return new ResponseEntity<Admin>(userService.findAdminByLocation(location).get(),HttpStatus.OK);
//		return productService.findProductById(id).get();
	}
	
	
	
	
	@GetMapping("api-admin/get/allAdmin")
	public ResponseEntity<List<Admin>> getAllAdmin() {
		try {
			return new ResponseEntity<List<Admin>>(userService.getAllAdmin(),HttpStatus.OK);
		} catch (Exception e) {
			if(e!=null)
				throw new ResourceNotFoundException();
		}
		return new ResponseEntity<List<Admin>>(userService.getAllAdmin(),HttpStatus.OK);
		
	}
}
