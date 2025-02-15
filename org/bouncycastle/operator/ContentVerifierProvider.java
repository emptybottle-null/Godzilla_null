/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.OperatorCreationException;

public interface ContentVerifierProvider {
    public boolean hasAssociatedCertificate();

    public X509CertificateHolder getAssociatedCertificate();

    public ContentVerifier get(AlgorithmIdentifier var1) throws OperatorCreationException;
}

