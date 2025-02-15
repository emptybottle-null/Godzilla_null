/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.spec;

import java.security.InvalidParameterException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;

public class McElieceKeyGenParameterSpec
implements AlgorithmParameterSpec {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private int m;
    private int t;
    private int n;
    private int fieldPoly;

    public McElieceKeyGenParameterSpec() {
        this(11, 50);
    }

    public McElieceKeyGenParameterSpec(int n) {
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
    }

    public McElieceKeyGenParameterSpec(int n, int n2) throws InvalidParameterException {
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
    }

    public McElieceKeyGenParameterSpec(int n, int n2, int n3) {
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

