/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.selector.jcajce;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.X509CertSelector;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.selector.X509CertificateHolderSelector;

public class JcaX509CertSelectorConverter {
    protected X509CertSelector doConversion(X500Name x500Name, BigInteger bigInteger, byte[] byArray) {
        X509CertSelector x509CertSelector = new X509CertSelector();
        if (x500Name != null) {
            try {
                x509CertSelector.setIssuer(x500Name.getEncoded());
            } catch (IOException iOException) {
                throw new IllegalArgumentException("unable to convert issuer: " + iOException.getMessage());
            }
        }
        if (bigInteger != null) {
            x509CertSelector.setSerialNumber(bigInteger);
        }
        if (byArray != null) {
            try {
                x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(byArray).getEncoded());
            } catch (IOException iOException) {
                throw new IllegalArgumentException("unable to convert issuer: " + iOException.getMessage());
            }
        }
        return x509CertSelector;
    }

    public X509CertSelector getCertSelector(X509CertificateHolderSelector x509CertificateHolderSelector) {
        return this.doConversion(x509CertificateHolderSelector.getIssuer(), x509CertificateHolderSelector.getSerialNumber(), x509CertificateHolderSelector.getSubjectKeyIdentifier());
    }
}

