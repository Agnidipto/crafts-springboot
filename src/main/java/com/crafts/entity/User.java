package com.crafts.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="userId")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String userId;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String name;
	
	@Column
	private String about;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private ContactInfo contactInfo;
	
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="profileImage")
	private File profileImage; 
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Post> posts;
	
	@OneToMany(mappedBy="commentedBy", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Comments> madeComments;
	
	@OneToMany(mappedBy="commentedOn", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Comments> receivedComments;
	
	@OneToMany(mappedBy="likedBy", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Likes> liked;
	
	@OneToMany(mappedBy="likedOn", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Likes> receivedLikes;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="saved_posts")
	private Set<Post> savedPosts;
	
	
	//constructors
	
	public User() {}

	public User(String userId, String email, String password, String name, File profileImage, Set<Post> posts, String about) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.profileImage = profileImage;
		this.posts = posts;
		this.about=about;
	}
	
	//getters and setters

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(File profileImage) {
		this.profileImage = profileImage;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	public Set<Post> getSavedPosts() {
		return savedPosts;
	}

	public void setSavedPosts(Set<Post> savedPosts) {
		this.savedPosts = savedPosts;
	}

	public User setUserInfo(String name, String email, String password, String about ) {
		
		this.email = email;
		this.password = password;
		this.name = name;
		this.about=about;
		
		return this;
	}
	
	public Set<Comments> getMadeComments() {
		return madeComments;
	}

	public void setMadeComments(Set<Comments> madeComments) {
		this.madeComments = madeComments;
	}

	public Set<Comments> getReceivedComments() {
		return receivedComments;
	}

	public void setReceivedComments(Set<Comments> receivedComments) {
		this.receivedComments = receivedComments;
	}

	public Set<Likes> getLiked() {
		return liked;
	}

	public void setLiked(Set<Likes> liked) {
		this.liked = liked;
	}

	public Set<Likes> getReceivedLikes() {
		return receivedLikes;
	}

	public void setReceivedLikes(Set<Likes> receivedLikes) {
		this.receivedLikes = receivedLikes;
	}
	
	
	
}
