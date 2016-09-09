package com.byhiras.marshalling.json.serializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime>{
	@Override
	public void serialize(final LocalDateTime localDate, final JsonGenerator gen, final SerializerProvider provider)
			throws IOException, JsonProcessingException {
		gen.writeString(localDate.format(DateTimeFormatter.ofPattern(FormatConstant.DATETIME_FORMAT)));
	}
}
