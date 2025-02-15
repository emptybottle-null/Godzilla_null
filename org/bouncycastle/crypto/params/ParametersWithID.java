/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

public class ParametersWithID
implements CipherParameters {
    private CipherParameters parameters;
    private byte[] id;

    public ParametersWithID(CipherParameters cipherParameters, byte[] byArray) {
        this.parameters = cipherParameters;
        this.id = byArray;
    }

    public byte[] getID() {
        return this.id;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}

