/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.spec;

import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.util.Arrays;

public class AEADParameterSpec
extends IvParameterSpec {
    private final byte[] associatedData;
    private final int macSizeInBits;

    public AEADParameterSpec(byte[] byArray, int n) {
        this(byArray, n, null);
    }

    public AEADParameterSpec(byte[] byArray, int n, byte[] byArray2) {
        super(byArray);
        this.macSizeInBits = n;
        this.associatedData = Arrays.clone(byArray2);
    }

    public int getMacSizeInBits() {
        return this.macSizeInBits;
    }

    public byte[] getAssociatedData() {
        return Arrays.clone(this.associatedData);
    }

    public byte[] getNonce() {
        return this.getIV();
    }
}

