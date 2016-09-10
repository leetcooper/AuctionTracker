package com.byhiras.messaging;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

@Component
public class BaseSubscriber implements MessageSubscriber{
	private static Logger LOG = LoggerFactory.getLogger(BaseSubscriber.class);
	protected final List<MessageHandler<?>> handlers = new CopyOnWriteArrayList<>();
	
	public void addSubscriber(final String name, final MessageHandler<?> handler) {
		LOG.info("Adding subscriber [{}]", name);
		handlers.add(handler);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void recieveMessage(final Message message){
		final MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		messageConverter.setTypeIdPropertyName("class");
		messageConverter.setTargetType(MessageType.TEXT);
		try{
			Object obj = messageConverter.fromMessage(message);
			LOG.trace("Recieved message [{}] - ThreadId [{}]", obj, Thread.currentThread().getId());
			boolean handlerExists = false;
			for(MessageHandler handler : handlers){
				handlerExists = true;
				if(handler.getType().isAssignableFrom(obj.getClass())){
					handler.handleMessage(obj);
				}
			}
			if(!handlerExists){
				LOG.warn("No handler for message [{}] - ThreadId [{}]", obj, Thread.currentThread().getId());
			}
		}
		catch(JMSException exc){
			exc.printStackTrace();
		}
	}	
}
