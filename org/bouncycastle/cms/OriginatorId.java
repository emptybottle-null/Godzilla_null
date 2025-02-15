/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.math.BigInteger;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;

class OriginatorId
implements Selector {
    private byte[] subjectKeyId;
    private X500Name issuer;
    private BigInteger serialNumber;

    public OriginatorId(byte[] byArray) {
        this.setSubjectKeyID(byArray);
    }

    private void setSubjectKeyID(byte[] byArray) {
        this.subjectKeyId = byArray;
    }

    public OriginatorId(X500Name x500Name, BigInteger bigInteger) {
        this.setIssuerAndSerial(x500Name, bigInteger);
    }

    private void setIssuerAndSerial(X500Name x500Name, BigInteger bigInteger) {
        this.issuer = x500Name;
        this.serialNumber = bigInteger;
    }

    public OriginatorId(X500Name x500Name, BigInteger bigInteger, byte[] byArray) {
        this.setIssuerAndSerial(x500Name, bigInteger);
        this.setSubjectKeyID(byArray);
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    public Object clone() {
        return new OriginatorId(this.issuer, this.serialNumber, this.subjectKeyId);
    }

    public int hashCode() {
        int n = Arrays.hashCode(this.subjectKeyId);
        if (this.serialNumber != null) {
            n ^= this.serialNumber.hashCode();
        }
        if (this.issuer != null) {
            n ^= this.issuer.hashCode();
        }
        return n;
    }

    public boolean equals(Object object) {
        if (!(object instanceof OriginatorId)) {
            return false;
        }
        OriginatorId originatorId = (OriginatorId)object;
        return Arrays.areEqual(this.subjectKeyId, originatorId.subjectKeyId) && this.equalsObj(this.serialNumber, originatorId.serialNumber) && this.equalsObj(this.issuer, originatorId.issuer);
    }

    private boolean equalsObj(Object object, Object object2) {
        return object != null ? object.equals(object2) : object2 == null;
    }

    public boolean match(Object object) {
        return false;
    }
}

