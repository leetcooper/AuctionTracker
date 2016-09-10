package com.byhiras.ref.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.byhiras.ref.model.Catalog;
import com.byhiras.ref.model.RefId;

public interface CatalogRepository extends CrudRepository<Catalog, RefId>{
	@Override
    @Cacheable("catalogCache")
    public Catalog findOne(final RefId id);	
}
