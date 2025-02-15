/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.selector;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.selector.MSOutlookKeyIdCalculator;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;

public class X509CertificateHolderSelector
implements Selector {
    private byte[] subjectKeyId;
    private X500Name issuer;
    private BigInteger serialNumber;

    public X509CertificateHolderSelector(byte[] byArray) {
        this(null, null, byArray);
    }

    public X509CertificateHolderSelector(X500Name x500Name, BigInteger bigInteger) {
        this(x500Name, bigInteger, null);
    }

    public X509CertificateHolderSelector(X500Name x500Name, BigInteger bigInteger, byte[] byArray) {
        this.issuer = x500Name;
        this.serialNumber = bigInteger;
        this.subjectKeyId = byArray;
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    public BigInteger getSerialNumber() {
        return this.serialNumber;
    }

    public byte[] getSubjectKeyIdentifier() {
        return Arrays.clone(this.subjectKeyId);
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
        if (!(object instanceof X509CertificateHolderSelector)) {
            return false;
        }
        X509CertificateHolderSelector x509CertificateHolderSelector = (X509CertificateHolderSelector)object;
        return Arrays.areEqual(this.subjectKeyId, x509CertificateHolderSelector.subjectKeyId) && this.equalsObj(this.serialNumber, x509CertificateHolderSelector.serialNumber) && this.equalsObj(this.issuer, x509CertificateHolderSelector.issuer);
    }

    private boolean equalsObj(Object object, Object object2) {
        return object != null ? object.equals(object2) : object2 == null;
    }

    public boolean match(Object object) {
        if (object instanceof X509CertificateHolder) {
            X509CertificateHolder x509CertificateHolder = (X509CertificateHolder)object;
            if (this.getSerialNumber() != null) {
                IssuerAndSerialNumber issuerAndSerialNumber = new IssuerAndSerialNumber(x509CertificateHolder.toASN1Structure());
                return issuerAndSerialNumber.getName().equals(this.issuer) && issuerAndSerialNumber.getSerialNumber().getValue().equals(this.serialNumber);
            }
            if (this.subjectKeyId != null) {
                Extension extension = x509CertificateHolder.getExtension(Extension.subjectKeyIdentifier);
                if (extension == null) {
                    return Arrays.areEqual(this.subjectKeyId, MSOutlookKeyIdCalculator.calculateKeyId(x509CertificateHolder.getSubjectPublicKeyInfo()));
                }
                byte[] byArray = ASN1OctetString.getInstance(extension.getParsedValue()).getOctets();
                return Arrays.areEqual(this.subjectKeyId, byArray);
            }
        } else if (object instanceof byte[]) {
            return Arrays.areEqual(this.subjectKeyId, (byte[])object);
        }
        return false;
    }

    public Object clone() {
        return new X509CertificateHolderSelector(this.issuer, this.serialNumber, this.subjectKeyId);
    }
}

