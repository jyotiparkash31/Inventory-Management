package com.gl.inventorymanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product_seller")
@Data
public class ProductSeller {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;

//	    @ManyToOne
//	    @JoinColumn(name = "seller_id")
//	    private Seller seller;
	    private int sellerId;
	    
	    private long price;

	    @Column(name = "quantity")
	    private int quantity;

	    @Column(name = "location")
	    private String location;

	    // Constructors, getters, and setters
	}

