package com.byhiras.service;

import com.byhiras.bid.model.LotBids;

public interface BiddingService {
	public void openBiddingForLot(final Integer lot);
	public LotBids findLotBidsByLot(final Integer lot);
}
