/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public interface SignatureAlgorithmIdentifierFinder {
    public AlgorithmIdentifier find(String var1);
}

