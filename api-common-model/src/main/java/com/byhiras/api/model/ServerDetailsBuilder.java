package com.byhiras.api.model;

public class ServerDetailsBuilder {
	public String protocol = "http";
	public String host = "localhost";
	public Integer port = 8080;	

	public ServerDetails build(){
		return new ServerDetails(protocol, host, port);
	}

	public ServerDetailsBuilder withProtocol(String protocol) {
		this.protocol = protocol;
		return this;
	}

	public ServerDetailsBuilder withPort(Integer port) {
		this.port = port;
		return this;
	}

	public ServerDetailsBuilder withHost(String host) {
		this.host = host;
		return this;
	}	

}
