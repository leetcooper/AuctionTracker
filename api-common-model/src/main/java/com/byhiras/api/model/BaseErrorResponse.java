package com.byhiras.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class BaseErrorResponse extends BaseResponse{	
	@JsonInclude(Include.NON_NULL)
    public ErrorResponse errors;    
    
    public BaseErrorResponse(){

    }
    
    public BaseErrorResponse(ErrorResponse error){
        this.errors = error;        
    }

    public ErrorResponse getErrors() {
        return errors;
    }

    public void setErrors(ErrorResponse errors) {
        this.errors = errors;
    }
}
