package com.byhiras.model.bid;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bid {
    @EmbeddedId
    @JsonIgnore
    protected Guid guid = GuidGenerator.generateGuid();	
    
	private Integer paddleNumber;
	
	private BigDecimal price;
	
	@Embedded
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
}