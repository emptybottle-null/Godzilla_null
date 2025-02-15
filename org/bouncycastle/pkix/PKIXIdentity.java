/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkix;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.KeyTransRecipientId;
import org.bouncycastle.cms.RecipientId;

public class PKIXIdentity {
    private final PrivateKeyInfo privateKeyInfo;
    private final X509CertificateHolder[] certificateHolders;

    public PKIXIdentity(PrivateKeyInfo privateKeyInfo, X509CertificateHolder[] x509CertificateHolderArray) {
        this.privateKeyInfo = privateKeyInfo;
        this.certificateHolders = new X509CertificateHolder[x509CertificateHolderArray.length];
        System.arraycopy(x509CertificateHolderArray, 0, this.certificateHolders, 0, x509CertificateHolderArray.length);
    }

    public PrivateKeyInfo getPrivateKeyInfo() {
        return this.privateKeyInfo;
    }

    public X509CertificateHolder getCertificate() {
        return this.certificateHolders[0];
    }

    public RecipientId getRecipientId() {
        return new KeyTransRecipientId(this.certificateHolders[0].getIssuer(), this.certificateHolders[0].getSerialNumber(), this.getSubjectKeyIdentifier());
    }

    private byte[] getSubjectKeyIdentifier() {
        SubjectKeyIdentifier subjectKeyIdentifier = SubjectKeyIdentifier.fromExtensions(this.certificateHolders[0].getExtensions());
        if (subjectKeyIdentifier == null) {
            return null;
        }
        return subjectKeyIdentifier.getKeyIdentifier();
    }
}

