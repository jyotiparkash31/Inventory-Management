package com.gl.inventorymanagement.exception;

public class SellerNotFoundException extends RuntimeException {

	public SellerNotFoundException() {
        super(String.format("Seller is not found"));
    }

}
