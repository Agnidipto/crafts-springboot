package com.crafts.entity;

import java.util.Date;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="post")
public class Post {

	@Id
	@Column(name="postId")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String postId;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="files")
	private Set<File> pictures;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonIgnoreProperties({"posts", "madeComments", "receivedComments", "liked", "receivedLikes", "savedPosts", "profileImage"})
	private User user;
	
	@OneToMany(mappedBy="post", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonIgnoreProperties("post")
	private Set<Comments> comments;
	
	@OneToMany(mappedBy="post", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonIgnoreProperties("post")
	private Set<Likes> likes;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Etc/UTC")
    private Date datetime;
	
	//constructors
	
	public Post() {}

	public Post(String postId, String name, String description, Set<File> pictures) {
		super();
		this.postId = postId;
		this.name = name;
		this.description = description;
		this.pictures = pictures;
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

	public Set<File> getPictures() {
		return pictures;
	}

	public void setPictures(Set<File> pictures) {
		this.pictures = pictures;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	public Set<Likes> getLikes() {
		return likes;
	}

	public void setLikes(Set<Likes> likes) {
		this.likes = likes;
	}
	
	
}
