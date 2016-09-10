package com.byhiras.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.byhiras.MarshallingSupport;
import com.byhiras.bid.model.LotBids;
import com.byhiras.bid.repo.LotBidsRepository;
import com.byhiras.ref.model.RefId;

@Component
public class LotBidsVersioningUtilImpl implements LotBidsVersioningUtil{
	private static Logger LOG = LoggerFactory.getLogger(LotBidsVersioningUtilImpl.class);
	@Autowired
	private LotBidsRepository lotBidsRepository;
	@Autowired
	private VersioningService versioningService;
	@Autowired
	private MarshallingSupport marshallingSupport;	
	@PersistenceContext 
	private EntityManager em;
	
	@Override
	public VersionedEntityPair<LotBids> getVersionPairByLotNumber(RefId lotId) {
		final LotBids oldVersion = lotBidsRepository.findDistinctByLotIdAndVersionCurrent(lotId.getId(),Boolean.TRUE);
		final LotBids newVersion = createNewVersion(oldVersion);		
		final VersionedEntityPair<LotBids> pair = new VersionedEntityPair<LotBids>(oldVersion, newVersion);
		return pair;
	}
	
	public LotBids createNewVersion(final LotBids oldVersion){		
		final LotBids newVersion = copy(oldVersion);
		newVersion.setVersion(oldVersion.getVersion().incrementVersion());
		versioningService.upVersion(newVersion, oldVersion);
		linkRelationships(newVersion);
		return newVersion;
	}
	
	private void linkRelationships(LotBids newVersion) {
		newVersion.getBids().forEach(e -> e.setLotBids(newVersion));
	}

	private LotBids copy(LotBids oldVersion) {
		return marshallingSupport.fromString(marshallingSupport.toString(oldVersion), LotBids.class);
	}
	
	@Override
	@Transactional
	public void saveVersionedPair(final VersionedEntityPair<LotBids> versionPair) {
		LOG.trace("Saving LotBids Pair [{}],[{}]", 
				versionPair.getPreviousVersionedEntity().getGuid(),
				versionPair.getVersionedEntity().getGuid());
		
		lotBidsRepository.save(versionPair.getPreviousVersionedEntity());
		lotBidsRepository.save(versionPair.getVersionedEntity());
		em.flush();
		LOG.info("LotBids Version Updated To [{}]", versionPair.getVersionedEntity().getVersion());
	}

	@Override
	public LotBids makeInitialVersion(LotBids initialVersion, Long entityId) {
		return versioningService.makeInitialVersion(initialVersion, entityId);
	}	
}
