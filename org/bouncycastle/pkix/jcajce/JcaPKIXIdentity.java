/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkix.jcajce;

import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.pkix.PKIXIdentity;

public class JcaPKIXIdentity
extends PKIXIdentity {
    private final PrivateKey privKey;
    private final X509Certificate[] certs;

    private static PrivateKeyInfo getPrivateKeyInfo(PrivateKey privateKey) {
        try {
            return PrivateKeyInfo.getInstance(privateKey.getEncoded());
        } catch (Exception exception) {
            return null;
        }
    }

    private static X509CertificateHolder[] getCertificates(X509Certificate[] x509CertificateArray) {
        X509CertificateHolder[] x509CertificateHolderArray = new X509CertificateHolder[x509CertificateArray.length];
        try {
            for (int i = 0; i != x509CertificateHolderArray.length; ++i) {
                x509CertificateHolderArray[i] = new JcaX509CertificateHolder(x509CertificateArray[i]);
            }
            return x509CertificateHolderArray;
        } catch (CertificateEncodingException certificateEncodingException) {
            throw new IllegalArgumentException("Unable to process certificates: " + certificateEncodingException.getMessage());
        }
    }

    public JcaPKIXIdentity(PrivateKey privateKey, X509Certificate[] x509CertificateArray) {
        super(JcaPKIXIdentity.getPrivateKeyInfo(privateKey), JcaPKIXIdentity.getCertificates(x509CertificateArray));
        this.privKey = privateKey;
        this.certs = new X509Certificate[x509CertificateArray.length];
        System.arraycopy(x509CertificateArray, 0, this.certs, 0, x509CertificateArray.length);
    }

    public PrivateKey getPrivateKey() {
        return this.privKey;
    }

    public X509Certificate getX509Certificate() {
        return this.certs[0];
    }
}

