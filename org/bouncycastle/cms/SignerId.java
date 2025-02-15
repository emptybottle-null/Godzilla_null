/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.math.BigInteger;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.selector.X509CertificateHolderSelector;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.util.Selector;

public class SignerId
implements Selector {
    private X509CertificateHolderSelector baseSelector;

    private SignerId(X509CertificateHolderSelector x509CertificateHolderSelector) {
        this.baseSelector = x509CertificateHolderSelector;
    }

    public SignerId(byte[] byArray) {
        this(null, null, byArray);
    }

    public SignerId(X500Name x500Name, BigInteger bigInteger) {
        this(x500Name, bigInteger, null);
    }

    public SignerId(X500Name x500Name, BigInteger bigInteger, byte[] byArray) {
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
        if (!(object instanceof SignerId)) {
            return false;
        }
        SignerId signerId = (SignerId)object;
        return this.baseSelector.equals(signerId.baseSelector);
    }

    public boolean match(Object object) {
        if (object instanceof SignerInformation) {
            return ((SignerInformation)object).getSID().equals(this);
        }
        return this.baseSelector.match(object);
    }

    public Object clone() {
        return new SignerId(this.baseSelector);
    }
}

