package com.gl.inventorymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.inventorymanagement.entity.Category;
import com.gl.inventorymanagement.entity.Seller;
import com.gl.inventorymanagement.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	public Category addCategory(Category  category) {
		 return  categoryRepository.save(category);
	}
	public Category findByCategory(String category) {
		return categoryRepository.findByCategory(category);
	}
	public Optional<Category> findCategoryById(int id) {
		return categoryRepository.findById(id);
	}
	
	public void deleteCategory(int id) {
		 categoryRepository.deleteById(id);
	}
	
	public void updateCategory(int id, String category) {
		categoryRepository.updateCategory(id, category);
	}
public List<Category> getAllCategory() {
		
		return   categoryRepository.findAll();
				}
}
