package com.crafts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crafts.entity.Admin;
import com.crafts.entity.AdminPost;
import com.crafts.repo.AdminPostRepo;
import com.crafts.repo.AdminRepo;
import com.crafts.exception.*;

@Service
public class AdminService {

	@Autowired
	AdminRepo arepo;
	
	@Autowired
	AdminPostRepo aprepo;
	
	//Admin
	
	public List<Admin> getAdmins() {
	
		return arepo.findAll();
	}
	
	public Admin getAdminById(String adminId) throws AdminNotFoundException {
		Optional<Admin> admin= arepo.findById(adminId);
		if(!admin.isPresent())
			throw new AdminNotFoundException();
		else return admin.get();
	}
	
	public Admin addNewAdmin(String adminId, Admin admin) throws AdminNotFoundException {
		if(!getAdmins().isEmpty())
			getAdminById(adminId);
		
		return arepo.save(admin);
	}
	
	//Admin Posts
	
	public AdminPost makeNewPost(String adminId, AdminPost adminPost) throws AdminNotFoundException {
		Admin admin=getAdminById(adminId);
		adminPost.setAdmin(admin);
		return aprepo.save(adminPost);
		
	}
	
	public AdminPost getPostById(String id) throws AdminPostNotFoundException {
		Optional<AdminPost> apop=aprepo.findById(id);
		if(!apop.isPresent())
			throw new AdminPostNotFoundException();
		else return apop.get();
	}
	
	public AdminPost deletePost(String postId) throws AdminPostNotFoundException {
		AdminPost apost=getPostById(postId);
		Admin admin=apost.getAdmin();
		admin.getAdminPost().remove(apost);
		arepo.saveAndFlush(admin);
		aprepo.delete(apost);
		return apost;
	}
}
