/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

public class IESParameters
implements CipherParameters {
    private byte[] derivation;
    private byte[] encoding;
    private int macKeySize;

    public IESParameters(byte[] byArray, byte[] byArray2, int n) {
        this.derivation = byArray;
        this.encoding = byArray2;
        this.macKeySize = n;
    }

    public byte[] getDerivationV() {
        return this.derivation;
    }

    public byte[] getEncodingV() {
        return this.encoding;
    }

    public int getMacKeySize() {
        return this.macKeySize;
    }
}

