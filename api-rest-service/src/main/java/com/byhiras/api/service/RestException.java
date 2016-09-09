package com.byhiras.api.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.byhiras.api.model.BaseErrorResponse;
import com.byhiras.api.model.ErrorResponseEnum;

public class RestException extends WebApplicationException{
	private static final long serialVersionUID = 1L;
	public final BaseErrorResponse response;

    public RestException(final BaseErrorResponse response, final Status status) {    
        super(Response.status(status)
                .entity(response).type(MediaType.APPLICATION_JSON).build());
        this.response = response;
    }

	public boolean containError(final ErrorResponseEnum error) {
        return response.getErrors().containsCode(error);
    }

	public BaseErrorResponse getBaseErrorResponse() {
		return response;
	}    
}
