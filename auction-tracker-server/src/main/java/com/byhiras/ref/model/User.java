package com.byhiras.ref.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class User implements RefItem{
	@EmbeddedId
	private RefId id = RefIdGenerator.getNextRefId();
    
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public RefId getId() {
		return id;
	}

	public void setId(RefId id) {
		this.id = id;
	}
	
}
