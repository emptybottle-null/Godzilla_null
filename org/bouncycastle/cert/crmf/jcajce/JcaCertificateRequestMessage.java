/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf.jcajce;

import java.io.IOException;
import java.security.Provider;
import java.security.PublicKey;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.crmf.CertReqMsg;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.CertificateRequestMessage;
import org.bouncycastle.cert.crmf.jcajce.CRMFHelper;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;

public class JcaCertificateRequestMessage
extends CertificateRequestMessage {
    private CRMFHelper helper = new CRMFHelper(new DefaultJcaJceHelper());

    public JcaCertificateRequestMessage(byte[] byArray) {
        this(CertReqMsg.getInstance(byArray));
    }

    public JcaCertificateRequestMessage(CertificateRequestMessage certificateRequestMessage) {
        this(certificateRequestMessage.toASN1Structure());
    }

    public JcaCertificateRequestMessage(CertReqMsg certReqMsg) {
        super(certReqMsg);
    }

    public JcaCertificateRequestMessage setProvider(String string) {
        this.helper = new CRMFHelper(new NamedJcaJceHelper(string));
        return this;
    }

    public JcaCertificateRequestMessage setProvider(Provider provider) {
        this.helper = new CRMFHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public X500Principal getSubjectX500Principal() {
        X500Name x500Name = this.getCertTemplate().getSubject();
        if (x500Name != null) {
            try {
                return new X500Principal(x500Name.getEncoded("DER"));
            } catch (IOException iOException) {
                throw new IllegalStateException("unable to construct DER encoding of name: " + iOException.getMessage());
            }
        }
        return null;
    }

    public PublicKey getPublicKey() throws CRMFException {
        SubjectPublicKeyInfo subjectPublicKeyInfo = this.getCertTemplate().getPublicKey();
        if (subjectPublicKeyInfo != null) {
            return this.helper.toPublicKey(subjectPublicKeyInfo);
        }
        return null;
    }
}

