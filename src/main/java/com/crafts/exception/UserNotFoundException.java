package com.crafts.exception;

public class UserNotFoundException extends Exception {

	public UserNotFoundException(String msg) {
		super(msg);
	}
	
	public UserNotFoundException() {
		super("User Not Found!");
	}
}
