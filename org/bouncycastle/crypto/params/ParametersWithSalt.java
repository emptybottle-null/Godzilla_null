/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

public class ParametersWithSalt
implements CipherParameters {
    private byte[] salt;
    private CipherParameters parameters;

    public ParametersWithSalt(CipherParameters cipherParameters, byte[] byArray) {
        this(cipherParameters, byArray, 0, byArray.length);
    }

    public ParametersWithSalt(CipherParameters cipherParameters, byte[] byArray, int n, int n2) {
        this.salt = new byte[n2];
        this.parameters = cipherParameters;
        System.arraycopy(byArray, n, this.salt, 0, n2);
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}

