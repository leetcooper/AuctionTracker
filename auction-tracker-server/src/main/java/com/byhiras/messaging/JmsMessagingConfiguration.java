package com.byhiras.messaging;

public class JmsMessagingConfiguration {
	
	private final int selectorSplit;
	private final String feedName;
	private final String selectorProperty;
	private final int concurrency = 1;
	private final boolean durable;	
	
	public JmsMessagingConfiguration(int selectorSplit, final String feedName, final String selectorProperty, boolean durable) {
		super();
		this.selectorSplit = selectorSplit;
		this.feedName = feedName;
		this.selectorProperty = selectorProperty;
		this.durable = durable;
	}

	public int getSelectorSplit() {
		return selectorSplit;
	}

	public String getFeedName() {
		return feedName;
	}

	public String getSelectorProperty() {
		return selectorProperty;
	}

	public String getConcurrency() {
		return concurrency+"";
	}

	@Override
	public String toString() {
		return "JmsMessagingConfiguration [selectorSplit=" + selectorSplit + ", feedName=" + feedName
				+ ", selectorProperty=" + selectorProperty + ", concurrency=" + concurrency
				+ ", durable=" + durable + "]";
	}

	public boolean isDurable() {
		return durable;
	}
		
}
