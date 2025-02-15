/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.raw;

import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;

public abstract class Nat512 {
    public static void mul(int[] nArray, int[] nArray2, int[] nArray3) {
        Nat256.mul(nArray, nArray2, nArray3);
        Nat256.mul(nArray, 8, nArray2, 8, nArray3, 16);
        int n = Nat256.addToEachOther(nArray3, 8, nArray3, 16);
        int n2 = n + Nat256.addTo(nArray3, 0, nArray3, 8, 0);
        n += Nat256.addTo(nArray3, 24, nArray3, 16, n2);
        int[] nArray4 = Nat256.create();
        int[] nArray5 = Nat256.create();
        boolean bl = Nat256.diff(nArray, 8, nArray, 0, nArray4, 0) != Nat256.diff(nArray2, 8, nArray2, 0, nArray5, 0);
        int[] nArray6 = Nat256.createExt();
        Nat256.mul(nArray4, nArray5, nArray6);
        Nat.addWordAt(32, n += bl ? Nat.addTo(16, nArray6, 0, nArray3, 8) : Nat.subFrom(16, nArray6, 0, nArray3, 8), nArray3, 24);
    }

    public static void square(int[] nArray, int[] nArray2) {
        Nat256.square(nArray, nArray2);
        Nat256.square(nArray, 8, nArray2, 16);
        int n = Nat256.addToEachOther(nArray2, 8, nArray2, 16);
        int n2 = n + Nat256.addTo(nArray2, 0, nArray2, 8, 0);
        n += Nat256.addTo(nArray2, 24, nArray2, 16, n2);
        int[] nArray3 = Nat256.create();
        Nat256.diff(nArray, 8, nArray, 0, nArray3, 0);
        int[] nArray4 = Nat256.createExt();
        Nat256.square(nArray3, nArray4);
        Nat.addWordAt(32, n += Nat.subFrom(16, nArray4, 0, nArray2, 8), nArray2, 24);
    }
}

