package com.gl.inventorymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.inventorymanagement.entity.Admin;
import com.gl.inventorymanagement.entity.Category;
import com.gl.inventorymanagement.entity.Seller;
import com.gl.inventorymanagement.repository.SellerRepository;

@Service
public class SellerService {
	@Autowired
    private SellerRepository sellerRepository;
	
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	public Seller authenticate(String name, String password) {
		return sellerRepository.findUserByUsernameAndPassword(name, password);
	}
	
	public Seller addSeller(Seller  seller) {
		 return sellerRepository.save(seller); 
	}
	
	public List<Seller> getAllSellers() {
		
		return  sellerRepository.findAll();
	}
	public void updateSeller(Seller seller) {
		String password=  passwordEncoder.encode(seller.getPassword());
		seller.setPassword(password);
		 sellerRepository.save(seller);
	}
	public void updateType(int id,String type) {
		 sellerRepository.updateType(id,type);
	}
	public void updateName(int id,String name) {
		 sellerRepository.updateName(id,name);
	}
	public void updateLocation(int id,String location) {
		 sellerRepository.updateLocation(id,location);
	}
	public Optional<Seller> findSeller(int id) {

		return sellerRepository.findById(id);
	}
	
	public List<Seller> findSellerByType(String type) {

		return sellerRepository.getSellerByType(type);
	}
	public List<Seller> findSellerByLocation(String type) {

		return sellerRepository.getSellerByLocation(type);
	}
	
	public void deleteSeller(int id) {
		 sellerRepository.deleteById(id);
	}
	
	public Seller registerSeller(Seller seller) {
		String password=  passwordEncoder.encode(seller.getPassword());
		seller.setPassword(password);
		return sellerRepository.save(seller);
	}
}
