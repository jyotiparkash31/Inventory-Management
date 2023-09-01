package com.gl.inventorymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.inventorymanagement.entity.Category;
import com.gl.inventorymanagement.entity.Product;
import com.gl.inventorymanagement.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
       private ProductRepository productRepository;
	public Product addProduct(Product product) {
		 return productRepository.save(product); 
	}
//	public void placeOrderUpdateQuantity(Product product, int quantity) {
//		int newQuantity= product.getQuantity()-quantity;
//		 productRepository.updateQuantity(product.getId(), newQuantity);
//	}
//	
//	public void cancelOrderUdateQuantity(Product product, int quantity) {
//		int newQuantity= product.getQuantity()+quantity;
//		 productRepository.updateQuantity(product.getId(), newQuantity);
//	}
	
	public List<Product> getAllProducts() {
		
		return  productRepository.findAll();
	}
//	public void updateProduct(Product product) {
//		 productRepository.save(product);
//	}
	
	
	public Optional<Product> findProductById(int id) {

		return productRepository.findById(id);
	}
	
//	public void updateDescription(int id, String description) {
//		productRepository.updateDescription(id,description);
//	}
//	
//	public void updateLocation(int id, String location) {
//		productRepository.updateLocation(id,location);
//	}
//	
//	public void updatePrice(int id, int price) {
//		productRepository.updatePrice(id,price);
//	}
	
	public List<Product> findProductByCategory(String category) {

		return productRepository.getProductByCategory(category);
	}
	public void deleteProduct(int id) {
		 productRepository.deleteById(id);
	}
	public Product getProductByName(String name) {
		return productRepository.getProductByName(name);
	}
	
	public Product updateProduct(Product updatedProduct, int id){
		Optional<Product> optionalOldProduct = productRepository.findById(id);
		if(optionalOldProduct.isEmpty()) {
			return null;
		}
		Product oldProduct = optionalOldProduct.get();
		if(updatedProduct.getCategory() != null)
			oldProduct.setCategory(updatedProduct.getCategory());
		if(updatedProduct.getDescription() != null)
			oldProduct.setDescription(updatedProduct.getDescription());

		if(updatedProduct.getIsFragile() != null)
			oldProduct.setIsFragile(updatedProduct.getIsFragile());
		if(updatedProduct.getIsHazardous()!= null)
			oldProduct.setIsHazardous(updatedProduct.getIsHazardous());
		if(updatedProduct.getExpiryDate()!= null)
			oldProduct.setExpiryDate(updatedProduct.getExpiryDate());
		if(updatedProduct.getName() != null)
			oldProduct.setName(updatedProduct.getName());
		
		if(updatedProduct.getWeightInKg() != null)
			oldProduct.setWeightInKg(updatedProduct.getWeightInKg());
		
		return productRepository.save(oldProduct);
		
	}
}
