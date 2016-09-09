package com.byhiras.api.client.rest;

import com.byhiras.api.model.RegisterRequest;
import com.byhiras.api.model.RegisterResponse;
import com.byhiras.api.model.ServerDetails;
import com.byhiras.marshalling.Marshalling;
import com.byhiras.rest.client.RestClient;

public interface HttpClientRestFactory {	
	public RestClient<RegisterResponse, RegisterRequest> registerClient(final ServerDetails serverDetails, final Marshalling marshalling);
	
	static HttpClientRestFactory getDefault(){
		if(System.getProperty("http.client.factory") == null){
			return new HttpComponentsRestClientFactory();
		}
		else{
			try {
				return (HttpClientRestFactory)Class.forName(System.getProperty("http.client.factory")).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {				
				return new HttpComponentsRestClientFactory();
			}
		}
	}	

}
