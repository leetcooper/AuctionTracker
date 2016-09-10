package com.byhiras.messaging;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSMessagingBidConfigurationConfig {

	private static final String BID_TOPIC_NAME = "bid-event";
	private static final String BID_SELECTOR_PROPERTY = "lotNumber";

	@Value("${bid.threads:1}")
	private int bidThreads;
	
	@Bean
	@Qualifier("BidConfiguration")
	public JmsMessagingConfiguration geBidConfiguration() {
		return new JmsMessagingConfiguration(bidThreads, BID_TOPIC_NAME, BID_SELECTOR_PROPERTY, true);
	}		
}
