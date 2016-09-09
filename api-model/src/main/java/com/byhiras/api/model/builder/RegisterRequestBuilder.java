package com.byhiras.api.model.builder;

import com.byhiras.api.model.RegisterRequest;

public class RegisterRequestBuilder {
	private String username = "";
	private String email = "";
	private String password = "";
	private String referralUsername = null;
	
	public RegisterRequestBuilder withUsername(final String username){
		this.username = username;
		return this;
	}
	
	public RegisterRequestBuilder withEmail(final String email){
		this.email = email;
		return this;
	}

	public RegisterRequestBuilder withPassword(final String password){
		this.password = password;
		return this;
	}
	
	public RegisterRequestBuilder withReferralUsername(String referralUsername) {
		this.referralUsername = referralUsername;
		return this;
	}	
	
	public RegisterRequest build(){
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setUsername(username);
		registerRequest.setEmail(email);
		registerRequest.setPassword(password);
		registerRequest.setReferralUsername(referralUsername);
		return registerRequest;
	}
}
