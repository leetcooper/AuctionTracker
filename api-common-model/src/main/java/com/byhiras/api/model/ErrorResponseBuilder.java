package com.byhiras.api.model;

import java.util.HashSet;
import java.util.Set;

public class ErrorResponseBuilder {
	final Set<ErrorResponseEnum> enums = new HashSet<ErrorResponseEnum>();

    public ErrorResponseBuilder addErrors(final Set<ErrorResponseEnum> errors) {
    	enums.addAll(errors);    
    	return this;
    }
            
    public ErrorResponseBuilder addError(final ErrorResponseEnum e) {
    	enums.add(e);
    	return this;
    }   
    
    public ErrorResponse build(){
        ErrorResponse errorResponse = null;

        if (enums != null) {
            errorResponse = new ErrorResponse();

            for (final ErrorResponseEnum e : enums) {
                ErrorEntry errorEntry = getErrorEntry(e);
                errorResponse.getErrors().add(errorEntry);
            }
        }

        return errorResponse;    	
    }

    private static ErrorEntry getErrorEntry(final ErrorResponseEnum e) {
        ErrorEntry errorEntry = null;
        errorEntry = new ErrorEntry();
        ErrorResponseEnum errorResponseEnum = (ErrorResponseEnum) e;
        errorEntry.setCode(errorResponseEnum.toString());
        errorEntry.setSystemMessage(errorResponseEnum.getSystemMessage());
        return errorEntry;
    }
}
