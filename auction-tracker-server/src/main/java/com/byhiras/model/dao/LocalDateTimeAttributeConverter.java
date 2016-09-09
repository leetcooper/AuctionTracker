package com.byhiras.model.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.AttributeConverter;

public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
		return Optional.ofNullable(localDateTime).map(t -> Timestamp.valueOf(t)).orElse(null);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
		return Optional.ofNullable(sqlTimestamp).map(t -> sqlTimestamp.toLocalDateTime()).orElse(null);		
	}
}
