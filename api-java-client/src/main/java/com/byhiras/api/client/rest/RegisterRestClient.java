package com.byhiras.api.client.rest;

import com.byhiras.api.model.RegisterRequest;
import com.byhiras.api.model.RegisterResponse;
import com.byhiras.api.service.RegisterAPIService;
import com.byhiras.rest.client.RestClient;

public class RegisterRestClient extends RestClientService<RegisterResponse, RegisterRequest> implements RegisterAPIService{

	public RegisterRestClient(RestClient<RegisterResponse, RegisterRequest> registerRestClient){
		super(registerRestClient);		
	}
	
	public RegisterResponse register(final RegisterRequest register) {
		return restClient.post(register);
	}

}
