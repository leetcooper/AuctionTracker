package com.byhiras.security.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.byhiras.security.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	public User findByEmail(final String email);
	public User findByUsername(final String username);

}
