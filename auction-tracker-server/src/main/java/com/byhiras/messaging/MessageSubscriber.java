package com.byhiras.messaging;

import javax.jms.Message;

public interface MessageSubscriber {
	public void addSubscriber(final String name, final MessageHandler<?> handler);
	public void recieveMessage(final Message message);
}
