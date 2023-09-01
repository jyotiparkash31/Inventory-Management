package com.gl.inventorymanagement.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Product{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int id;
	private String name;
	private String category;
	private String description;
	private Integer isFragile;
	private Integer isHazardous;
	private Double weightInKg;
	private LocalDate expiryDate;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	    private List<ProductSeller> productSellers;
	//
//	@ManyToMany(cascade = CascadeType.ALL)
//   private List<Seller> seller=new ArrayList<>();
}
