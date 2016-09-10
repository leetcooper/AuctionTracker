package com.byhiras.bid.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Embeddable
public class VersionDetails {
	@Column(name = "ENTITY_ID")	
	private Long entityId;		
	@Column (name="VERSION")	
	private Integer version;
	@Column(name="LATEST")
	private Boolean current;	
	@Column(name="ACTIVE_FROM")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime activeFrom;
	@Column(name="ACTIVE_TO")	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime activeTo;
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getCurrent() {
		return current;
	}

	public void setCurrent(Boolean current) {
		this.current = current;
	}

	public LocalDateTime getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(LocalDateTime activeFrom) {
		this.activeFrom = activeFrom;
	}

	public LocalDateTime getActiveTo() {
		return activeTo;
	}

	public void setActiveTo(LocalDateTime activeTo) {
		this.activeTo = activeTo;
	}
	
	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public VersionDetails incrementVersion() {
		final VersionDetails newVersion = new VersionDetails();
		newVersion.setVersion(version+1);
		newVersion.setEntityId(entityId);
		return newVersion;
	}

	@Override
	public String toString() {
		return "VersionDetails [entityId=" + entityId + ", version=" + version + ", current=" + current
				+ ", activeFrom=" + activeFrom + ", activeTo=" + activeTo + "]";
	}
		
}
