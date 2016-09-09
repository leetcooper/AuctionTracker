package com.byhiras.api.service;

import javax.ws.rs.core.Response.Status;

import com.byhiras.api.model.ErrorResponseBuilder;
import com.byhiras.api.model.ErrorResponseEnum;
import com.byhiras.api.model.SimpleErrorResponse;

public class VersionException extends RestException{ 
	private static final long serialVersionUID = 1L;

	public VersionException(final String message){		       
        super(new SimpleErrorResponse(new ErrorResponseBuilder().addError(new ErrorResponseEnum() {
			@Override
			public String getSystemMessage() {
				return message;
			}
		}).build())
		, Status.BAD_REQUEST);
    }
}
