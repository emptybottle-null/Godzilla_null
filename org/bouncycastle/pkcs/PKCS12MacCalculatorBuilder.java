/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.OperatorCreationException;

public interface PKCS12MacCalculatorBuilder {
    public MacCalculator build(char[] var1) throws OperatorCreationException;

    public AlgorithmIdentifier getDigestAlgorithmIdentifier();
}

