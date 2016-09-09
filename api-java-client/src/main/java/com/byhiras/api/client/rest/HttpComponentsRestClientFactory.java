package com.byhiras.api.client.rest;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import com.byhiras.api.model.RegisterRequest;
import com.byhiras.api.model.RegisterResponse;
import com.byhiras.api.model.ServerDetails;
import com.byhiras.marshalling.Marshalling;
import com.byhiras.rest.client.HttpComponentsRestClient;
import com.byhiras.rest.client.RestClient;
import com.byhiras.rest.client.RestPath;
public class HttpComponentsRestClientFactory implements HttpClientRestFactory{
	
	private HttpClient client;
	
	public HttpComponentsRestClientFactory(){
		this.client = HttpClients.createDefault();
	}
	
	public HttpComponentsRestClientFactory(HttpClient client){
		this.client = client;
	}	

	@Override
	public RestClient<RegisterResponse, RegisterRequest> registerClient(final ServerDetails serverDetails, final Marshalling marshalling) {
		return new HttpComponentsRestClient<RegisterResponse,RegisterRequest>(client, serverDetails, RestPath.root().getPath(RegisterResponse.class, "/register"), marshalling);
	}
}
