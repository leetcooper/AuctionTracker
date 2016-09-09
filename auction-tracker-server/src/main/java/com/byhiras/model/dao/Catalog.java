package com.byhiras.model.dao;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Catalog {
    @EmbeddedId
    @JsonIgnore
    protected Guid guid = GuidGenerator.generateGuid();
    
    private Long catalogNumber;

	public Long getCatalogNumber() {
		return catalogNumber;
	}

	public void setCatalogNumber(Long catalogNumber) {
		this.catalogNumber = catalogNumber;
	}    
}
