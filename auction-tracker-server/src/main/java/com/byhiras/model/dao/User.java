package com.byhiras.model.dao;

import javax.persistence.EmbeddedId;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    @EmbeddedId
    @JsonIgnore
    protected Guid guid = GuidGenerator.generateGuid();	
    
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
