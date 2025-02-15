/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public interface AlgorithmNameFinder {
    public boolean hasAlgorithmName(ASN1ObjectIdentifier var1);

    public String getAlgorithmName(ASN1ObjectIdentifier var1);

    public String getAlgorithmName(AlgorithmIdentifier var1);
}

