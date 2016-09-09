package com.byhiras.api.client;

import com.byhiras.api.client.rest.RestClientFactory;
import com.byhiras.api.model.ServerDetails;
import com.byhiras.api.service.RegisterAPIService;
import com.byhiras.marshalling.MarshallerFactory;

public abstract class ClientFactory {
	public enum ClientType{REST}
	public static RestClientFactory restClientFactory;
	
	public abstract RegisterAPIService getRegisterService();
	
	public static ClientFactory factoryInstance(final ClientType type, final ServerDetails serverDetails, final MarshallerFactory marshallerFactory){
		if(type == ClientType.REST && serverDetails != null && marshallerFactory != null){			
			if(restClientFactory == null){
				restClientFactory = new RestClientFactory(serverDetails, marshallerFactory);
			}
			return restClientFactory;
		}
		else{
			throw new IllegalArgumentException("Invalid arguments please see FGC documentation for details on how to construct a FGC client");
		}
	}	

}
