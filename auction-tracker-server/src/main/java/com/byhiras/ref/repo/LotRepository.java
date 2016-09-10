package com.byhiras.ref.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.byhiras.ref.model.Lot;
import com.byhiras.ref.model.RefId;

public interface LotRepository extends CrudRepository<Lot, RefId>{
	@Override
    @Cacheable("lotCache")
    public Lot findOne(final RefId id);	
	
    @Cacheable("lotCache")
    public Lot findByLotNumber(final Integer id);	
}
