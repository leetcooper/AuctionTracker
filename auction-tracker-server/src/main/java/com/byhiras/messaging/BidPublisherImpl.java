package com.byhiras.messaging;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.byhiras.bid.model.Bid;


@Service
@Qualifier("BidPublisherImpl")
public class BidPublisherImpl extends BaseMessagePublisher<Bid> implements BidPublisher {

	private static final String MARKET_ID_PROPERTY_NAME = "lotNumber";
	private JmsMessagingConfiguration jmsMessagingConfiguration;

	@Inject
	public BidPublisherImpl(final ConfigurableApplicationContext context,
			@Qualifier("BidConfiguration") final JmsMessagingConfiguration jmsMessagingConfiguration) {
		super(context, jmsMessagingConfiguration.getFeedName());
		this.jmsMessagingConfiguration = jmsMessagingConfiguration;
	}

	public void publish(final Bid bid) {
		final AuctionSelectorMessage<Bid> message = new AuctionSelectorMessage<Bid>(bid,
				jmsMessagingConfiguration.getSelectorProperty(), jmsMessagingConfiguration.getSelectorSplit(),
				MARKET_ID_PROPERTY_NAME);
		super.publish(message);
	}
}
