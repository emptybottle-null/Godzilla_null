/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

public class ParametersWithUKM
implements CipherParameters {
    private byte[] ukm;
    private CipherParameters parameters;

    public ParametersWithUKM(CipherParameters cipherParameters, byte[] byArray) {
        this(cipherParameters, byArray, 0, byArray.length);
    }

    public ParametersWithUKM(CipherParameters cipherParameters, byte[] byArray, int n, int n2) {
        this.ukm = new byte[n2];
        this.parameters = cipherParameters;
        System.arraycopy(byArray, n, this.ukm, 0, n2);
    }

    public byte[] getUKM() {
        return this.ukm;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}

