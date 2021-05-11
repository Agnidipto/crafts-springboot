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
@Table(name="comments")
public class Comments {

	@Id
	@Column(name="commentId")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String commentId;
	
	@Column
	private String content;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonIgnoreProperties({"likes", "comments", "user"})
	private Post post;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonIgnoreProperties({"posts", "madeComments", "receivedComments", "liked", "receivedLikes", "savedPosts", "profileImage"})
	private User commentedBy;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonIgnoreProperties({"posts", "madeComments", "receivedComments", "liked", "receivedLikes", "savedPosts", "profileImage"})
	private User commentedOn;

	
	//constructors
	
	
	
	public Comments(String commentId, String content, Post post, User commentedBy, User commentedOn) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.post = post;
		this.commentedBy = commentedBy;
		this.commentedOn = commentedOn;
	}
	
	public Comments() {
		super();
	}

	public Comments(String content, Post post, User commentedBy, User commentedOn) {
		super();
		this.content = content;
		this.post = post;
		this.commentedBy = commentedBy;
		this.commentedOn = commentedOn;
	}

	//getters and setters

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(User commentedBy) {
		this.commentedBy = commentedBy;
	}

	public User getCommentedOn() {
		return commentedOn;
	}

	public void setCommentedOn(User commentedOn) {
		this.commentedOn = commentedOn;
	}
	
	
}
