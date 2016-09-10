package com.byhiras.ref.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.byhiras.ref.model.RefId;
import com.byhiras.ref.model.User;

public interface UserRepository extends CrudRepository<User, RefId>{
	@Override
    @Cacheable("userCache")
    public User findOne(final RefId id);
	
    @Cacheable("userCache")
    public User findByUsername(final String username);	
}
