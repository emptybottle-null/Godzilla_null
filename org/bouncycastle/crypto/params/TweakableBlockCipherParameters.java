/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;

public class TweakableBlockCipherParameters
implements CipherParameters {
    private final byte[] tweak;
    private final KeyParameter key;

    public TweakableBlockCipherParameters(KeyParameter keyParameter, byte[] byArray) {
        this.key = keyParameter;
        this.tweak = Arrays.clone(byArray);
    }

    public KeyParameter getKey() {
        return this.key;
    }

    public byte[] getTweak() {
        return this.tweak;
    }
}

