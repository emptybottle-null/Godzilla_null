/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificatePair;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.provider.X509CertificateObject;
import org.bouncycastle.x509.ExtCertificateEncodingException;

public class X509CertificatePair {
    private final JcaJceHelper bcHelper = new BCJcaJceHelper();
    private X509Certificate forward;
    private X509Certificate reverse;

    public X509CertificatePair(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        this.forward = x509Certificate;
        this.reverse = x509Certificate2;
    }

    public X509CertificatePair(CertificatePair certificatePair) throws CertificateParsingException {
        if (certificatePair.getForward() != null) {
            this.forward = new X509CertificateObject(certificatePair.getForward());
        }
        if (certificatePair.getReverse() != null) {
            this.reverse = new X509CertificateObject(certificatePair.getReverse());
        }
    }

    public byte[] getEncoded() throws CertificateEncodingException {
        Certificate certificate = null;
        Certificate certificate2 = null;
        try {
            if (this.forward != null && (certificate = Certificate.getInstance(new ASN1InputStream(this.forward.getEncoded()).readObject())) == null) {
                throw new CertificateEncodingException("unable to get encoding for forward");
            }
            if (this.reverse != null && (certificate2 = Certificate.getInstance(new ASN1InputStream(this.reverse.getEncoded()).readObject())) == null) {
                throw new CertificateEncodingException("unable to get encoding for reverse");
            }
            return new CertificatePair(certificate, certificate2).getEncoded("DER");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new ExtCertificateEncodingException(illegalArgumentException.toString(), illegalArgumentException);
        } catch (IOException iOException) {
            throw new ExtCertificateEncodingException(iOException.toString(), iOException);
        }
    }

    public X509Certificate getForward() {
        return this.forward;
    }

    public X509Certificate getReverse() {
        return this.reverse;
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof X509CertificatePair)) {
            return false;
        }
        X509CertificatePair x509CertificatePair = (X509CertificatePair)object;
        boolean bl = true;
        boolean bl2 = true;
        if (this.forward != null) {
            bl2 = this.forward.equals(x509CertificatePair.forward);
        } else if (x509CertificatePair.forward != null) {
            bl2 = false;
        }
        if (this.reverse != null) {
            bl = this.reverse.equals(x509CertificatePair.reverse);
        } else if (x509CertificatePair.reverse != null) {
            bl = false;
        }
        return bl2 && bl;
    }

    public int hashCode() {
        int n = -1;
        if (this.forward != null) {
            n ^= this.forward.hashCode();
        }
        if (this.reverse != null) {
            n *= 17;
            n ^= this.reverse.hashCode();
        }
        return n;
    }
}

