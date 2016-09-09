package com.byhiras.rest.client;

import com.byhiras.api.model.BaseResponse;
import com.byhiras.api.model.VoidResponse;

public class RestPath<RES extends BaseResponse> {
	
	public final String path;
	public final Class<RES> responseType;
	
	public RestPath(final Class<RES> responseType, final String path){
		this.path = path;
		this.responseType = responseType;		
	}

	public <CHILDRES extends BaseResponse> RestPath<CHILDRES> getPath(final Class<CHILDRES> responseType, final String path){
		if(path == null) throw new IllegalArgumentException("Path is null");
		if(responseType == null) throw new IllegalArgumentException("Response Type is null");
		return new RestPath<CHILDRES>(responseType, this.path.concat(path));
	}
	
	public String path(){
		return this.path;
	}

	public Class<? extends BaseResponse> getResponseType() {
		return responseType;
	}

	public static RestPath<VoidResponse> root() {		
		return new RestPath<>(VoidResponse.class, "/tradeserver/services");
	}
}
