/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.ExchangePair;

public interface ExchangePairGenerator {
    public ExchangePair GenerateExchange(AsymmetricKeyParameter var1);

    public ExchangePair generateExchange(AsymmetricKeyParameter var1);
}

