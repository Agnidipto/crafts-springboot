package com.crafts.dto;

import java.util.List;

public class ResponseUser {

	String userId;
	String name;
	String email;
	List<ResponsePost> posts;
	ResponseFile profilePicture;
	
	//constructors
	
	public ResponseUser(String userId, String name, String email, List<ResponsePost> posts, ResponseFile profilePicture) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.posts = posts;
		this.profilePicture = profilePicture;
	}
	public ResponseUser() {
		super();
	}
	
	//getters and setters
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<ResponsePost> getPosts() {
		return posts;
	}
	public void setPosts(List<ResponsePost> posts) {
		this.posts = posts;
	}
	public ResponseFile getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(ResponseFile profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	
	
	
}
