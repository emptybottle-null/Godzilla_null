/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

public class ParametersWithSBox
implements CipherParameters {
    private CipherParameters parameters;
    private byte[] sBox;

    public ParametersWithSBox(CipherParameters cipherParameters, byte[] byArray) {
        this.parameters = cipherParameters;
        this.sBox = byArray;
    }

    public byte[] getSBox() {
        return this.sBox;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}

