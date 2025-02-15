/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;

public interface X509ContentVerifierProviderBuilder {
    public ContentVerifierProvider build(SubjectPublicKeyInfo var1) throws OperatorCreationException;

    public ContentVerifierProvider build(X509CertificateHolder var1) throws OperatorCreationException;
}

