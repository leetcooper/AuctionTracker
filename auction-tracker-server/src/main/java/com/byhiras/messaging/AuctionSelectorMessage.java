package com.byhiras.messaging;

public class AuctionSelectorMessage<T> extends AuctionMessage<T>{
	
	private String key;
	private Integer processes;
	private String property;
	
	public AuctionSelectorMessage(final T t, String key, Integer processes, String property) {
		super(t);
		this.processes = processes;
		this.key = key;
		this.property = property;
	}

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public Integer getProcesses() {
		return processes;
	}

	public void setProcesses(Integer processes) {
		this.processes = processes;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
}
