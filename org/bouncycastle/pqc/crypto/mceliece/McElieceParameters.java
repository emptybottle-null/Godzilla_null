/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;

public class McElieceParameters
implements CipherParameters {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private int m;
    private int t;
    private int n;
    private int fieldPoly;
    private Digest digest;

    public McElieceParameters() {
        this(11, 50);
    }

    public McElieceParameters(Digest digest) {
        this(11, 50, digest);
    }

    public McElieceParameters(int n) {
        this(n, null);
    }

    public McElieceParameters(int n, Digest digest) {
        if (n < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        this.m = 0;
        this.n = 1;
        while (this.n < n) {
            this.n <<= 1;
            ++this.m;
        }
        this.t = this.n >>> 1;
        this.t /= this.m;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(this.m);
        this.digest = digest;
    }

    public McElieceParameters(int n, int n2) {
        this(n, n2, null);
    }

    public McElieceParameters(int n, int n2, Digest digest) {
        if (n < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (n > 32) {
            throw new IllegalArgumentException("m is too large");
        }
        this.m = n;
        this.n = 1 << n;
        if (n2 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (n2 > this.n) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        this.t = n2;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(n);
        this.digest = digest;
    }

    public McElieceParameters(int n, int n2, int n3) {
        this(n, n2, n3, null);
    }

    public McElieceParameters(int n, int n2, int n3, Digest digest) {
        this.m = n;
        if (n < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (n > 32) {
            throw new IllegalArgumentException(" m is too large");
        }
        this.n = 1 << n;
        this.t = n2;
        if (n2 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (n2 > this.n) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        if (PolynomialRingGF2.degree(n3) != n || !PolynomialRingGF2.isIrreducible(n3)) {
            throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
        }
        this.fieldPoly = n3;
        this.digest = digest;
    }

    public int getM() {
        return this.m;
    }

    public int getN() {
        return this.n;
    }

    public int getT() {
        return this.t;
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }
}

