package com.byhiras.ref.builder;

import static java.util.Objects.requireNonNull;

import com.byhiras.ref.model.User;

public class UserBuilder {
	private String username;
	
	public UserBuilder withUsername(final String username){
		this.username = username;
		return this;
	}
		
	public User build(){
		requireNonNull(username);	
		final User user = new User();
		user.setUsername(username);
		return user;
	}
}
