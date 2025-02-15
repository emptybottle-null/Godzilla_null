/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

public class KeyParameter
implements CipherParameters {
    private byte[] key;

    public KeyParameter(byte[] byArray) {
        this(byArray, 0, byArray.length);
    }

    public KeyParameter(byte[] byArray, int n, int n2) {
        this.key = new byte[n2];
        System.arraycopy(byArray, n, this.key, 0, n2);
    }

    public byte[] getKey() {
        return this.key;
    }
}

