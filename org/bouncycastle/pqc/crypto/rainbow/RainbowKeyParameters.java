/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.rainbow;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class RainbowKeyParameters
extends AsymmetricKeyParameter {
    private int docLength;

    public RainbowKeyParameters(boolean bl, int n) {
        super(bl);
        this.docLength = n;
    }

    public int getDocLength() {
        return this.docLength;
    }
}

