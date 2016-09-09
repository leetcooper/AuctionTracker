package com.byhiras.marshalling.multippart;

import java.util.Map;

import com.byhiras.marshalling.MarshallingSupport;

public class QueryStringMarshaller implements MarshallingSupport{

	@Override
	public String toString(Object object) {
		if(object instanceof QueryStringRequest){
			Map<String, String> queryStringMap = ((QueryStringRequest)object).getQueryStringMap();
			return MapQuery.urlEncodeUTF8(queryStringMap);
		}
		return "";
	}

	@Override
	public <T> T fromString(String s, Class<T> t) {
		return null;
	}

	@Override
	public String getAcceptType() {
		return "*/*";
	}

	@Override
	public String getContentType() {
		return "application/x-www-form-urlencoded";
	}

}
