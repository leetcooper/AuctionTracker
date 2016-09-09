package com.byhiras.api.model;

public class ServerDetails {
	
	public String protocol;
	public String host;
	public Integer port;
	
	public ServerDetails(){
		 
	}
		
	public ServerDetails(String protocol, String host, Integer port) {
		this.protocol = protocol;
		this.host = host;
		this.port = port;
	}

	public String path(String path) {
		if(port != null){
			return protocol + "://" + host + ":" + port + path;
		}
		else{
			return protocol + "://" + host + path;
		}
	}

	public String getProtocol() {
		return protocol;
	}

	public String getHost() {
		return host;
	}

	public Integer getPort() {
		return port;
	}
	 
}
