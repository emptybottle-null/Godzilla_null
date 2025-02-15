/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

public class ParametersWithIV
implements CipherParameters {
    private byte[] iv;
    private CipherParameters parameters;

    public ParametersWithIV(CipherParameters cipherParameters, byte[] byArray) {
        this(cipherParameters, byArray, 0, byArray.length);
    }

    public ParametersWithIV(CipherParameters cipherParameters, byte[] byArray, int n, int n2) {
        this.iv = new byte[n2];
        this.parameters = cipherParameters;
        System.arraycopy(byArray, n, this.iv, 0, n2);
    }

    public byte[] getIV() {
        return this.iv;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}

