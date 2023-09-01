package com.gl.inventorymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.inventorymanagement.entity.ProductSeller;
import com.gl.inventorymanagement.entity.Seller;
import com.gl.inventorymanagement.repository.ProductSellerRepository;
@Service
public class ProductSellerService {

	@Autowired
	ProductSellerRepository productSellerRepository;
	public ProductSeller addproductSeller(ProductSeller productSeller) {
		 return productSellerRepository.save(productSeller);
	}
	public int getQuantityFromProductSeller(int product_id) {
		return productSellerRepository.getQuantityFromProductSeller(product_id);
	}
	
	public ProductSeller getProductSellerUsingProductIdAndSellerId(int product_id,int seller_id) {
		return productSellerRepository.getProductSellerUsingProductIdAndSellerId(seller_id, product_id);
	} 
	
	public void updateProductSellerQuantity(int quantity,long id) {
		productSellerRepository.updateProductSellerQuantity(quantity, id);
	}
	public Optional<ProductSeller> getProductSellerById(int id){
		return productSellerRepository.findById(id);
	}
	public List<ProductSeller>  getProductSellers(){
		return productSellerRepository.findAll();
	}
	
	public ProductSeller updateProductSeller(ProductSeller updatedProductSeller, int id){
		Optional<ProductSeller> optionalOldProduct = productSellerRepository.findById(id);
		if(optionalOldProduct.isEmpty()) {
			return null;
		}
		ProductSeller oldProductSeller = optionalOldProduct.get();
		if(updatedProductSeller.getPrice() != 0)
			oldProductSeller.setPrice(updatedProductSeller.getPrice());
		if(updatedProductSeller.getLocation() != null)
			oldProductSeller.setLocation(updatedProductSeller.getLocation());
		if(updatedProductSeller.getQuantity()!=0)
			oldProductSeller.setQuantity(updatedProductSeller.getQuantity());

		return productSellerRepository.save(oldProductSeller);
		
	}
}
