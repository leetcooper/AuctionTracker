package com.byhiras.api.service.register;

import javax.ws.rs.core.Response.Status;

import com.byhiras.api.model.ErrorResponseBuilder;
import com.byhiras.api.model.SimpleErrorResponse;
import com.byhiras.api.model.UserError;
import com.byhiras.api.service.RestException;
import jersey.repackaged.com.google.common.collect.Sets;

public class RegisterUserDoesNotExistException extends RestException{ 
	private static final long serialVersionUID = 1L;

	public RegisterUserDoesNotExistException(){		       		
        super(new SimpleErrorResponse(new ErrorResponseBuilder().addErrors(Sets.newHashSet(UserError.UE02)).build()), Status.BAD_REQUEST);
    }
}
