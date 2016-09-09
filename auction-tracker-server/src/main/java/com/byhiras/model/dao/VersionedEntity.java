package com.byhiras.model.dao;

public interface VersionedEntity {
	public VersionDetails getVersion();
	public Long getId();
}
