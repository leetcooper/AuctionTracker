package com.byhiras.rest.client;

import com.byhiras.api.model.BaseRequest;
import com.byhiras.api.model.BaseResponse;

public interface RestClient<RES extends BaseResponse, REQ extends BaseRequest> {	
	public RES post(final REQ r);
	public RES get();
	public <CHILDRES extends BaseResponse, CHILDREQ  extends BaseRequest> RestClient<CHILDRES, CHILDREQ> getPath(final Class<CHILDRES> responseType, final String path);
	public void addHeader(String key, String value);
	public void addResponseMutator(final StringMutator mutator);
	public RES delete();
}
