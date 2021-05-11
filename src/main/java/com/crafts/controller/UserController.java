package com.crafts.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crafts.entity.ContactInfo;
import com.crafts.entity.Post;
import com.crafts.entity.User;
import com.crafts.exception.UserNotFoundException;
import com.crafts.service.UserService;
import com.crafts.dto.*;

@Controller
@CrossOrigin(origins="*")
public class UserController {

	@Autowired 
	UserService uservice;
	
	@GetMapping("/users") //tested
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok().body(uservice.getAllUsers());
	}
	
	@PostMapping("/users") //tested
	public ResponseEntity<User> saveNewUser(@RequestBody User user) {
		return ResponseEntity.ok().body( uservice.saveUser(user));
	}
	
	@GetMapping("/user/{id}") //tested
	public ResponseEntity<User> getUserById(@PathVariable String id) throws UserNotFoundException {
		return ResponseEntity.ok().body(uservice.getUserById(id));
	}
	
	@GetMapping("/user/email/{email}") //tested
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) throws UserNotFoundException {
		return ResponseEntity.ok().body(uservice.getUserByEmail(email));
	}
	
	@PostMapping("/user/profileimage/{id}") //tested
	public ResponseEntity<User> saveProfilePicture(@PathVariable String id, @RequestParam("file") MultipartFile file) throws UserNotFoundException, IOException {
		return ResponseEntity.ok().body(uservice.saveProfilePic(id, file));
	}
	
	@GetMapping("/user/{id}/posts") //tested
	public ResponseEntity<Set<Post>> getUserPosts(@PathVariable String id) throws UserNotFoundException {
		return ResponseEntity.ok().body(uservice.getAllPosts(id));
	}
	
	@GetMapping("/user/{id}/info") //tested - returns id, email, password and name only
	public ResponseEntity<User> getUserInfo(@PathVariable String id) throws UserNotFoundException {
		return ResponseEntity.ok().body(uservice.getUserInfo(id));
	}
	
	@PutMapping("/user/{id}/update") //tested
	public ResponseEntity<User> updateUserInfo(@PathVariable String id, @RequestBody User user) throws UserNotFoundException {
		return ResponseEntity.ok().body(uservice.updateUser(id, user));
	}
	
	@PostMapping("/user/{id}/contactinfo") //tested
	public ResponseEntity<User> saveUserContactInfo(@PathVariable String id, @RequestBody ContactInfo contactInfo) throws UserNotFoundException {
		return ResponseEntity.ok().body(uservice.saveUserContactInfo(id, contactInfo));
	}
	
	@PostMapping("/user/{id}/about") //tested
	public ResponseEntity<User> saveUserAbout(@PathVariable String id, @RequestBody String about) throws UserNotFoundException {
		return ResponseEntity.ok().body(uservice.saveUserAbout(id, about));

	}
	
	//ResponseUser
	
	@GetMapping("/users/url") //tested
	public ResponseEntity<List<ResponseUser>> getResponseUser() {
		return ResponseEntity.ok().body(uservice.getResponseUsers());
	}
	
	@GetMapping("/user/{id}/url") //tested
	public ResponseEntity<ResponseUser> getResponseUserById(@PathVariable String id) throws UserNotFoundException {
		return ResponseEntity.ok().body(uservice.getResponseUserById(id));
	}
}
