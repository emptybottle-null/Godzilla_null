/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.math.BigInteger;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.selector.X509CertificateHolderSelector;
import org.bouncycastle.cms.KeyAgreeRecipientInformation;
import org.bouncycastle.cms.RecipientId;

public class KeyAgreeRecipientId
extends RecipientId {
    private X509CertificateHolderSelector baseSelector;

    private KeyAgreeRecipientId(X509CertificateHolderSelector x509CertificateHolderSelector) {
        super(2);
        this.baseSelector = x509CertificateHolderSelector;
    }

    public KeyAgreeRecipientId(byte[] byArray) {
        this(null, null, byArray);
    }

    public KeyAgreeRecipientId(X500Name x500Name, BigInteger bigInteger) {
        this(x500Name, bigInteger, null);
    }

    public KeyAgreeRecipientId(X500Name x500Name, BigInteger bigInteger, byte[] byArray) {
        this(new X509CertificateHolderSelector(x500Name, bigInteger, byArray));
    }

    public BigInteger getSerialNumber() {
        return this.baseSelector.getSerialNumber();
    }

    public byte[] getSubjectKeyIdentifier() {
        return this.baseSelector.getSubjectKeyIdentifier();
    }

    public int hashCode() {
        return this.baseSelector.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof KeyAgreeRecipientId)) {
            return false;
        }
        KeyAgreeRecipientId keyAgreeRecipientId = (KeyAgreeRecipientId)object;
        return this.baseSelector.equals(keyAgreeRecipientId.baseSelector);
    }

    public Object clone() {
        return new KeyAgreeRecipientId(this.baseSelector);
    }

    public boolean match(Object object) {
        if (object instanceof KeyAgreeRecipientInformation) {
            return ((KeyAgreeRecipientInformation)object).getRID().equals(this);
        }
        return this.baseSelector.match(object);
    }
}

