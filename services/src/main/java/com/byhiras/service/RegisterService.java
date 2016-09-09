package com.byhiras.service;

import com.byhiras.security.model.User;
import com.byhiras.service.exception.UserExistsException;

public interface RegisterService {
	public User register(final String username, final String password, final String email, final String referralUser) throws UserExistsException;
}
