package com.byhiras.api.service.register;

import com.byhiras.api.model.RegisterFormatError;
import com.byhiras.api.model.RegisterRequest;
import com.byhiras.api.service.BaseRequestValidator;
import com.byhiras.api.service.FormatException;
import com.byhiras.api.service.JSR303Validator;
import com.byhiras.api.service.RequestValidator;
import com.byhiras.api.service.RestException;

public class RegisterRequestValidator extends BaseRequestValidator implements RequestValidator<RegisterRequest>{
	public RegisterRequestValidator(final JSR303Validator jsr303Validator, final RegisterRequest registerRequest){
		super(jsr303Validator, RegisterFormatError.class, registerRequest);
	}

	@Override
	public RestException getRestException() {
		return new FormatException(getErrors());
	}	
}
