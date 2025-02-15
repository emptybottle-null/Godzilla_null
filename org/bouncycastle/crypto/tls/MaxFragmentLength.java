/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

public class MaxFragmentLength {
    public static final short pow2_9 = 1;
    public static final short pow2_10 = 2;
    public static final short pow2_11 = 3;
    public static final short pow2_12 = 4;

    public static boolean isValid(short s) {
        return s >= 1 && s <= 4;
    }
}

