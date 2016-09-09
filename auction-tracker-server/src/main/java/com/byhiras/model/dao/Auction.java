package com.byhiras.model.dao;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Auction implements VersionedEntity {
    @EmbeddedId
    @JsonIgnore
    protected Guid guid = GuidGenerator.generateGuid();	
    
	@Embedded
	private VersionDetails version = new VersionDetails();
	@OneToMany
	private List<AuctionPaddle> paddles;
	@OneToOne
	private Catalog catalog;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public List<AuctionPaddle> getPaddles() {
		return paddles;
	}

	public void setPaddles(List<AuctionPaddle> paddles) {
		this.paddles = paddles;
	}

	@Override
	@JsonIgnore
	public Long getId() {
		return version.getEntityId();
	}

	public VersionDetails getVersion() {
		return version;
	}

	public void setVersion(VersionDetails version) {
		this.version = version;
	}

}
