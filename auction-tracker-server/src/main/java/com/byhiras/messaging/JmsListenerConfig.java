package com.byhiras.messaging;

import java.util.Collection;

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;

@Configuration
public class JmsListenerConfig implements JmsListenerConfigurer {
	private static Logger LOG = LoggerFactory.getLogger(JmsListenerConfig.class);
	
	@Autowired
	private MessageSubscriber subscribers;
	
	@Autowired
	private Collection<JMSListenerConfiguration> jmsListenerConfiguration;
	
	@Autowired
	private ConnectionFactory connectionFactory;

	@Override
	public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {		
		jmsListenerConfiguration.forEach(c -> {
			for(int i = 0; i < c.getJmsMessagingConfiguration().getSelectorSplit(); i++){
				final SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
				final String instanceName = c.getJmsMessagingConfiguration().getFeedName() + i;
				endpoint.setId(instanceName);
				endpoint.setDestination(c.getJmsMessagingConfiguration().getFeedName());
				endpoint.setSelector(c.getJmsMessagingConfiguration().getSelectorProperty() + " = " + i);
				endpoint.setConcurrency(c.getJmsMessagingConfiguration().getConcurrency());				
				endpoint.setMessageListener(message -> {
					subscribers.recieveMessage(message);
				});
				LOG.info("Registering JMS Listner [{}]", c.getJmsMessagingConfiguration());
				if(c.getJmsMessagingConfiguration().isDurable()){
					endpoint.setSubscription(instanceName+"-subscription");
					registrar.registerEndpoint(endpoint, createDurableFactory(connectionFactory, instanceName+"-clientId"));
				}
				else{
					registrar.registerEndpoint(endpoint, createNonDurableFactory(connectionFactory));
				}				
			}
		});
		
	}

	private JmsListenerContainerFactory<?> createNonDurableFactory(ConnectionFactory conntectionFactory) {
    	DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);  
        factory.setPubSubDomain(true);
        return factory;
	}	
	
	private JmsListenerContainerFactory<?> createDurableFactory(ConnectionFactory conntectionFactory, final String clientId) {
    	DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);  
        factory.setPubSubDomain(true);
        factory.setSubscriptionDurable(Boolean.TRUE);
        factory.setClientId(clientId);        
        return factory;
	}
}
