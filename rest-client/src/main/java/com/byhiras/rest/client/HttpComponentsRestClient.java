package com.byhiras.rest.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.byhiras.api.model.BaseRequest;
import com.byhiras.api.model.BaseResponse;
import com.byhiras.api.model.ServerDetails;
import com.byhiras.marshalling.Marshalling;

public class HttpComponentsRestClient<RES extends BaseResponse, REQ extends BaseRequest> implements RestClient<RES, REQ>{
	private static Logger LOG = LoggerFactory.getLogger(HttpComponentsRestClient.class); 
	private final HttpClient client;
	private final Map<String, String> customHeaders = new HashMap<String, String>();	
	private final RestPath<RES> pathDetails;
	private final Marshalling marshalling;
	private final String path;
	private final AuthTokenProvider tokenProvider;
	private final ServerDetails serverDetails;
	private StringMutator responseMutator;
	
	public HttpComponentsRestClient(final HttpClient httpClient, final ServerDetails serverDetails, final RestPath<RES> pathDetails, final Marshalling marshalling) {		
		this(httpClient, serverDetails, pathDetails, marshalling, null);
	}	
	
	public HttpComponentsRestClient(final HttpClient client, final ServerDetails serverDetails, final RestPath<RES> pathDetails, final Marshalling marshalling, final AuthTokenProvider tokenProvider) {
		if(serverDetails == null) throw new IllegalArgumentException("Server details is null");
		if(pathDetails == null) throw new IllegalArgumentException("Path Details details is null");
		if(marshalling == null) throw new IllegalArgumentException("Marshalling is null");		
		this.client = client;
		this.pathDetails = pathDetails;
		this.marshalling = marshalling;
		this.path = serverDetails.path(pathDetails.path());
		this.tokenProvider = tokenProvider;
		this.serverDetails = serverDetails;
	}	
	
	@Override
	public RES post(REQ r) {
		HttpPost post = new HttpPost(path);
		setHeaders(post);		
		try {
			setContent(post, r);			
			HttpResponse response = client.execute(post);
			return responseFromInputStream(response.getEntity().getContent());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void setContent(HttpPost post, REQ r) throws UnsupportedEncodingException {
		String body = marshalling.getRequestMarshaller().toString(r);
		LOG.trace("Request[{}]", body);
		final StringEntity entity = new StringEntity(marshalling.getRequestMarshaller().toString(r));
		entity.setContentType(marshalling.getRequestMarshaller().getContentType());
		post.setEntity(entity);
	}

	private void setHeaders(final HttpRequestBase request) {
		final Set<String> keySet = customHeaders.keySet();
		for (String key : keySet) {
			LOG.trace("Header Added[{},{}]", key, customHeaders.get(key));
			request.addHeader(key, customHeaders.get(key));
		}
		request.addHeader("Content-Type", marshalling.getRequestMarshaller().getContentType());
		
		if(tokenProvider != null){
			LOG.trace("Header Added[{},{}]", "Authorization", "Bearer " + tokenProvider.getToken());
			request.addHeader("Authorization","Bearer " + tokenProvider.getToken());			
		}
	}
	
	@SuppressWarnings("unchecked")
	private RES responseFromInputStream(final InputStream response) {
        try{        	
        	String body = IOUtils.toString(response);
        	LOG.trace("Response[{}]", body);
        	if(responseMutator != null){
        		body = responseMutator.mutate(body);
        	}
        	return (RES)marshalling.getResponseMarshaller().fromString(body, pathDetails.getResponseType());
        }
        catch(Exception exc){
        	throw new RuntimeException(exc);
        }
    }	

	@Override
	public RES get() {
		HttpGet get = new HttpGet(path);
		setHeaders(get);
		try {
			final HttpResponse response = client.execute(get);
			return responseFromInputStream(response.getEntity().getContent());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <CHILDRES extends BaseResponse, CHILDREQ extends BaseRequest> RestClient<CHILDRES, CHILDREQ> getPath(
			Class<CHILDRES> responseType, String path) {
		return new HttpComponentsRestClient<CHILDRES, CHILDREQ>(client, serverDetails, pathDetails.getPath(responseType, path), marshalling, tokenProvider);
	}

	@Override
	public void addHeader(String key, String value) {
		customHeaders.put(key, value);
	}

	@Override
	public void addResponseMutator(StringMutator mutator) {
		this.responseMutator = mutator;
	}

	@Override
	public RES delete() {
		HttpDelete get = new HttpDelete(path);
		setHeaders(get);
		try {
			final HttpResponse response = client.execute(get);
			return responseFromInputStream(response.getEntity().getContent());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}	
}
