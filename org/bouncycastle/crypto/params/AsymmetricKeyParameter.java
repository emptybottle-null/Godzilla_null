/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

public class AsymmetricKeyParameter
implements CipherParameters {
    boolean privateKey;

    public AsymmetricKeyParameter(boolean bl) {
        this.privateKey = bl;
    }

    public boolean isPrivate() {
        return this.privateKey;
    }
}

