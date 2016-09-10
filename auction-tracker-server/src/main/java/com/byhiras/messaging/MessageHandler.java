package com.byhiras.messaging;

public interface MessageHandler<T> {
	public Class<T> getType();
	public void handleMessage(T t);
}
