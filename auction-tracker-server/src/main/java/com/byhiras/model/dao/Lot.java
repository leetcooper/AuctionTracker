package com.byhiras.model.dao;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lot implements VersionedEntity{
	
    @EmbeddedId
    @JsonIgnore
    protected Guid guid = GuidGenerator.generateGuid();	
    
    @Embedded
    private VersionDetails version = new VersionDetails();		
	@OneToOne
	private Long cataLogNumber;	
	@OneToOne
	private Bid currentHigestBid;	
	private Integer lotNumber;	
	private String description;	
	@OneToMany
	private List<Bid> bids;
	
	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(final List<Bid> bids) {
		this.bids = bids;
	}

	public Integer getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(final Integer lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Bid getCurrentHigestBid() {
		return currentHigestBid;
	}

	public void setCurrentHigestBid(Bid currentHigestBid) {
		this.currentHigestBid = currentHigestBid;
	}
	
	public Long getCataLogNumber() {
		return cataLogNumber;
	}

	public void setCataLogNumber(Long cataLogNumber) {
		this.cataLogNumber = cataLogNumber;
	}

	public VersionDetails getVersion() {
		return version;
	}

	public void setVersion(VersionDetails version) {
		this.version = version;
	}
	
	@Override
	@JsonIgnore
	public Long getId() {
		return version.getEntityId();
	}	
	
}
