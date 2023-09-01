package com.gl.inventorymanagement.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.inventorymanagement.entity.Admin;
import com.gl.inventorymanagement.repository.AdminRepository;

//@Service
//public class UserService {
//
//	@Autowired
//	UserRepository userRepository;
//	
////	   @Autowired
////	    private PasswordEncoder passwordEncoder;
//
////
////	public User authenticate(User user) {
////		return userRepository.findUserByUsernameAndPassword(user.getName(), user.getPassword());
////	}
//
//	public User registerUser(String username, String) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		
//		user.setPassword(encoder.encode(user.getPassword()));
//
//		return userRepository.save(user);
//	}
//	
//}

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	public Admin authenticate(String name, String password) {
		return adminRepository.findUserByUsernameAndPassword(name, password);
	}

	public Admin registerAdmin(Admin admin) {
		String password=  passwordEncoder.encode(admin.getPassword());
		admin.setPassword(password);
		return adminRepository.save(admin);
	}

	public Optional<Admin> findAdminById(int id) {
		// TODO Auto-generated method stub
		return adminRepository.findById(id);
	}

	public Optional<Admin> findAdminByLocation(String location) {
		
		return adminRepository.findAdminByLocation(location);
	}
	public List<Admin> getAllAdmin() {
		// TODO Auto-generated method stub
		return adminRepository.findAll();
		
	}
	
}
