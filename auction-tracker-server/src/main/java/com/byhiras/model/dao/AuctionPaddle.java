package com.byhiras.model.dao;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AuctionPaddle {
    @EmbeddedId
    @JsonIgnore
    protected Guid guid = GuidGenerator.generateGuid();	
    
	private Integer paddle;
	private User user;

	public Integer getPaddle() {
		return paddle;
	}

	public void setPaddle(Integer paddle) {
		this.paddle = paddle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
