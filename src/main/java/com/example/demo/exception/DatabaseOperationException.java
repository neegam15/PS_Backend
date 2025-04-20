package com.example.demo.exception;

public class DatabaseOperationException extends RuntimeException {

	public DatabaseOperationException(String message) {
		super(message);
	}
}
