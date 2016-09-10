package com.byhiras.service;

import java.math.BigInteger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

@Component
public class LotBidsIdGenerator {	
	private EntityManager em;
	
	@Inject
	public LotBidsIdGenerator(EntityManager em){
		this.em = em;
	}
	
	public Long generateNextId(){
		return new Long(((BigInteger)em.createNativeQuery("select nextval('LOT_BIDS_ID_SEQ')").getSingleResult()).intValue());
	}
}
