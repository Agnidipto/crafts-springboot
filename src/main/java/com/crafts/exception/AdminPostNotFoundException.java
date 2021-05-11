package com.crafts.exception;

public class AdminPostNotFoundException extends Exception {

	public AdminPostNotFoundException(String msg) {
		super(msg);
	}
	
	public AdminPostNotFoundException() {
		super("Admin Post Not Found");
	}
}
