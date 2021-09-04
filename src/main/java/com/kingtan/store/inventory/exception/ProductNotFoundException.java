package com.kingtan.store.inventory.exception;

public class ProductNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	public ProductNotFoundException(long productId) {
		this.message = "Product[" + productId + "not found";
	}

	public ProductNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
