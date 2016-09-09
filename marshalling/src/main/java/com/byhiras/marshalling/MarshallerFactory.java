package com.byhiras.marshalling;

import com.byhiras.marshalling.json.JacksonJSONMarshaller;
import com.byhiras.marshalling.multippart.QueryStringMarshaller;

public class MarshallerFactory {
	
	private JacksonJSONMarshaller jsonMarshaller;
	private QueryStringMarshaller queryStringMarshaller;
	
	public MarshallerFactory(){
		
	}

	public MarshallingSupport getJsonMarshaller() {
		if(jsonMarshaller == null){
			jsonMarshaller = new JacksonJSONMarshaller();
		}
		return jsonMarshaller;
	}

	public MarshallingSupport getMultipartMarshaller() {
		if(queryStringMarshaller == null){
			queryStringMarshaller = new QueryStringMarshaller();
		}
		return queryStringMarshaller;
	}

}
