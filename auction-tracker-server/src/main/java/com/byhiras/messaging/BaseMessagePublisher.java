package com.byhiras.messaging;

import static org.apache.commons.beanutils.PropertyUtils.getProperty;

import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

public abstract class BaseMessagePublisher<T> implements MessagePublisher<T> {
	private static Logger LOG = LoggerFactory.getLogger(BaseMessagePublisher.class);
	final ConfigurableApplicationContext context;
	private final String destinationName;

	public BaseMessagePublisher(final ConfigurableApplicationContext context, final String destinationName) {
		this.context = context;
		this.destinationName = destinationName;
	}

	@Override
	public void publish(final AuctionMessage<T> auctionMessage) {
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		final MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		messageConverter.setTargetType(MessageType.TEXT);
		jmsTemplate.setMessageConverter(messageConverter);
		jmsTemplate.setPubSubDomain(true);
		LOG.trace("Sending message to destination [{}] entity[{}]", destinationName, auctionMessage.getEntity());
		jmsTemplate.convertAndSend(destinationName, auctionMessage.getEntity(), new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws JMSException {
				message.setStringProperty("class", auctionMessage.getEntity().getClass().getCanonicalName());

				if (auctionMessage instanceof AuctionSelectorMessage) {
					@SuppressWarnings("rawtypes")
					final AuctionSelectorMessage selector = (AuctionSelectorMessage) auctionMessage;
					message.setIntProperty(selector.getKey(), modProperty(selector));
				}

				return message;
			}		
		});
		LOG.trace("Sent message to destination [{}] entity[{}]", destinationName, auctionMessage.getEntity());
	}
	
	private Integer modProperty(@SuppressWarnings("rawtypes") AuctionSelectorMessage message) {
		try {
			final Integer numberToMod = getProperty(message.getEntity(), message.getProperty()).toString()
					.chars().sum();
			return numberToMod % message.getProcesses();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	
}
