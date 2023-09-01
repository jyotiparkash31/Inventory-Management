package com.gl.inventorymanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.inventorymanagement.entity.Customer;
import com.gl.inventorymanagement.entity.Product;
import com.gl.inventorymanagement.entity.ProductSeller;
import com.gl.inventorymanagement.exception.ResourceNotFoundException;
import com.gl.inventorymanagement.exception.SellerNotFoundException;
import com.gl.inventorymanagement.service.ProductSellerService;
import com.gl.inventorymanagement.service.ProductService;

import jakarta.transaction.Transactional;

@Transactional
@RestController
public class ProductSellerController {

	@Autowired
	ProductSellerService productSellerService;
	@Autowired
	ProductService productService;
	@PostMapping("api-seller/add/productSeller/{sellerId}")
	public ResponseEntity<String> addProductSeller( @RequestBody ProductSeller productSeller,@PathVariable int sellerId) {
		try {
			productSeller.setSellerId(sellerId);
			Product product=productSeller.getProduct();
			System.out.println(product.getName());
			Product product2=productService.getProductByName(product.getName());
			ProductSeller productSeller2=productSellerService.getProductSellerUsingProductIdAndSellerId(product2.getId(),sellerId);
			
         if( productSeller2!=null && product2!=null ) {
        	 System.out.println(product2.getName());
				productSeller.setProduct(product2);
				System.out.println("product2 set");
				
				System.out.println(productSeller2.getQuantity());
				int quantity=productSeller2.getQuantity()+productSeller.getQuantity();
				System.out.println(quantity);
				productSellerService.updateProductSellerQuantity(quantity, productSeller2.getId());
				return new ResponseEntity<String>("productSeller updated",
						HttpStatus.ACCEPTED);
			}else if(product2!=null) {
				productSeller.setProduct(product2);
				productSellerService.addproductSeller(productSeller);
				return new ResponseEntity<String>("productSeller added",
						HttpStatus.ACCEPTED);
			}
			else {
				productService.addProduct(product);
				productSellerService.addproductSeller(productSeller);
				return new ResponseEntity<String>("productSeller added",
						HttpStatus.ACCEPTED);
			}
			
		} catch (Exception e) {
			if(e!=null)
				throw new ResourceNotFoundException();
		}
		
		
		return new ResponseEntity<String>("Product Added ",HttpStatus.CREATED);
	}
	
	@GetMapping("api-seller/get/productSellerById/{id}")
	public ProductSeller getCustomerById(@PathVariable int id) {
		Optional<ProductSeller> productSeller = productSellerService.getProductSellerById(id);
		if (productSeller.isPresent()) {
			return productSeller.get();
		}
		else {
			throw new SellerNotFoundException();
		}
		//return null;

	}
	
	@GetMapping("api-seller/get/productSellers")
	public List<ProductSeller>  getAllProductSellers() {
		
		return productSellerService.getProductSellers();

	}
	
	@PutMapping("api-seller/update/productSeller/{id}")
	public ResponseEntity<String> updateProduct( @RequestBody ProductSeller productSeller, @PathVariable int id){
		ProductSeller updatedProductSeller = productSellerService.updateProductSeller(productSeller, id);
		if(updatedProductSeller == null) {
			return  new ResponseEntity<String>("The productSeller does not exist",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("ProductSeller is updated",HttpStatus.OK);
	}
	
}
