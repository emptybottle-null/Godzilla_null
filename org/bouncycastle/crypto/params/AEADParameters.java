/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;

public class AEADParameters
implements CipherParameters {
    private byte[] associatedText;
    private byte[] nonce;
    private KeyParameter key;
    private int macSize;

    public AEADParameters(KeyParameter keyParameter, int n, byte[] byArray) {
        this(keyParameter, n, byArray, null);
    }

    public AEADParameters(KeyParameter keyParameter, int n, byte[] byArray, byte[] byArray2) {
        this.key = keyParameter;
        this.nonce = byArray;
        this.macSize = n;
        this.associatedText = byArray2;
    }

    public KeyParameter getKey() {
        return this.key;
    }

    public int getMacSize() {
        return this.macSize;
    }

    public byte[] getAssociatedText() {
        return this.associatedText;
    }

    public byte[] getNonce() {
        return this.nonce;
    }
}

