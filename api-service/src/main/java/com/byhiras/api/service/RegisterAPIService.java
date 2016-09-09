package com.byhiras.api.service;

import com.byhiras.api.model.RegisterRequest;
import com.byhiras.api.model.RegisterResponse;

public interface RegisterAPIService {
	public RegisterResponse register(final RegisterRequest register);
}
