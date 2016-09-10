package com.byhiras.messaging;

public interface MessagePublisher<T> {
	public void publish(AuctionMessage<T> message); 
}
