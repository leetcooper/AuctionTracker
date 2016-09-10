package com.byhiras.messaging;

import com.byhiras.model.bid.Bid;

public interface BidPublisher {
	void publish(final Bid bid);
}
