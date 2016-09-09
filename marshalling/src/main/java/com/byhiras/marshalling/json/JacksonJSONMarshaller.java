package com.byhiras.marshalling.json;

import java.io.StringReader;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.byhiras.marshalling.MarshallingSupport;

public class JacksonJSONMarshaller implements MarshallingSupport{    
    private ObjectMapper mapper = new ObjectMapper();
    
    public JacksonJSONMarshaller(){
    	
    }
    
    public String toString(Object object) {
        try{ 
            return mapper.writeValueAsString(object);
        }
        catch(Exception exc){
            throw new RuntimeException(exc);
        }
    }

	public <T> T fromString(String json, Class<T> t) {
        try{                       
            return mapper.readValue(new StringReader(json), t);
        }
        catch(Exception exc){
            throw new RuntimeException(exc);
        }
	}

	@Override
	public String getAcceptType() {
		return "application/json";
	}

	@Override
	public String getContentType() {
		return "application/json";
	}	
    
}
