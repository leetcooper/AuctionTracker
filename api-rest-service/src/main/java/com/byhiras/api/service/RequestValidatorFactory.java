package com.byhiras.api.service;

import com.byhiras.api.model.BaseRequest;

public interface RequestValidatorFactory {
	public <REQ extends BaseRequest>  RequestValidator<REQ> validatorInstance(final REQ requestType); 
}
