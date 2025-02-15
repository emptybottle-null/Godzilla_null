/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;

public class ExchangePair {
    private final AsymmetricKeyParameter publicKey;
    private final byte[] shared;

    public ExchangePair(AsymmetricKeyParameter asymmetricKeyParameter, byte[] byArray) {
        this.publicKey = asymmetricKeyParameter;
        this.shared = Arrays.clone(byArray);
    }

    public AsymmetricKeyParameter getPublicKey() {
        return this.publicKey;
    }

    public byte[] getSharedValue() {
        return Arrays.clone(this.shared);
    }
}

