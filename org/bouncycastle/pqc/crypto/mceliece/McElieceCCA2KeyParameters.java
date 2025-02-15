/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class McElieceCCA2KeyParameters
extends AsymmetricKeyParameter {
    private String params;

    public McElieceCCA2KeyParameters(boolean bl, String string) {
        super(bl);
        this.params = string;
    }

    public String getDigest() {
        return this.params;
    }
}

