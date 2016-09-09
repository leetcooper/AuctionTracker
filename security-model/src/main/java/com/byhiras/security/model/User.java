package com.byhiras.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "ID")
	private Long id;
	
	@Column(name= "USERNAME", unique = true, nullable = false)	
	private String username;
	
	@Column(name= "REFERRAL_USERNAME")	
	private String referralUsername;		

	@Column(name= "EMAIL", unique = true, nullable = false)	
	private String email;

	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ENABLED")
	private Boolean enabled;
	
	@Column(name="FIRSTNAME")
	private String firstname;
	
	@Column(name="SURNAME")
	private String surname;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
		
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReferralUsername() {
		return referralUsername;
	}

	public void setReferralUsername(String referralUsername) {
		this.referralUsername = referralUsername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFullname() {
		return firstname + " " + surname;
	}
}
