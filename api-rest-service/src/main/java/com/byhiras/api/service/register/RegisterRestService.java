package com.byhiras.api.service.register;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.byhiras.api.model.RegisterRequest;
import com.byhiras.api.model.RegisterResponse;
import com.byhiras.api.service.RegisterAPIService;
import com.byhiras.api.service.RequestValidator;
import com.byhiras.api.service.RequestValidatorFactory;
import com.byhiras.service.RegisterService;
import com.byhiras.service.exception.UserDoesNotExistException;
import com.byhiras.service.exception.UserExistsException;

@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("/register")
public class RegisterRestService implements RegisterAPIService{
	final RequestValidatorFactory validatorFactory;
	final RegisterService registerService;
	
	@Inject
	public RegisterRestService(final RequestValidatorFactory validatorFactory, final RegisterService registerService){
		this.validatorFactory = validatorFactory;
		this.registerService = registerService;
	}
	
    @POST
    @Transactional     
	public RegisterResponse register(final RegisterRequest register) {		
		final RequestValidator<RegisterRequest> registerRequestFormatValidator = validatorFactory.validatorInstance(register);
		
		if(registerRequestFormatValidator.validated()){
			try{
				RegisterResponse registerResponse = new RegisterResponse();
				registerResponse.setEmail(registerService.register(register.getUsername(), register.getPassword(),register.getEmail(), register.getReferralUsername()).getEmail());
				return registerResponse;
			}
			catch(UserExistsException e){
				throw new RegisterUserExistException();
			}
			catch(UserDoesNotExistException e){
				throw new RegisterUserDoesNotExistException();
			}			
		}
		else{
			throw registerRequestFormatValidator.getRestException();		
		}
	}	
}
