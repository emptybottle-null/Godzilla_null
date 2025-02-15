/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.raw;

import java.util.Random;
import org.bouncycastle.math.raw.Nat;

public abstract class Mod {
    public static int inverse32(int n) {
        int n2 = n;
        n2 *= 2 - n * n2;
        n2 *= 2 - n * n2;
        n2 *= 2 - n * n2;
        n2 *= 2 - n * n2;
        return n2;
    }

    public static void invert(int[] nArray, int[] nArray2, int[] nArray3) {
        int n = nArray.length;
        if (Nat.isZero(n, nArray2)) {
            throw new IllegalArgumentException("'x' cannot be 0");
        }
        if (Nat.isOne(n, nArray2)) {
            System.arraycopy(nArray2, 0, nArray3, 0, n);
            return;
        }
        int[] nArray4 = Nat.copy(n, nArray2);
        int[] nArray5 = Nat.create(n);
        nArray5[0] = 1;
        int n2 = 0;
        if ((nArray4[0] & 1) == 0) {
            n2 = Mod.inversionStep(nArray, nArray4, n, nArray5, n2);
        }
        if (Nat.isOne(n, nArray4)) {
            Mod.inversionResult(nArray, n2, nArray5, nArray3);
            return;
        }
        int[] nArray6 = Nat.copy(n, nArray);
        int[] nArray7 = Nat.create(n);
        int n3 = 0;
        int n4 = n;
        while (true) {
            if (nArray4[n4 - 1] == 0 && nArray6[n4 - 1] == 0) {
                --n4;
                continue;
            }
            if (Nat.gte(n4, nArray4, nArray6)) {
                Nat.subFrom(n4, nArray6, nArray4);
                n2 += Nat.subFrom(n, nArray7, nArray5) - n3;
                n2 = Mod.inversionStep(nArray, nArray4, n4, nArray5, n2);
                if (!Nat.isOne(n4, nArray4)) continue;
                Mod.inversionResult(nArray, n2, nArray5, nArray3);
                return;
            }
            Nat.subFrom(n4, nArray4, nArray6);
            n3 += Nat.subFrom(n, nArray5, nArray7) - n2;
            n3 = Mod.inversionStep(nArray, nArray6, n4, nArray7, n3);
            if (Nat.isOne(n4, nArray6)) break;
        }
        Mod.inversionResult(nArray, n3, nArray7, nArray3);
    }

    public static int[] random(int[] nArray) {
        int n = nArray.length;
        Random random = new Random();
        int[] nArray2 = Nat.create(n);
        int n2 = nArray[n - 1];
        n2 |= n2 >>> 1;
        n2 |= n2 >>> 2;
        n2 |= n2 >>> 4;
        n2 |= n2 >>> 8;
        n2 |= n2 >>> 16;
        do {
            for (int i = 0; i != n; ++i) {
                nArray2[i] = random.nextInt();
            }
            int n3 = n - 1;
            nArray2[n3] = nArray2[n3] & n2;
        } while (Nat.gte(n, nArray2, nArray));
        return nArray2;
    }

    public static void add(int[] nArray, int[] nArray2, int[] nArray3, int[] nArray4) {
        int n = nArray.length;
        int n2 = Nat.add(n, nArray2, nArray3, nArray4);
        if (n2 != 0) {
            Nat.subFrom(n, nArray, nArray4);
        }
    }

    public static void subtract(int[] nArray, int[] nArray2, int[] nArray3, int[] nArray4) {
        int n = nArray.length;
        int n2 = Nat.sub(n, nArray2, nArray3, nArray4);
        if (n2 != 0) {
            Nat.addTo(n, nArray, nArray4);
        }
    }

    private static void inversionResult(int[] nArray, int n, int[] nArray2, int[] nArray3) {
        if (n < 0) {
            Nat.add(nArray.length, nArray2, nArray, nArray3);
        } else {
            System.arraycopy(nArray2, 0, nArray3, 0, nArray.length);
        }
    }

    private static int inversionStep(int[] nArray, int[] nArray2, int n, int[] nArray3, int n2) {
        int n3 = nArray.length;
        int n4 = 0;
        while (nArray2[0] == 0) {
            Nat.shiftDownWord(n, nArray2, 0);
            n4 += 32;
        }
        int n5 = Mod.getTrailingZeroes(nArray2[0]);
        if (n5 > 0) {
            Nat.shiftDownBits(n, nArray2, n5, 0);
            n4 += n5;
        }
        for (n5 = 0; n5 < n4; ++n5) {
            if ((nArray3[0] & 1) != 0) {
                n2 = n2 < 0 ? (n2 += Nat.addTo(n3, nArray, nArray3)) : (n2 += Nat.subFrom(n3, nArray, nArray3));
            }
            Nat.shiftDownBit(n3, nArray3, n2);
        }
        return n2;
    }

    private static int getTrailingZeroes(int n) {
        int n2 = 0;
        while ((n & 1) == 0) {
            n >>>= 1;
            ++n2;
        }
        return n2;
    }
}

