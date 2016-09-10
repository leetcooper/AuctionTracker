package com.byhiras.model.bid;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.byhiras.ref.model.RefId;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LotBids implements VersionedEntity{
	
    @EmbeddedId
    @JsonIgnore
    protected Guid guid = GuidGenerator.generateGuid();	
    
    @Embedded
    private VersionDetails version = new VersionDetails();		
    
	@Embedded
	private RefId lot;	    
    
    @OneToOne
	private Bid currentHigestBid;
	
    private String description;	

    @OneToMany
	private List<Bid> bids;
	
	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(final List<Bid> bids) {
		this.bids = bids;
	}
	
	public RefId getLot() {
		return lot;
	}

	public void setLot(RefId lot) {
		this.lot = lot;
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
	
}
