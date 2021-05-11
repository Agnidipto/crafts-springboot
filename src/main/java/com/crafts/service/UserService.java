package com.crafts.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.crafts.dto.ResponseUser;
import com.crafts.entity.ContactInfo;
import com.crafts.entity.File;
import com.crafts.entity.Post;
import com.crafts.entity.User;
import com.crafts.repo.UserRepo;
import com.crafts.utils.UserUtil;
import com.crafts.exception.*;

@Transactional
@Service
public class UserService {

	@Autowired
	UserRepo urepo;
	
	@Autowired
	PostService pservice;
	
	public List<User> getAllUsers() {
		return urepo.findAll();
	}
	
	public User saveUser(User user) {
		return urepo.save(user);
	}
	
	public User getUserById(String id) throws UserNotFoundException {
		Optional<User> uop= urepo.findById(id);
		if(!uop.isPresent())
			throw new UserNotFoundException();
		return uop.get();
	}
	
	public User saveProfilePic(String id, MultipartFile multipartFile) throws UserNotFoundException, IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	    
	    File f=new File(fileName, multipartFile.getContentType(), multipartFile.getBytes());
		
	    Optional<User> uop= urepo.findById(id);
		if(!uop.isPresent())
			throw new UserNotFoundException();
		
		User user=uop.get();
		user.setProfileImage(f);
		return urepo.save(user);	
		
	}
	
	public User getUserByEmail(String email) throws UserNotFoundException{
		User user=urepo.findByEmail(email);
		if (user==null) throw new UserNotFoundException("User not found for given email");
		else return user;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Post> getAllPosts(String id) throws UserNotFoundException {
		Optional<User> uop= urepo.findById(id);
		if(!uop.isPresent())
			throw new UserNotFoundException();
		
		return uop.get().getPosts();
	}
	
	public User getUserInfo(String id) throws UserNotFoundException {
		User user=getUserById(id);
		return new User(id, user.getEmail(), user.getPassword(), user.getName(), user.getProfileImage(), null, user.getAbout());
	}
	//ResponseUsers
	
	public List<ResponseUser> getResponseUsers() {
		
		List<User> allUsers=urepo.findAll();
		
		List<ResponseUser> responseUsers= allUsers.stream().map(user -> UserUtil.convertToResponseUser(user)
		).collect(Collectors.toList());
		
		return responseUsers;
	}
	
	public ResponseUser getResponseUserById(String id) throws UserNotFoundException {
		User user=getUserById(id);
		return UserUtil.convertToResponseUser(user);
	}

	public User saveUserContactInfo(String id, ContactInfo contactInfo) throws UserNotFoundException {
		User user=getUserById(id);
		user.setContactInfo(contactInfo);
		return urepo.save(user);
	}
	
	public User saveUserAbout(String id,String about) throws UserNotFoundException {
		User user=getUserById(id);
		user.setAbout(about);
		return urepo.save(user);
	}

	public User updateUser(String id, User user) throws UserNotFoundException {
		User user1=getUserById(id);
		return user1.setUserInfo(user.getName(), user.getEmail(), user.getPassword(), user.getAbout());
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> saveSavedPost(String userId, String postId) throws UserNotFoundException, PostNotFoundException {
		User user=getUserById(userId);
		Post post=pservice.getPostById(postId);
		Set<Post> savedPosts=user.getSavedPosts();
		savedPosts.add(post);
		user.setSavedPosts(savedPosts);
		urepo.save(user);
		return (List<Post>) user.getSavedPosts();
		
	}
	
	public Set<Post> getSavedPost(String id) throws UserNotFoundException {
		User user=getUserById(id);
		return user.getSavedPosts();
	}
}
