package com.gl.inventorymanagement.entity;


import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="seller")
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sellerId;
//	@SuppressWarnings("deprecation")
	@NotEmpty(message = "location should not be empty")
	private String location;
	@Size(min=4 , message = "Name should be atleast 4 characters")
	private String name;

//	@Size(min=9 , message = "Contact number should be of 10 digits")
	private long contactNumber;
	@NotEmpty(message = "Type should not be empty")
	private String type;
	private String  roles;
	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
//	@ManyToMany(mappedBy = "seller")
//    private List<Product> product;	
}
