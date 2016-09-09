package com.byhiras.api.service;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.byhiras.api.model.BaseRequest;
import com.byhiras.api.model.RegisterRequest;
import com.byhiras.api.service.register.RegisterRequestValidator;

@Component
public class RequestValidatorFactoryImpl implements RequestValidatorFactory{
	
	private JSR303Validator jsr303Validator;
		
	@Inject
	public RequestValidatorFactoryImpl(final JSR303Validator jsr303Validator){
		this.jsr303Validator = jsr303Validator;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <REQ extends BaseRequest> RequestValidator<REQ> validatorInstance(final REQ request) {
		if(request == null)throw new IllegalArgumentException("Null request cannot be mapped to validator type");
		if(request.getClass() == RegisterRequest.class){
			return (RequestValidator<REQ>) new RegisterRequestValidator(jsr303Validator, (RegisterRequest)request);
		}
		else{
			throw new IllegalArgumentException("Unknown request cannot be mapped to validator [" + request.getClass().getCanonicalName() + "]");
		}
	}

}
