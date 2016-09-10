package com.byhiras.service;

import com.byhiras.model.bid.VersionedEntity;

public interface VersioningService {
	public <T extends VersionedEntity> T makeInitialVersion(final T initialVersion);
	public <T extends VersionedEntity> VersionedEntityPair<T> upVersion(final T newVersion, final T currentVersionProvider);
}
