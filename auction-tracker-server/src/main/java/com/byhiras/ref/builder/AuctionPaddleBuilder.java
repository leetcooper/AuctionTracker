package com.byhiras.ref.builder;

import static java.util.Objects.requireNonNull;

import com.byhiras.ref.model.AuctionPaddle;
import com.byhiras.ref.model.User;

public class AuctionPaddleBuilder {
	private User user;
	private Integer paddleNumber;
	
	public AuctionPaddleBuilder withUser(User user){
		this.user = user;
		return this;
	}
	
	public AuctionPaddleBuilder withPaddleNumber(Integer paddleNumber){
		this.paddleNumber = paddleNumber;
		return this;
	}
	
	public AuctionPaddle build(){
		requireNonNull(user);
		requireNonNull(paddleNumber);
		AuctionPaddle paddle = new AuctionPaddle();
		paddle.setUser(user);
		paddle.setPaddle(paddleNumber);
		return paddle;
	}
}
