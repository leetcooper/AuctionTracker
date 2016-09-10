package com.byhiras.bid.model;

public abstract class GuidGenerator {
	public static Guid generateGuid() {
		return new Guid(GuidUtil.createGuid());
	}
}
