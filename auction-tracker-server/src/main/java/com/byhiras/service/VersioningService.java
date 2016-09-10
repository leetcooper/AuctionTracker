package com.byhiras.service;

import com.byhiras.bid.model.VersionedEntity;

public interface VersioningService {
	public <T extends VersionedEntity> T makeInitialVersion(final T initialVersion, Long entityId);
	public <T extends VersionedEntity> VersionedEntityPair<T> upVersion(final T newVersion, final T currentVersionProvider);
}
