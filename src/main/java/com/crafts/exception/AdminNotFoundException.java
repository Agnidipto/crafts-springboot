package com.crafts.exception;

public class AdminNotFoundException extends Exception{

	public AdminNotFoundException(String msg) {
		super(msg);
	}
	
	public AdminNotFoundException() {
		super("Admin Not Found");
	}
}
