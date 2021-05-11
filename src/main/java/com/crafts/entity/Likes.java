package com.crafts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="likes")
public class Likes {

	
	@Id
	@Column(name="likeId")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String likeId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonIgnoreProperties({"likes", "comments", "user"})
	private Post post;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonIgnoreProperties({"posts", "madeComments", "receivedComments", "liked", "receivedLikes", "savedPosts", "profileImage"})
	private User likedBy;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonIgnoreProperties({"posts", "madeComments", "receivedComments", "liked", "receivedLikes", "savedPosts", "profileImage"})
	private User likedOn;

	
	//constructors
	
	
	public Likes() {
		super();
	}

	public Likes(Post post, User likedBy, User likedOn) {
		super();
		this.post = post;
		this.likedBy = likedBy;
		this.likedOn = likedOn;
	}

	public Likes(String likeId, Post post, User likedBy, User likedOn) {
		super();
		this.likeId = likeId;
		this.post = post;
		this.likedBy = likedBy;
		this.likedOn = likedOn;
	}

	//getters and setters
	
	public String getLikeId() {
		return likeId;
	}

	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(User likedBy) {
		this.likedBy = likedBy;
	}

	public User getLikedOn() {
		return likedOn;
	}

	public void setLikedOn(User likedOn) {
		this.likedOn = likedOn;
	}
}
