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
@Table(name="adminPost")
public class AdminPost {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String adminPostId;
	
	@Column
	private String content;
	
	@Column
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonIgnoreProperties("adminPost")
	private Admin admin;

	public String getAdminPostId() {
		return adminPostId;
	}

	public void setAdminPostId(String adminPostId) {
		this.adminPostId = adminPostId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
