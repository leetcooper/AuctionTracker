package com.byhiras.messaging;

public class AuctionMessage<T> {	
	private T entity;
	
	public AuctionMessage(final T t){
		this.entity = t;
	}
	
	public T getEntity(){
		return entity;
	}
}
