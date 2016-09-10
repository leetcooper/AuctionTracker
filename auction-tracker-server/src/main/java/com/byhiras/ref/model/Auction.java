package com.byhiras.ref.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Auction implements RefItem{
	
	@EmbeddedId
	private RefId id = RefIdGenerator.getNextRefId();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<AuctionPaddle> paddles = new ArrayList<>();
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
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

	public RefId getId() {
		return id;
	}

	public void setId(RefId id) {
		this.id = id;
	}

	public void addPaddle(final AuctionPaddle paddle) {
		paddles.add(paddle);
	}	
}
