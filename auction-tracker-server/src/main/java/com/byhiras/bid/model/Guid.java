package com.byhiras.bid.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class Guid implements Serializable, Comparable<Guid> {

    private static final long serialVersionUID = 1L;
    @Column(name = "GUID", columnDefinition="BINARY(16)")
    protected byte[] guid;

    public Guid(final byte[] guid) {
        this.guid = guid;
    }

    public Guid() {
    }

    public Guid(final String hexString) {
        this(GuidUtil.decodeGuidHexString(hexString));
    }

    public static Guid fromString(final String hexString) {
        return new Guid(hexString);
    }
    
    @JsonIgnore
    public byte[] getGuid() {
        return guid;
    }

    public void setGuid(final byte[] guid) {
        this.guid = guid;
    }
    
    @JsonIgnore
    public byte[] getBytes() {
        return guid;
    }

    public void setBytes(final byte[] guid) {
        this.guid = guid;
    }

    public byte[] toByteArray() {
        return guid;
    }

    public String toHexString() {
        return GuidUtil.encodeGuidToHexString(guid);
    }

    public String getHexString() {
        return toHexString();
    }

    public void setHexString(final String hex) {
        guid = GuidUtil.decodeGuidHexString(hex);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Guid)) {
            return false;
        }
        Guid rhs = (Guid) obj;
        return new EqualsBuilder().append(guid, rhs.guid).isEquals();
    }

    @Override
    public int compareTo(final Guid o) {
        return new CompareToBuilder().append(guid, o.guid).toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(37, 45).append(guid).toHashCode();
    }

    @Override
    public String toString() {
        return toHexString();
    }
}
