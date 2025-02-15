/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

import java.util.Arrays;

public class PESignature {
    private static byte[] expected1 = new byte[]{80, 69, 0, 0};
    private static byte[] expected2 = new byte[]{80, 105, 0, 0};
    private byte[] signature;

    public byte[] getSignature() {
        return this.signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public boolean isValid() {
        return Arrays.equals(expected1, this.signature) || Arrays.equals(expected2, this.signature);
    }
}

