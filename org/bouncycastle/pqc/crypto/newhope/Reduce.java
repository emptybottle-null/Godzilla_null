/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.newhope;

class Reduce {
    static final int QInv = 12287;
    static final int RLog = 18;
    static final int RMask = 262143;

    Reduce() {
    }

    static short montgomery(int n) {
        int n2 = n * 12287;
        n2 &= 0x3FFFF;
        n2 *= 12289;
        return (short)((n2 += n) >>> 18);
    }

    static short barrett(short s) {
        int n = s & 0xFFFF;
        int n2 = n * 5 >>> 16;
        return (short)(n - (n2 *= 12289));
    }
}

