package com.byhiras.ref.builder;

import static java.util.Objects.requireNonNull;

import com.byhiras.ref.model.Auction;
import com.byhiras.ref.model.Catalog;

public class AuctionBuilder {
	private Catalog catalog;
	private String name;
	
	public AuctionBuilder withName(final String name){
		this.name = name;
		return this;
	}
	
	public AuctionBuilder withCatalog(final Catalog catalog){
		this.catalog = catalog;
		return this;
	}
		
	public Auction build(){
		requireNonNull(name);
		requireNonNull(catalog);		
		final Auction auction = new Auction();
		auction.setName(name);
		auction.setCatalog(catalog);
		return auction;
	}
}
