package com.crafts.controller;

import java.io.FileNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crafts.exception.*;

@SuppressWarnings({ "rawtypes" }) 
@RestControllerAdvice
@CrossOrigin(origins="*")
public class ExceptionController {

	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity handlePostNotFoundException(PostNotFoundException ex) {
		return ResponseEntity.ok().body(ex.getMessage());
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity handleFileNotFoundException(FileNotFoundException ex) {
		return ResponseEntity.ok().body(ex.getMessage());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity handleUSerNotFoundException(UserNotFoundException ex) {
		return ResponseEntity.ok().body(ex.getMessage());
	}
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity handleAdminNotFoundException(AdminNotFoundException ex) {
		return ResponseEntity.ok().body(ex.getMessage());
	}
	
	@ExceptionHandler(AdminPostNotFoundException.class)
	public ResponseEntity handleAdminPostNotFoundException(AdminPostNotFoundException ex) {
		return ResponseEntity.ok().body(ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
		return ResponseEntity.badRequest().body("Exception occured :"+e.getMessage());
	}
}
