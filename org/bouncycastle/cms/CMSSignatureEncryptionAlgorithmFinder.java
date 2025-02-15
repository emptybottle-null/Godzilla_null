/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public interface CMSSignatureEncryptionAlgorithmFinder {
    public AlgorithmIdentifier findEncryptionAlgorithm(AlgorithmIdentifier var1);
}

