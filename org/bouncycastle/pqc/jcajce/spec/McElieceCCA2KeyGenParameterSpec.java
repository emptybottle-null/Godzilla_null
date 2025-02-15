/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;

public class McElieceCCA2KeyGenParameterSpec
implements AlgorithmParameterSpec {
    public static final String SHA1 = "SHA-1";
    public static final String SHA224 = "SHA-224";
    public static final String SHA256 = "SHA-256";
    public static final String SHA384 = "SHA-384";
    public static final String SHA512 = "SHA-512";
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private final int m;
    private final int t;
    private final int n;
    private int fieldPoly;
    private final String digest;

    public McElieceCCA2KeyGenParameterSpec() {
        this(11, 50, SHA256);
    }

    public McElieceCCA2KeyGenParameterSpec(int n) {
        this(n, SHA256);
    }

    public McElieceCCA2KeyGenParameterSpec(int n, String string) {
        if (n < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        int n2 = 0;
        int n3 = 1;
        while (n3 < n) {
            n3 <<= 1;
            ++n2;
        }
        this.t = (n3 >>> 1) / n2;
        this.m = n2;
        this.n = n3;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(n2);
        this.digest = string;
    }

    public McElieceCCA2KeyGenParameterSpec(int n, int n2) {
        this(n, n2, SHA256);
    }

    public McElieceCCA2KeyGenParameterSpec(int n, int n2, String string) {
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
        this.digest = string;
    }

    public McElieceCCA2KeyGenParameterSpec(int n, int n2, int n3) {
        this(n, n2, n3, SHA256);
    }

    public McElieceCCA2KeyGenParameterSpec(int n, int n2, int n3, String string) {
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
        this.digest = string;
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

    public String getDigest() {
        return this.digest;
    }
}

