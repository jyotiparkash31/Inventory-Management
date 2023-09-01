package com.gl.inventorymanagement.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException() {
		super(String.format("Product is not found"));
	}
}
