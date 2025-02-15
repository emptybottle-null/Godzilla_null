/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;

public class ISO18033KDFParameters
implements DerivationParameters {
    byte[] seed;

    public ISO18033KDFParameters(byte[] byArray) {
        this.seed = byArray;
    }

    public byte[] getSeed() {
        return this.seed;
    }
}

