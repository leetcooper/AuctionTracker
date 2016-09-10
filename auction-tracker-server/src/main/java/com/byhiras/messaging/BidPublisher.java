package com.byhiras.messaging;

import com.byhiras.bid.model.Bid;

public interface BidPublisher {
	void publish(final Bid bid);
}
