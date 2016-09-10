package com.byhiras.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSListenerBidConfigurationConfig {
	
	@Autowired
	@Qualifier("BidConfiguration")
	private JmsMessagingConfiguration bidMessagingConfiguration;
	
	@Bean
	public JMSListenerConfiguration getJMSListenerAssetOrderCreatedUpdatedConfiguration() {
		return new JMSListenerConfiguration(){
			@Override
			public JmsMessagingConfiguration getJmsMessagingConfiguration() {
				return bidMessagingConfiguration;
			}			
		};
	}		
}
