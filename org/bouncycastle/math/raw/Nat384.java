/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.raw;

import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;

public abstract class Nat384 {
    public static void mul(int[] nArray, int[] nArray2, int[] nArray3) {
        Nat192.mul(nArray, nArray2, nArray3);
        Nat192.mul(nArray, 6, nArray2, 6, nArray3, 12);
        int n = Nat192.addToEachOther(nArray3, 6, nArray3, 12);
        int n2 = n + Nat192.addTo(nArray3, 0, nArray3, 6, 0);
        n += Nat192.addTo(nArray3, 18, nArray3, 12, n2);
        int[] nArray4 = Nat192.create();
        int[] nArray5 = Nat192.create();
        boolean bl = Nat192.diff(nArray, 6, nArray, 0, nArray4, 0) != Nat192.diff(nArray2, 6, nArray2, 0, nArray5, 0);
        int[] nArray6 = Nat192.createExt();
        Nat192.mul(nArray4, nArray5, nArray6);
        Nat.addWordAt(24, n += bl ? Nat.addTo(12, nArray6, 0, nArray3, 6) : Nat.subFrom(12, nArray6, 0, nArray3, 6), nArray3, 18);
    }

    public static void square(int[] nArray, int[] nArray2) {
        Nat192.square(nArray, nArray2);
        Nat192.square(nArray, 6, nArray2, 12);
        int n = Nat192.addToEachOther(nArray2, 6, nArray2, 12);
        int n2 = n + Nat192.addTo(nArray2, 0, nArray2, 6, 0);
        n += Nat192.addTo(nArray2, 18, nArray2, 12, n2);
        int[] nArray3 = Nat192.create();
        Nat192.diff(nArray, 6, nArray, 0, nArray3, 0);
        int[] nArray4 = Nat192.createExt();
        Nat192.square(nArray3, nArray4);
        Nat.addWordAt(24, n += Nat.subFrom(12, nArray4, 0, nArray2, 6), nArray2, 18);
    }
}

