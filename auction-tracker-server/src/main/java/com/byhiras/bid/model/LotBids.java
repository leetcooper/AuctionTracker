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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LotBids implements VersionedEntity{
	private static Logger LOG = LoggerFactory.getLogger(LotBids.class);
	
    @EmbeddedId
    @JsonIgnore
    protected Guid guid = GuidGenerator.generateGuid();	
    
    @Embedded
    private VersionDetails version = new VersionDetails();		
    	
	private Long lotId;	    
    
    @OneToOne(cascade=CascadeType.ALL)
	private Bid currentHighestBid;
	
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
	
	public Bid getCurrentHighestBid() {
		return currentHighestBid;
	}

	public void setCurrentHighestBid(Bid currentHighestBid) {
		this.currentHighestBid = currentHighestBid;
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
		LOG.info("Adding Bid [{}] Current Highest [{}]", bid, currentHighestBid);
		bids.add(bid);
		if(bids.size() == 1){
			setCurrentHighestBid(bid);
			LOG.info("First Bid - Setting to highest [{}]", bid);
		}
		else if(currentHighestBid.getPrice().compareTo(bid.getPrice()) == 0){
			setCurrentHighestBid(currentHighestBid);
			LOG.info("Bid is equal maintaining current bid [{}]", currentHighestBid);
		}
		else{
			if(currentHighestBid.getPrice().compareTo(bid.getPrice()) < 0){
				LOG.info("Bid [{}] is new highest bid", bid);
				setCurrentHighestBid(bid);				
			}
		}	
		return this;
	}	

	public Guid getGuid() {
		return guid;
	}

	public void setGuid(Guid guid) {
		this.guid = guid;
	}
	
}
