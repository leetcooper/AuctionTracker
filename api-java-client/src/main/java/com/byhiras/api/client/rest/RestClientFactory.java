package com.byhiras.api.client.rest;

import com.byhiras.api.client.ClientFactory;
import com.byhiras.api.model.ServerDetails;
import com.byhiras.api.service.RegisterAPIService;
import com.byhiras.marshalling.MarshallerFactory;
import com.byhiras.marshalling.Marshalling;

public class RestClientFactory extends ClientFactory {
	private final ServerDetails serverDetails;
	private final MarshallerFactory marshallerFactory;
	private RegisterAPIService registerAPIService;	
	private Marshalling jsonResponseRequestMarshaller;
	
	public RestClientFactory(final ServerDetails serverDetails, final MarshallerFactory marshallerFactory){
		this.serverDetails = serverDetails;
		this.marshallerFactory = marshallerFactory;
	}

	@Override
	public RegisterAPIService getRegisterService() {
		if(registerAPIService == null){
			registerAPIService = new RegisterRestClient(HttpClientRestFactory.getDefault().registerClient(serverDetails, jsonResponseRequestMarshaller()));
		}
		return registerAPIService;		
	}

	private Marshalling jsonResponseRequestMarshaller() {
		if(jsonResponseRequestMarshaller == null){
			jsonResponseRequestMarshaller = new Marshalling(marshallerFactory.getJsonMarshaller(), marshallerFactory.getJsonMarshaller());
		}		
		return jsonResponseRequestMarshaller;
	}
	
}
