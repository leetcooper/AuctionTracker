package com.byhiras;

public interface MarshallingSupport {
    public String toString(Object object);
    public <T> T fromString(String s, Class<T> t);
}
