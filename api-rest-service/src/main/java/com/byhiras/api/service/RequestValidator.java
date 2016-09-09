package com.byhiras.api.service;

import com.byhiras.api.model.BaseRequest;

public interface RequestValidator<T extends BaseRequest> {
	public boolean validated();
	public RestException getRestException();
}
