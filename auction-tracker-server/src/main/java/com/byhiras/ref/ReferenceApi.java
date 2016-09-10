package com.byhiras.ref;

import org.springframework.data.repository.CrudRepository;

import com.byhiras.ref.model.RefId;
import com.byhiras.ref.model.RefItem;

public interface ReferenceApi {
	public <T extends RefItem> T getById(final Class<T> t, final RefId id);
	public <T extends RefItem> CrudRepository<? extends RefItem, RefId> getRepository(final Class<T> t);	
}
