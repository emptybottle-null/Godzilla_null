/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.params.DESParameters;

public class DESedeParameters
extends DESParameters {
    public static final int DES_EDE_KEY_LENGTH = 24;

    public DESedeParameters(byte[] byArray) {
        super(byArray);
        if (DESedeParameters.isWeakKey(byArray, 0, byArray.length)) {
            throw new IllegalArgumentException("attempt to create weak DESede key");
        }
    }

    public static boolean isWeakKey(byte[] byArray, int n, int n2) {
        for (int i = n; i < n2; i += 8) {
            if (!DESParameters.isWeakKey(byArray, i)) continue;
            return true;
        }
        return false;
    }

    public static boolean isWeakKey(byte[] byArray, int n) {
        return DESedeParameters.isWeakKey(byArray, n, byArray.length - n);
    }

    public static boolean isRealEDEKey(byte[] byArray, int n) {
        return byArray.length == 16 ? DESedeParameters.isReal2Key(byArray, n) : DESedeParameters.isReal3Key(byArray, n);
    }

    public static boolean isReal2Key(byte[] byArray, int n) {
        boolean bl = false;
        for (int i = n; i != n + 8; ++i) {
            if (byArray[i] == byArray[i + 8]) continue;
            bl = true;
        }
        return bl;
    }

    public static boolean isReal3Key(byte[] byArray, int n) {
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        for (int i = n; i != n + 8; ++i) {
            bl |= byArray[i] != byArray[i + 8];
            bl2 |= byArray[i] != byArray[i + 16];
            bl3 |= byArray[i + 8] != byArray[i + 16];
        }
        return bl && bl2 && bl3;
    }
}

