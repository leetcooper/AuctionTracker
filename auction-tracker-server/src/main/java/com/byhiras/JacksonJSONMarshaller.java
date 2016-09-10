package com.byhiras;

import java.io.StringReader;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class JacksonJSONMarshaller implements MarshallingSupport{    
    private ObjectMapper mapper;
    
    public JacksonJSONMarshaller(){
    	mapper = new ObjectMapper();    	  	
    	mapper.registerModule(new JavaTimeModule());
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
}
