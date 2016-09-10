package com.byhiras.service;

import com.byhiras.bid.model.LotBids;
import com.byhiras.ref.model.RefId;

public interface LotBidsVersioningUtil {
	public VersionedEntityPair<LotBids> getVersionPairByLotNumber(RefId lotId);
	public void saveVersionedPair(VersionedEntityPair<LotBids> versionPair);
	public LotBids makeInitialVersion(final LotBids initialVersion, Long entityId);

}
