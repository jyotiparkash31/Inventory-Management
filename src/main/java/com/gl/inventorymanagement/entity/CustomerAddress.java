package com.gl.inventorymanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
     @Column(name = "is_current_address")
     private int defaultAddress;

    private String houseName;

    private String colony;

	private String landmark;

    private String city;

    private String state;

    private String country;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
