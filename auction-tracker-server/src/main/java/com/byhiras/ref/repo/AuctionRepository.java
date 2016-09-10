package com.byhiras.ref.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.byhiras.ref.model.Auction;
import com.byhiras.ref.model.RefId;

public interface AuctionRepository extends CrudRepository<Auction, RefId>{
	@Override
    @Cacheable("auctionCache")
    public Auction findOne(final RefId id);
	
    @Cacheable("auctionCache")
    public Auction findByName(final String name);		
}
