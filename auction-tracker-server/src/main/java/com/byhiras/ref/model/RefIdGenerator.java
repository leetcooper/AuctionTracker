package com.byhiras.ref.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Hacky ref id generator would obviously normally use a database sequence that I cannot be bothered to set up for this programming test
 *  
 */
public class RefIdGenerator {
	private static AtomicLong id = new AtomicLong(1);
	public static RefId getNextRefId(){
		RefId refId = new RefId();
		refId.setId(id.getAndIncrement());
		return refId;
	}
}
