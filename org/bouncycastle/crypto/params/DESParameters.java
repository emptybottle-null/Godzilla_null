/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.params.KeyParameter;

public class DESParameters
extends KeyParameter {
    public static final int DES_KEY_LENGTH = 8;
    private static final int N_DES_WEAK_KEYS = 16;
    private static byte[] DES_weak_keys = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 31, 31, 31, 31, 14, 14, 14, 14, -32, -32, -32, -32, -15, -15, -15, -15, -2, -2, -2, -2, -2, -2, -2, -2, 1, -2, 1, -2, 1, -2, 1, -2, 31, -32, 31, -32, 14, -15, 14, -15, 1, -32, 1, -32, 1, -15, 1, -15, 31, -2, 31, -2, 14, -2, 14, -2, 1, 31, 1, 31, 1, 14, 1, 14, -32, -2, -32, -2, -15, -2, -15, -2, -2, 1, -2, 1, -2, 1, -2, 1, -32, 31, -32, 31, -15, 14, -15, 14, -32, 1, -32, 1, -15, 1, -15, 1, -2, 31, -2, 31, -2, 14, -2, 14, 31, 1, 31, 1, 14, 1, 14, 1, -2, -32, -2, -32, -2, -15, -2, -15};

    public DESParameters(byte[] byArray) {
        super(byArray);
        if (DESParameters.isWeakKey(byArray, 0)) {
            throw new IllegalArgumentException("attempt to create weak DES key");
        }
    }

    public static boolean isWeakKey(byte[] byArray, int n) {
        if (byArray.length - n < 8) {
            throw new IllegalArgumentException("key material too short.");
        }
        block0: for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (byArray[j + n] != DES_weak_keys[i * 8 + j]) continue block0;
            }
            return true;
        }
        return false;
    }

    public static void setOddParity(byte[] byArray) {
        for (int i = 0; i < byArray.length; ++i) {
            byte by = byArray[i];
            byArray[i] = (byte)(by & 0xFE | (by >> 1 ^ by >> 2 ^ by >> 3 ^ by >> 4 ^ by >> 5 ^ by >> 6 ^ by >> 7 ^ 1) & 1);
        }
    }
}

