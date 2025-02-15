/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import org.apache.commons.math3.random.AbstractWell;

public class Well512a
extends AbstractWell {
    private static final long serialVersionUID = -6104179812103820574L;
    private static final int K = 512;
    private static final int M1 = 13;
    private static final int M2 = 9;
    private static final int M3 = 5;

    public Well512a() {
        super(512, 13, 9, 5);
    }

    public Well512a(int seed) {
        super(512, 13, 9, 5, seed);
    }

    public Well512a(int[] seed) {
        super(512, 13, 9, 5, seed);
    }

    public Well512a(long seed) {
        super(512, 13, 9, 5, seed);
    }

    protected int next(int bits) {
        int indexRm1 = this.iRm1[this.index];
        int vi = this.v[this.index];
        int vi1 = this.v[this.i1[this.index]];
        int vi2 = this.v[this.i2[this.index]];
        int z0 = this.v[indexRm1];
        int z1 = vi ^ vi << 16 ^ (vi1 ^ vi1 << 15);
        int z2 = vi2 ^ vi2 >>> 11;
        int z3 = z1 ^ z2;
        int z4 = z0 ^ z0 << 2 ^ (z1 ^ z1 << 18) ^ z2 << 28 ^ (z3 ^ z3 << 5 & 0xDA442D24);
        this.v[this.index] = z3;
        this.v[indexRm1] = z4;
        this.index = indexRm1;
        return z4 >>> 32 - bits;
    }
}

