/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import org.apache.commons.math3.random.AbstractWell;

public class Well19937a
extends AbstractWell {
    private static final long serialVersionUID = -7462102162223815419L;
    private static final int K = 19937;
    private static final int M1 = 70;
    private static final int M2 = 179;
    private static final int M3 = 449;

    public Well19937a() {
        super(19937, 70, 179, 449);
    }

    public Well19937a(int seed) {
        super(19937, 70, 179, 449, seed);
    }

    public Well19937a(int[] seed) {
        super(19937, 70, 179, 449, seed);
    }

    public Well19937a(long seed) {
        super(19937, 70, 179, 449, seed);
    }

    protected int next(int bits) {
        int indexRm1 = this.iRm1[this.index];
        int indexRm2 = this.iRm2[this.index];
        int v0 = this.v[this.index];
        int vM1 = this.v[this.i1[this.index]];
        int vM2 = this.v[this.i2[this.index]];
        int vM3 = this.v[this.i3[this.index]];
        int z0 = Integer.MIN_VALUE & this.v[indexRm1] ^ Integer.MAX_VALUE & this.v[indexRm2];
        int z1 = v0 ^ v0 << 25 ^ (vM1 ^ vM1 >>> 27);
        int z2 = vM2 >>> 9 ^ (vM3 ^ vM3 >>> 1);
        int z3 = z1 ^ z2;
        int z4 = z0 ^ (z1 ^ z1 << 9) ^ (z2 ^ z2 << 21) ^ (z3 ^ z3 >>> 21);
        this.v[this.index] = z3;
        this.v[indexRm1] = z4;
        int n = indexRm2;
        this.v[n] = this.v[n] & Integer.MIN_VALUE;
        this.index = indexRm1;
        return z4 >>> 32 - bits;
    }
}

