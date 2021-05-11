package com.crafts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.crafts.entity.Admin;
import com.crafts.entity.AdminPost;
import com.crafts.exception.AdminNotFoundException;
import com.crafts.exception.AdminPostNotFoundException;
import com.crafts.service.AdminService;

@Controller
@CrossOrigin(origins="*")
public class AdminController {

	@Autowired
	AdminService aservice;
	
	@GetMapping("/admins") //tested
	public ResponseEntity<List<Admin>> getAdmins() {
		return ResponseEntity.ok().body(aservice.getAdmins());
	}
	
	@PostMapping("/admins/{id}/new") //tested
	public ResponseEntity<Admin> createNewAdmin(@PathVariable String id, @RequestBody Admin admin) throws AdminNotFoundException {
		return ResponseEntity.ok().body(aservice.addNewAdmin(id, admin));
	}
	
	@PostMapping("/admin/{id}/post") //tested
	public ResponseEntity<AdminPost> createNewPost(@PathVariable String id, @RequestBody AdminPost post) throws AdminNotFoundException {
		
		return ResponseEntity.ok().body(aservice.makeNewPost(id, post));
	}
	
	@GetMapping("/adminpost/{id}") //tested
	public ResponseEntity<AdminPost> getPostById(@PathVariable String id) throws AdminPostNotFoundException {
		
		return ResponseEntity.ok().body(aservice.getPostById(id));
	}
	
	@DeleteMapping("/adminpost/{id}") //tested
	public ResponseEntity<AdminPost> deletePost(@PathVariable String id) throws AdminPostNotFoundException {
		return ResponseEntity.ok().body(aservice.deletePost(id));
	}
}
