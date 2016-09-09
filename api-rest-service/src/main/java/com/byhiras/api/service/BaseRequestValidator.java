package com.byhiras.api.service;

import java.util.HashSet;
import java.util.Set;

import com.byhiras.api.model.BaseRequest;
import com.byhiras.api.model.ErrorResponseEnum;

public class BaseRequestValidator {
	final JSR303Validator jsr303Validator;
	private Set<ErrorResponseEnum> errros = new HashSet<ErrorResponseEnum>();
	private Class<? extends ErrorResponseEnum> enumClass;
	private BaseRequest request;
	
	public BaseRequestValidator(final JSR303Validator jsr303Validator, final Class<? extends ErrorResponseEnum> enumClass, final BaseRequest request){
		this.jsr303Validator = jsr303Validator;
		this.request = request;
		this.enumClass = enumClass;
	}	
	
	public boolean validated() {
		return jsr303Validator.validate(enumClass, errros, request);
	}
	
	public Set<ErrorResponseEnum> getErrors() {
		return errros;
	}
}
