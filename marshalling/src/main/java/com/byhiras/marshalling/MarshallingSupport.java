package com.byhiras.marshalling;

public interface MarshallingSupport {
    public String toString(Object object);
    public <T> T fromString(String s, Class<T> t);
	public String getAcceptType();
	public String getContentType();
}
