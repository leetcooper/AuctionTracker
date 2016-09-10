package com.byhiras.ref.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.byhiras.ref.model.AuctionPaddle;
import com.byhiras.ref.model.RefId;

public interface AuctionPaddleRepository extends CrudRepository<AuctionPaddle, RefId>{
	@Override
    @Cacheable("auctionPaddleCache")
    public AuctionPaddle findOne(final RefId id);	
}
