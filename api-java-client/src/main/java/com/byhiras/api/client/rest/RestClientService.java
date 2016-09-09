package com.byhiras.api.client.rest;

import com.byhiras.api.model.BaseRequest;
import com.byhiras.api.model.BaseResponse;
import com.byhiras.rest.client.RestClient;

public class RestClientService <RES extends BaseResponse, REQ extends BaseRequest> {
	protected RestClient<RES, REQ> restClient;
	
	public RestClientService(RestClient<RES, REQ> restClient){
		if(restClient == null) throw new IllegalArgumentException("RestClient is null");
		this.restClient = restClient;
	}
}
