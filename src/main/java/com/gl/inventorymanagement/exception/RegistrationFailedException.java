package com.gl.inventorymanagement.exception;

public class RegistrationFailedException extends RuntimeException{
	public RegistrationFailedException() {
        // TODO Auto-generated constructor stub
        super(String.format("Seller could not be added!"));
    }

}
