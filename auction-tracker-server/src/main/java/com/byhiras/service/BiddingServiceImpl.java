package com.byhiras.service;

import java.util.Optional;

import javax.inject.Inject;

import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.byhiras.bid.model.Bid;
import com.byhiras.bid.model.LotBids;
import com.byhiras.bid.repo.LotBidsRepository;
import com.byhiras.messaging.MessageHandler;
import com.byhiras.messaging.MessageSubscriber;
import com.byhiras.ref.ReferenceApi;
import com.byhiras.ref.model.Lot;
import com.byhiras.ref.repo.LotRepository;

@Service
public class BiddingServiceImpl implements BiddingService, MessageHandler<Bid> {
	private static Logger LOG = LoggerFactory.getLogger(BiddingServiceImpl.class);
	private final ReferenceApi ref;
	private final LotBidsRepository lotBidsRepository;
	private final LotBidsIdGenerator lotBidsIdGenerator;
	private final LotBidsVersioningUtil lotBidsVersioningUtil;

	@Inject
	public BiddingServiceImpl(final ReferenceApi ref, final LotBidsRepository lotBidsRepository,
			final VersioningService versioningService, final LotBidsIdGenerator lotBidsIdGenerator,
			final MessageSubscriber messageSubscriber, final LotBidsVersioningUtil lotBidsVersioningUtil) {
		this.ref = ref;
		this.lotBidsRepository = lotBidsRepository;
		this.lotBidsIdGenerator = lotBidsIdGenerator;
		this.lotBidsVersioningUtil = lotBidsVersioningUtil;
		messageSubscriber.addSubscriber("Bid", this);
	}

	@Override
	public void openBiddingForLot(final Integer lotNumber) {		
		try {
			final LotBids lotBids = findLotByLotNumber(lotNumber).map(this::openLotBidding)
					.orElseThrow(() -> new ObjectNotFoundException(lotNumber, Lot.class.getName()));
			LOG.info("Lot [{}] is open of bidding using LotBid [{}]", lotNumber, lotBids.getId());
		} catch (ObjectNotFoundException e) {
			LOG.error(e.getMessage());
		}
	}

	private Optional<Lot> findLotByLotNumber(final Integer lotNumber) {
		final LotRepository lotRepository = (LotRepository) ref.getRepository(Lot.class);
		final Lot lot = lotRepository.findByLotNumber(lotNumber);
		return Optional.ofNullable(lot);
	}

	private LotBids openLotBidding(final Lot lot) {
		final LotBids lotBids = lotBidsVersioningUtil.makeInitialVersion(new LotBids(),
				lotBidsIdGenerator.generateNextId());
		lotBids.setLotId(lot.getId().getId());
		lotBidsRepository.save(lotBids);
		return lotBids;
	}

	@Override
	public Class<Bid> getType() {
		return Bid.class;
	}

	@Override
	public void handleMessage(final Bid bid) {
		try {
			final VersionedEntityPair<LotBids> versionedPair = findLotByLotNumber(bid.getLotNumber())
			.map(this::getVersionedPairLotBidsByLot)
			.orElseThrow(() -> new ObjectNotFoundException(bid.getLotNumber(), Lot.class.getName()));
			versionedPair.getVersionedEntity().addBid(bid);
			bid.setLotBids(versionedPair.getVersionedEntity());
			lotBidsVersioningUtil.saveVersionedPair(versionedPair);
		} catch (ObjectNotFoundException e) {
			LOG.error(e.getMessage());
		}
	}
	
	private VersionedEntityPair<LotBids> getVersionedPairLotBidsByLot(final Lot lot){
		return lotBidsVersioningUtil
				.getVersionPairByLotNumber(lot.getId());
	}
	
}
