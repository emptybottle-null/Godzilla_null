/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.math.BigInteger;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.selector.X509CertificateHolderSelector;
import org.bouncycastle.cms.KeyTransRecipientInformation;
import org.bouncycastle.cms.RecipientId;

public class KeyTransRecipientId
extends RecipientId {
    private X509CertificateHolderSelector baseSelector;

    private KeyTransRecipientId(X509CertificateHolderSelector x509CertificateHolderSelector) {
        super(0);
        this.baseSelector = x509CertificateHolderSelector;
    }

    public KeyTransRecipientId(byte[] byArray) {
        this(null, null, byArray);
    }

    public KeyTransRecipientId(X500Name x500Name, BigInteger bigInteger) {
        this(x500Name, bigInteger, null);
    }

    public KeyTransRecipientId(X500Name x500Name, BigInteger bigInteger, byte[] byArray) {
        this(new X509CertificateHolderSelector(x500Name, bigInteger, byArray));
    }

    public X500Name getIssuer() {
        return this.baseSelector.getIssuer();
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
        if (!(object instanceof KeyTransRecipientId)) {
            return false;
        }
        KeyTransRecipientId keyTransRecipientId = (KeyTransRecipientId)object;
        return this.baseSelector.equals(keyTransRecipientId.baseSelector);
    }

    public Object clone() {
        return new KeyTransRecipientId(this.baseSelector);
    }

    public boolean match(Object object) {
        if (object instanceof KeyTransRecipientInformation) {
            return ((KeyTransRecipientInformation)object).getRID().equals(this);
        }
        return this.baseSelector.match(object);
    }
}

