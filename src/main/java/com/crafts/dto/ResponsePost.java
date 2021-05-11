package com.crafts.dto;

import java.util.Set;

public class ResponsePost {

	String postId;
	String name;
	String description;
	String postUrl;
	Set<ResponseFile> responseFile;
	
	//constructors
	
	public ResponsePost() {
		super();
	}

	public ResponsePost(String postId, String name, String description, String postUrl, Set<ResponseFile> responseFile) {
		super();
		this.postId = postId;
		this.name = name;
		this.description = description;
		this.postUrl = postUrl;
		this.responseFile = responseFile;
	}

	//getters and setters
	
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<ResponseFile> getResponseFile() {
		return responseFile;
	}
	public void setResponseFile(Set<ResponseFile> responseFile) {
		this.responseFile = responseFile;
	}
	
	
}
