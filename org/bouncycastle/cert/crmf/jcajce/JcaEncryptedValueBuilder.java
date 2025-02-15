/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf.jcajce;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.crmf.EncryptedValue;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.EncryptedValueBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.operator.KeyWrapper;
import org.bouncycastle.operator.OutputEncryptor;

public class JcaEncryptedValueBuilder
extends EncryptedValueBuilder {
    public JcaEncryptedValueBuilder(KeyWrapper keyWrapper, OutputEncryptor outputEncryptor) {
        super(keyWrapper, outputEncryptor);
    }

    public EncryptedValue build(X509Certificate x509Certificate) throws CertificateEncodingException, CRMFException {
        return this.build(new JcaX509CertificateHolder(x509Certificate));
    }
}

