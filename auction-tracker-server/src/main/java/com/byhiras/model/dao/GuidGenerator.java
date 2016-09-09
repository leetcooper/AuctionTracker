package com.byhiras.model.dao;

public abstract class GuidGenerator {
        public static Guid generateGuid() {
                return new Guid(GuidUtil.createGuid());
        }
}
