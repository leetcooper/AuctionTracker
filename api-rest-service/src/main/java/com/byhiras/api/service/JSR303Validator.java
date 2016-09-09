package com.byhiras.api.service;

import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.stereotype.Component;
import com.byhiras.api.model.ErrorResponseEnum;

@Component
public class JSR303Validator {	
	final private Validator validator; 
	
	@Inject
	public JSR303Validator(final Validator validator){
        this.validator = validator;
    }	
	
    @SuppressWarnings("rawtypes")
    public boolean validate(final Class enumClass, final Set<ErrorResponseEnum> errros, final Object... objs) {
    	if(objs == null) throw new IllegalArgumentException("Request array specified is null cannot validated null array"); 
        boolean valid = true;

        for (Object obj : objs) {
        	if(obj == null) throw new IllegalArgumentException("Item in array specified is null cannot validated null object"); 
            validate(enumClass, obj, errros);
        }

        if (errros.size() > 0) {
            valid = false;
        }

        return valid;
    }

    @SuppressWarnings({"unchecked"})
    private void validate(@SuppressWarnings("rawtypes") final Class enumClass, final Object object, final Set<ErrorResponseEnum> enums) {
        final Set<ConstraintViolation<Object>> violations = validator.validate(object);
        
        Iterator<ConstraintViolation<Object>> iterator = violations.iterator();
        while (iterator.hasNext()) {
            String m = iterator.next().getMessage();
            enums.add((ErrorResponseEnum)Enum.valueOf(enumClass, m));
        }                                        
    }
}
