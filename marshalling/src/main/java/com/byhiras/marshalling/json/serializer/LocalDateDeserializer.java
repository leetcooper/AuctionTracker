package com.byhiras.marshalling.json.serializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateDeserializer extends JsonDeserializer<LocalDateTime>{
	@Override
	public LocalDateTime deserialize(JsonParser parer, DeserializationContext arg1)
			throws IOException, JsonProcessingException {        
		return LocalDateTime.parse(parer.getText(), DateTimeFormatter.ofPattern(FormatConstant.DATETIME_FORMAT));        
	}
}
