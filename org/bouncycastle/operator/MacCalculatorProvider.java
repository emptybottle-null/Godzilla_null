/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.MacCalculator;

public interface MacCalculatorProvider {
    public MacCalculator get(AlgorithmIdentifier var1);
}

