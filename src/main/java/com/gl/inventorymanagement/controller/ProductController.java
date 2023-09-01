package com.gl.inventorymanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheOperationInvoker.ThrowableWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.inventorymanagement.entity.Category;
import com.gl.inventorymanagement.entity.Product;
import com.gl.inventorymanagement.entity.Seller;
import com.gl.inventorymanagement.exception.ResourceNotFoundException;
import com.gl.inventorymanagement.exception.SellerNotFoundException;
import com.gl.inventorymanagement.service.CategoryService;
import com.gl.inventorymanagement.service.ProductService;
import com.gl.inventorymanagement.service.SellerService;

import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;

@RestController
@Transactional
public class ProductController {	
	@Autowired
	ProductService productService;
	@Autowired
	SellerService sellerService;
	@Autowired
	CategoryService categoryService;
	

	@PostMapping("api-seller/add/product/{id}")
	public ResponseEntity<String> addProduct( @RequestBody Product product,@PathVariable int id) {
		
		
		try {
			Category category=categoryService.findByCategory(product.getCategory());
			if(category==null) {
				return new ResponseEntity<String>("Category not found",HttpStatus.NOT_FOUND);
			}
			Seller seller=sellerService.findSeller(id).get();
			List<Seller> sellers=new ArrayList<>();
			sellers.add(seller);
//			product.setSeller(sellers);
			
			System.out.println(product.getId());
			productService.addProduct(product);
			
		} catch (Exception e) {
			if(e!=null)
				throw new ResourceNotFoundException();
		}
		
		
		return new ResponseEntity<String>("Product Added ",HttpStatus.CREATED);
	}
	
	@PostMapping("api-seller/add/category")
	public ResponseEntity<String> addCategory( @RequestBody Category category) {
		
		
		try {
		
			categoryService.addCategory(category);
			
		} catch (Exception e) {
			if(e!=null)
				throw new ResourceNotFoundException();
		}
		
		return new ResponseEntity<String>("Category Created ",HttpStatus.CREATED);
	}
	
	@GetMapping("api-all/get/allProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		try {
			return new ResponseEntity<List<Product>>(productService.getAllProducts(),HttpStatus.OK);
		} catch (Exception e) {
			if(e!=null)
				throw new ResourceNotFoundException();
		}
		return new ResponseEntity<List<Product>>(productService.getAllProducts(),HttpStatus.OK);
		
	}
	@GetMapping("api-all/get/allCategories")
	public ResponseEntity<List<Category>> getAllCategory() {
		try {
			return new ResponseEntity<List<Category>>(categoryService.getAllCategory(),HttpStatus.OK);
		} catch (Exception e) {
			if(e!=null)
				throw new ResourceNotFoundException();
		}
		return new ResponseEntity<List<Category>>(categoryService.getAllCategory(),HttpStatus.OK);
		
	}
	@GetMapping("api-all/get/productById/{id}")
	public ResponseEntity<Product> getAllProductsById(@PathVariable int id) {

		try {
			return new ResponseEntity<Product>(productService.findProductById(id).get(),HttpStatus.OK);
		} catch (Exception e) {
			if(e!=null) { 
				throw new ResourceNotFoundException();
			}
		}
		return new ResponseEntity<Product>(productService.findProductById(id).get(),HttpStatus.OK);
//		return productService.findProductById(id).get();
	}
	
	@PutMapping("api-seller/update/product/{id}")
	public ResponseEntity<String> updateProduct( @RequestBody Product product, @PathVariable int id){
		Product updatedProduct = productService.updateProduct(product, id);
		if(updatedProduct == null) {
			return  new ResponseEntity<String>("The product does not exist",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("Product is updated",HttpStatus.OK);
	}
	


	
	@GetMapping("api-seller/get/productByCategory")
	public List<Product> getSellerByType(@RequestParam String category) {
		List<Product> productList =productService.findProductByCategory(category);
		if (productList!=null) {
			return productList;
		}
		else {
			throw new ResourceNotFoundException();
		}
		

	}
	
	
	@PutMapping("api-seller/update/category")
	public ResponseEntity<String> updateCategory( @RequestParam int id,@RequestParam String category) {
		Optional<Category> category1 =categoryService.findCategoryById(id);	
		if(category1.isPresent()) {
			categoryService.updateCategory(id, category);
			return new ResponseEntity<String>("Category Updated", HttpStatus.ACCEPTED);
		  
	}
		else {
			throw new ResourceNotFoundException();
		}
//		return new ResponseEntity<String>("Product Not Found" , HttpStatus.NO_CONTENT);
}
	
	
	@DeleteMapping("api-seller/delete/product")
	public ResponseEntity<String> deleteCustomer(@RequestParam int id) {
		Optional<Product> product2 = productService.findProductById(id);

		if (product2.isPresent()) {
			productService.deleteProduct(id);
			return new ResponseEntity<String>("Product deleted",
					HttpStatus.ACCEPTED);

		}
		else {
			throw new ResourceNotFoundException();
		}
		
	}
	
	@DeleteMapping("api-seller/delete/category")
	public ResponseEntity<String> deleteCategory(@RequestParam String category) {
		Category  category1=categoryService.findByCategory(category);

		if (category1!=null) {
			categoryService.deleteCategory(category1.getId());
			return new ResponseEntity<String>("Category deleted ",
					HttpStatus.ACCEPTED);

		}
		else {
			throw new ResourceNotFoundException();
		}
		
	}
}
