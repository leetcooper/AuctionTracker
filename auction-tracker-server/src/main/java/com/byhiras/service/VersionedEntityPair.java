package com.byhiras.service;

import com.byhiras.model.dao.VersionedEntity;

public class VersionedEntityPair <T extends VersionedEntity> {
	public T previousVersionedEntity;
	public T versionedEntity;
	
	public VersionedEntityPair(final T previousVersionedEntity, final T versionedEntity){
		this.previousVersionedEntity = previousVersionedEntity;
		this.versionedEntity = versionedEntity;
	}

	public T getPreviousVersionedEntity() {
		return previousVersionedEntity;
	}

	public void setPreviousVersionedEntity(T previousVersionedEntity) {
		this.previousVersionedEntity = previousVersionedEntity;
	}

	public T getVersionedEntity() {
		return versionedEntity;
	}

	public void setVersionedEntity(T versionedEntity) {
		this.versionedEntity = versionedEntity;
	}
}
