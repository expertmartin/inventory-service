package com.kingtan.store.inventory.exception;

public class StoreServerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	public StoreServerException(String message) {
		this.message = message;
	}

	public String getSpittleId() {
		return message;
	}
}
