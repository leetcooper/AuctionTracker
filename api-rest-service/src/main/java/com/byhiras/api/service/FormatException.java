package com.byhiras.api.service;

import java.util.Set;
import javax.ws.rs.core.Response.Status;

import com.byhiras.api.model.ErrorResponseBuilder;
import com.byhiras.api.model.ErrorResponseEnum;
import com.byhiras.api.model.SimpleErrorResponse;

public class FormatException extends RestException{ 
	private static final long serialVersionUID = 1L;

	public FormatException(final Set<ErrorResponseEnum> errors){		       
        super(new SimpleErrorResponse(new ErrorResponseBuilder().addErrors(errors).build()), Status.BAD_REQUEST);
    }
}
