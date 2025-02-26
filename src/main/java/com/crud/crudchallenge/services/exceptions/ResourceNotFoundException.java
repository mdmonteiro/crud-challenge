package com.crud.crudchallenge.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2887304240097664236L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
