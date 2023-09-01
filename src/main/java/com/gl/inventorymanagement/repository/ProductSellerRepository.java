package com.gl.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gl.inventorymanagement.entity.ProductSeller;

@Repository
public interface ProductSellerRepository extends JpaRepository<ProductSeller, Integer>{

	@Query("SELECT e.quantity FROM ProductSeller e WHERE e.product.id= :product_id ")
	int getQuantityFromProductSeller(@Param("product_id") int product_id);
	
	@Query("SELECT e FROM ProductSeller e WHERE e.product.id = :product_id and e.sellerId= :sellerId")
	ProductSeller getProductSellerUsingProductIdAndSellerId(@Param("sellerId") int sellerId,@Param("product_id") int product_id);
	
	@Modifying
	@Query("UPDATE ProductSeller e SET e.quantity= :quantity WHERE e.id = :id ")
	void updateProductSellerQuantity(@Param("quantity") int quantity,@Param("id") long id);
	

}
