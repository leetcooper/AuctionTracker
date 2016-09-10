package com.byhiras.ref.builder;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.byhiras.ref.model.Catalog;
import com.byhiras.ref.model.Lot;

public class CatalogBuilder {
	final List<Lot> lots = new CopyOnWriteArrayList<>();
	
	public CatalogBuilder withLots(final List<Lot> lot){
		lots.addAll(lot);
		return this;
	}
	
	public Catalog build(){		
		final Catalog catalog = new Catalog();
		catalog.setLots(lots);
		return catalog;
	}
}
