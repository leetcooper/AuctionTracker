package com.byhiras.model.bid;

public abstract class GuidGenerator {
	public static Guid generateGuid() {
		return new Guid(GuidUtil.createGuid());
	}
}
