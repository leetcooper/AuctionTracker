package com.byhiras.bid.repo;

import org.springframework.data.repository.CrudRepository;

import com.byhiras.bid.model.Guid;
import com.byhiras.bid.model.LotBids;

public interface LotBidsRepository extends CrudRepository<LotBids, Guid>{	
	public LotBids findDistinctByLotIdAndVersionCurrent(Long lotId, Boolean current);
}
