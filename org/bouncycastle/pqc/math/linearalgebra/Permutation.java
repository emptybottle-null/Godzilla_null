/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import org.bouncycastle.pqc.math.linearalgebra.IntUtils;
import org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions;
import org.bouncycastle.pqc.math.linearalgebra.LittleEndianConversions;
import org.bouncycastle.pqc.math.linearalgebra.RandUtils;

public class Permutation {
    private int[] perm;

    public Permutation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("invalid length");
        }
        this.perm = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            this.perm[i] = i;
        }
    }

    public Permutation(int[] nArray) {
        if (!this.isPermutation(nArray)) {
            throw new IllegalArgumentException("array is not a permutation vector");
        }
        this.perm = IntUtils.clone(nArray);
    }

    public Permutation(byte[] byArray) {
        int n;
        if (byArray.length <= 4) {
            throw new IllegalArgumentException("invalid encoding");
        }
        int n2 = LittleEndianConversions.OS2IP(byArray, 0);
        if (byArray.length != 4 + n2 * (n = IntegerFunctions.ceilLog256(n2 - 1))) {
            throw new IllegalArgumentException("invalid encoding");
        }
        this.perm = new int[n2];
        for (int i = 0; i < n2; ++i) {
            this.perm[i] = LittleEndianConversions.OS2IP(byArray, 4 + i * n, n);
        }
        if (!this.isPermutation(this.perm)) {
            throw new IllegalArgumentException("invalid encoding");
        }
    }

    public Permutation(int n, SecureRandom secureRandom) {
        int n2;
        if (n <= 0) {
            throw new IllegalArgumentException("invalid length");
        }
        this.perm = new int[n];
        int[] nArray = new int[n];
        for (n2 = 0; n2 < n; ++n2) {
            nArray[n2] = n2;
        }
        n2 = n;
        for (int i = 0; i < n; ++i) {
            int n3 = RandUtils.nextInt(secureRandom, n2);
            this.perm[i] = nArray[n3];
            nArray[n3] = nArray[--n2];
        }
    }

    public byte[] getEncoded() {
        int n = this.perm.length;
        int n2 = IntegerFunctions.ceilLog256(n - 1);
        byte[] byArray = new byte[4 + n * n2];
        LittleEndianConversions.I2OSP(n, byArray, 0);
        for (int i = 0; i < n; ++i) {
            LittleEndianConversions.I2OSP(this.perm[i], byArray, 4 + i * n2, n2);
        }
        return byArray;
    }

    public int[] getVector() {
        return IntUtils.clone(this.perm);
    }

    public Permutation computeInverse() {
        Permutation permutation = new Permutation(this.perm.length);
        for (int i = this.perm.length - 1; i >= 0; --i) {
            permutation.perm[this.perm[i]] = i;
        }
        return permutation;
    }

    public Permutation rightMultiply(Permutation permutation) {
        if (permutation.perm.length != this.perm.length) {
            throw new IllegalArgumentException("length mismatch");
        }
        Permutation permutation2 = new Permutation(this.perm.length);
        for (int i = this.perm.length - 1; i >= 0; --i) {
            permutation2.perm[i] = this.perm[permutation.perm[i]];
        }
        return permutation2;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Permutation)) {
            return false;
        }
        Permutation permutation = (Permutation)object;
        return IntUtils.equals(this.perm, permutation.perm);
    }

    public String toString() {
        String string = "[" + this.perm[0];
        for (int i = 1; i < this.perm.length; ++i) {
            string = string + ", " + this.perm[i];
        }
        string = string + "]";
        return string;
    }

    public int hashCode() {
        return this.perm.hashCode();
    }

    private boolean isPermutation(int[] nArray) {
        int n = nArray.length;
        boolean[] blArray = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (nArray[i] < 0 || nArray[i] >= n || blArray[nArray[i]]) {
                return false;
            }
            blArray[nArray[i]] = true;
        }
        return true;
    }
}

