/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.crypto.mceliece.McElieceKeyParameters;
import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.GoppaCode;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2m;

public class McEliecePrivateKeyParameters
extends McElieceKeyParameters {
    private String oid;
    private int n;
    private int k;
    private GF2mField field;
    private PolynomialGF2mSmallM goppaPoly;
    private GF2Matrix sInv;
    private Permutation p1;
    private Permutation p2;
    private GF2Matrix h;
    private PolynomialGF2mSmallM[] qInv;

    public McEliecePrivateKeyParameters(int n, int n2, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, Permutation permutation, Permutation permutation2, GF2Matrix gF2Matrix) {
        super(true, null);
        this.k = n2;
        this.n = n;
        this.field = gF2mField;
        this.goppaPoly = polynomialGF2mSmallM;
        this.sInv = gF2Matrix;
        this.p1 = permutation;
        this.p2 = permutation2;
        this.h = GoppaCode.createCanonicalCheckMatrix(gF2mField, polynomialGF2mSmallM);
        PolynomialRingGF2m polynomialRingGF2m = new PolynomialRingGF2m(gF2mField, polynomialGF2mSmallM);
        this.qInv = polynomialRingGF2m.getSquareRootMatrix();
    }

    public McEliecePrivateKeyParameters(int n, int n2, byte[] byArray, byte[] byArray2, byte[] byArray3, byte[] byArray4, byte[] byArray5, byte[] byArray6, byte[][] byArray7) {
        super(true, null);
        this.n = n;
        this.k = n2;
        this.field = new GF2mField(byArray);
        this.goppaPoly = new PolynomialGF2mSmallM(this.field, byArray2);
        this.sInv = new GF2Matrix(byArray3);
        this.p1 = new Permutation(byArray4);
        this.p2 = new Permutation(byArray5);
        this.h = new GF2Matrix(byArray6);
        this.qInv = new PolynomialGF2mSmallM[byArray7.length];
        for (int i = 0; i < byArray7.length; ++i) {
            this.qInv[i] = new PolynomialGF2mSmallM(this.field, byArray7[i]);
        }
    }

    public int getN() {
        return this.n;
    }

    public int getK() {
        return this.k;
    }

    public GF2mField getField() {
        return this.field;
    }

    public PolynomialGF2mSmallM getGoppaPoly() {
        return this.goppaPoly;
    }

    public GF2Matrix getSInv() {
        return this.sInv;
    }

    public Permutation getP1() {
        return this.p1;
    }

    public Permutation getP2() {
        return this.p2;
    }

    public GF2Matrix getH() {
        return this.h;
    }

    public PolynomialGF2mSmallM[] getQInv() {
        return this.qInv;
    }
}

