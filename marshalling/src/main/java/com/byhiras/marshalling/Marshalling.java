package com.byhiras.marshalling;

public class Marshalling {
	
	private final MarshallingSupport requestMarshaller;
	private final MarshallingSupport responseMarshaller;
	
	public Marshalling(MarshallingSupport requestMarshaller, MarshallingSupport responseMarshaller) {		
		this.requestMarshaller = requestMarshaller;
		this.responseMarshaller = responseMarshaller;
	}

	public MarshallingSupport getRequestMarshaller() {
		return requestMarshaller;
	}

	public MarshallingSupport getResponseMarshaller() {
		return responseMarshaller;
	}

	public String getEncoding() {
		return "UTF-8";
	}
		
}
