package com.byhiras.ref.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Catalog implements RefItem {
	@EmbeddedId
	private RefId id = RefIdGenerator.getNextRefId();
    
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Lot> lots;    

	public List<Lot> getLots() {
		return lots;
	}

	public void setLots(List<Lot> lots) {
		this.lots = lots;
	}
	
	public RefId getId() {
		return id;
	}

	public void setId(RefId id) {
		this.id = id;
	}	
}
