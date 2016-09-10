package com.byhiras.bid.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bid {
    @EmbeddedId
    @JsonIgnore
    protected Guid guid = GuidGenerator.generateGuid();	
    
    @ManyToOne
    @JsonIgnore
    private LotBids lotBids;
    
	private Integer paddleNumber;
	
	private BigDecimal price;
	
	private Integer lotNumber;	

	public Integer getPaddleNumber() {
		return paddleNumber;
	}

	public void setPaddleNumber(Integer paddleNumber) {
		this.paddleNumber = paddleNumber;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(Integer lotNumber) {
		this.lotNumber = lotNumber;
	}

	public LotBids getLotBids() {
		return lotBids;
	}

	public void setLotBids(LotBids lotBids) {
		this.lotBids = lotBids;
	}	
}
