package com.byhiras.bid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LotBids implements VersionedEntity{
	
    @EmbeddedId
    @JsonIgnore
    protected Guid guid = GuidGenerator.generateGuid();	
    
    @Embedded
    private VersionDetails version = new VersionDetails();		
    	
	private Long lotId;	    
    
    @OneToOne
	private Bid currentHigestBid;
	
    private String description;	
    
    @OneToMany(mappedBy="lotBids", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Bid> bids = new ArrayList<>();
	
	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(final List<Bid> bids) {
		this.bids = bids;
	}

	public Long getLotId() {
		return lotId;
	}

	public void setLotId(Long lotId) {
		this.lotId = lotId;
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

	public LotBids addBid(Bid bid) {
		bids.add(bid);
		return this;
	}

	public Guid getGuid() {
		return guid;
	}

	public void setGuid(Guid guid) {
		this.guid = guid;
	}
	
}
