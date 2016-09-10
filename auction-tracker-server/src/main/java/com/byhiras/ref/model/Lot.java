package com.byhiras.ref.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Lot implements RefItem {
	@EmbeddedId
	private RefId id = RefIdGenerator.getNextRefId();
	
	private Integer lotNumber;
	
	private String description;
	
	public Integer getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(Integer lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RefId getId() {
		return id;
	}

	public void setId(RefId id) {
		this.id = id;
	}	
}
