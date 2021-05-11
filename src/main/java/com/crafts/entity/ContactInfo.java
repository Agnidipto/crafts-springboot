package com.crafts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="contactinfo")
public class ContactInfo {

	@Id
	@Column(name="contactInfoId")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String contactInfoId;
	
	@Column
	private String facebook;
	
	@Column
	private String twitter;
	
	@Column
	private String instagram;
	
	@Column
	private String pinterest;
	
	@Column
	private String phoneNumber;
	
	@Column
	private String whatsapp;
	
	@Column
	private String email;
	
	

	public ContactInfo() {
		super();
	}

	public ContactInfo(String contactInfoId, String facebook, String twitter, String instagram, String pinterest,
			String phoneNumber, String whatsapp, String email) {
		super();
		this.contactInfoId = contactInfoId;
		this.facebook = facebook;
		this.twitter = twitter;
		this.instagram = instagram;
		this.pinterest = pinterest;
		this.phoneNumber = phoneNumber;
		this.whatsapp = whatsapp;
		this.email=email;
	}

	public String getContactInfoId() {
		return contactInfoId;
	}

	public void setContactInfoId(String contactInfoId) {
		this.contactInfoId = contactInfoId;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getPinterest() {
		return pinterest;
	}

	public void setPinterest(String pinterest) {
		this.pinterest = pinterest;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
