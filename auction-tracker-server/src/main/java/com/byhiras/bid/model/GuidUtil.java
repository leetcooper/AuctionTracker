package com.byhiras.bid.model;

import static org.apache.commons.codec.binary.Hex.decodeHex;
import static org.apache.commons.codec.binary.Hex.encodeHex;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.apache.commons.codec.DecoderException;

/**
 * Helper class for Guid functions used by the model
 *
 * @author lee
 */
public abstract class GuidUtil {

	public static byte[] createGuid() {
		UUID uuid = UUID.randomUUID();
		ByteBuffer bytes = ByteBuffer.wrap(new byte[16]);
		bytes.putLong(uuid.getMostSignificantBits());
		bytes.putLong(uuid.getLeastSignificantBits());
		return bytes.array();
	}

	public static String createGuidHex() {
		return encodeGuidToHexString(createGuid());
	}

	public static byte[] decodeGuidHexString(final String s) {
		try {  
			return s != null ? decodeHex(s.toCharArray()) : new byte[0];
		} catch (DecoderException e) {
			throw new RuntimeException("Unable to decode invalid GUID value " + s + ".", e);
		}
	}

	public static String encodeGuidToHexString(final byte[] guid) {
		return guid != null ? new String(encodeHex(guid)) : null;
	}
}