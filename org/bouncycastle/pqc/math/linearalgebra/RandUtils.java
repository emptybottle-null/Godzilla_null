/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;

public class RandUtils {
    static int nextInt(SecureRandom secureRandom, int n) {
        int n2;
        int n3;
        if ((n & -n) == n) {
            return (int)((long)n * (long)(secureRandom.nextInt() >>> 1) >> 31);
        }
        while ((n3 = secureRandom.nextInt() >>> 1) - (n2 = n3 % n) + (n - 1) < 0) {
        }
        return n2;
    }
}

