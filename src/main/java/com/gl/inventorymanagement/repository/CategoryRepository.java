package com.gl.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gl.inventorymanagement.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	 public  Category findByCategory(String category);
	 @Modifying
		@Query("UPDATE Category s SET s.category = :category WHERE s.id = :id")
		void updateCategory(@Param("id") int id, @Param("category") String category);
}
