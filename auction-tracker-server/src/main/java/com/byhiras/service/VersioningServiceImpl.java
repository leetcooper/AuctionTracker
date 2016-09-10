package com.byhiras.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.byhiras.bid.model.VersionDetails;
import com.byhiras.bid.model.VersionedEntity;

@Service
public class VersioningServiceImpl implements VersioningService{

	@Override
	public <T extends VersionedEntity> T makeInitialVersion(final T initialVersion, Long entityId) {
		initialVersion.getVersion().setEntityId(entityId);
		validateVersion(initialVersion);
		makeNewCurrent(initialVersion);
		initialVersion.getVersion().setVersion(1);
		return initialVersion;
	}

	private void validateVersion(final VersionedEntity version) {
		if(version == null){
			throw new VersionException("Version is Null");
		}
		if(version.getVersion() == null){
			throw new VersionException("Version Version is Null");
		}
		if(version.getId() == null){
			throw new VersionException("Version Id is Null");
		}		
	}

	@Override
	public <T extends VersionedEntity> VersionedEntityPair<T> upVersion(final T newVersion, final T currentVersion) {
		validateVersionWithNumber(newVersion);
		validateVersionWithNumber(currentVersion);
		
		if(newVersion.getVersion().getEntityId().longValue() != currentVersion.getVersion().getEntityId().longValue()){
			throw new VersionException("Current and New entity IDs are different");
		}
		if(newVerisonIsNotOneAfterCurrent(newVersion, currentVersion)){
			throw new VersionException("New Version is not one version after current");
		}
		makeNewCurrent(newVersion);
		closeVersion(newVersion, currentVersion);
		if(newVersion.getVersion().getActiveFrom().isBefore(currentVersion.getVersion().getActiveFrom())){
			throw new VersionException("New Version cannot have active from before current active from");
		}
		return new VersionedEntityPair<T>(currentVersion, newVersion);
	}

	private <T extends VersionedEntity> void validateVersionWithNumber(T version) {
		validateVersion(version);
		if(version.getVersion().getVersion() == null){
			throw new VersionException("Version Number is Null");
		}		
	}

	private boolean newVerisonIsNotOneAfterCurrent(VersionedEntity newVersion, VersionedEntity currentVersion) {
		return newVersion.getVersion().getVersion() - currentVersion.getVersion().getVersion() != 1;
	}

	private void makeNewCurrent(final VersionedEntity newVersion) {
		final VersionDetails version = newVersion.getVersion();
		version.setCurrent(Boolean.TRUE);
		version.setActiveFrom(LocalDateTime.now());
		version.setActiveTo(null);
	}

	private void closeVersion(VersionedEntity newVersion, VersionedEntity currentVersion) {
		final VersionDetails oldVersion = currentVersion.getVersion();
		oldVersion.setActiveTo(newVersion.getVersion().getActiveFrom());
		oldVersion.setCurrent(Boolean.FALSE);
		VersionDetails version = newVersion.getVersion();
		version.setActiveFrom(oldVersion.getActiveTo());
	}
}
