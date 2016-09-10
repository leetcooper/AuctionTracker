package com.byhiras.ref.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class AuctionPaddle implements RefItem {
	@EmbeddedId
	private RefId id = RefIdGenerator.getNextRefId();
    
    @ManyToOne
	private User user;

	private Integer paddle;
	    
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

	public RefId getId() {
		return id;
	}

	public void setId(RefId id) {
		this.id = id;
	}	
}
