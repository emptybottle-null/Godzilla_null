/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.djb;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.djb.Curve25519;
import org.bouncycastle.math.ec.custom.djb.Curve25519Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

public class Curve25519FieldElement
extends ECFieldElement {
    public static final BigInteger Q = Curve25519.q;
    private static final int[] PRECOMP_POW2 = new int[]{1242472624, -991028441, -1389370248, 792926214, 1039914919, 726466713, 1338105611, 730014848};
    protected int[] x;

    public Curve25519FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for Curve25519FieldElement");
        }
        this.x = Curve25519Field.fromBigInteger(bigInteger);
    }

    public Curve25519FieldElement() {
        this.x = Nat256.create();
    }

    protected Curve25519FieldElement(int[] nArray) {
        this.x = nArray;
    }

    public boolean isZero() {
        return Nat256.isZero(this.x);
    }

    public boolean isOne() {
        return Nat256.isOne(this.x);
    }

    public boolean testBitZero() {
        return Nat256.getBit(this.x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat256.toBigInteger(this.x);
    }

    public String getFieldName() {
        return "Curve25519Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        Curve25519Field.add(this.x, ((Curve25519FieldElement)eCFieldElement).x, nArray);
        return new Curve25519FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat256.create();
        Curve25519Field.addOne(this.x, nArray);
        return new Curve25519FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        Curve25519Field.subtract(this.x, ((Curve25519FieldElement)eCFieldElement).x, nArray);
        return new Curve25519FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        Curve25519Field.multiply(this.x, ((Curve25519FieldElement)eCFieldElement).x, nArray);
        return new Curve25519FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        Mod.invert(Curve25519Field.P, ((Curve25519FieldElement)eCFieldElement).x, nArray);
        Curve25519Field.multiply(nArray, this.x, nArray);
        return new Curve25519FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat256.create();
        Curve25519Field.negate(this.x, nArray);
        return new Curve25519FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat256.create();
        Curve25519Field.square(this.x, nArray);
        return new Curve25519FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat256.create();
        Mod.invert(Curve25519Field.P, this.x, nArray);
        return new Curve25519FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat256.isZero(nArray) || Nat256.isOne(nArray)) {
            return this;
        }
        int[] nArray2 = Nat256.create();
        Curve25519Field.square(nArray, nArray2);
        Curve25519Field.multiply(nArray2, nArray, nArray2);
        int[] nArray3 = nArray2;
        Curve25519Field.square(nArray2, nArray3);
        Curve25519Field.multiply(nArray3, nArray, nArray3);
        int[] nArray4 = Nat256.create();
        Curve25519Field.square(nArray3, nArray4);
        Curve25519Field.multiply(nArray4, nArray, nArray4);
        int[] nArray5 = Nat256.create();
        Curve25519Field.squareN(nArray4, 3, nArray5);
        Curve25519Field.multiply(nArray5, nArray3, nArray5);
        int[] nArray6 = nArray3;
        Curve25519Field.squareN(nArray5, 4, nArray6);
        Curve25519Field.multiply(nArray6, nArray4, nArray6);
        int[] nArray7 = nArray5;
        Curve25519Field.squareN(nArray6, 4, nArray7);
        Curve25519Field.multiply(nArray7, nArray4, nArray7);
        int[] nArray8 = nArray4;
        Curve25519Field.squareN(nArray7, 15, nArray8);
        Curve25519Field.multiply(nArray8, nArray7, nArray8);
        int[] nArray9 = nArray7;
        Curve25519Field.squareN(nArray8, 30, nArray9);
        Curve25519Field.multiply(nArray9, nArray8, nArray9);
        int[] nArray10 = nArray8;
        Curve25519Field.squareN(nArray9, 60, nArray10);
        Curve25519Field.multiply(nArray10, nArray9, nArray10);
        int[] nArray11 = nArray9;
        Curve25519Field.squareN(nArray10, 11, nArray11);
        Curve25519Field.multiply(nArray11, nArray6, nArray11);
        int[] nArray12 = nArray6;
        Curve25519Field.squareN(nArray11, 120, nArray12);
        Curve25519Field.multiply(nArray12, nArray10, nArray12);
        int[] nArray13 = nArray12;
        Curve25519Field.square(nArray13, nArray13);
        int[] nArray14 = nArray10;
        Curve25519Field.square(nArray13, nArray14);
        if (Nat256.eq(nArray, nArray14)) {
            return new Curve25519FieldElement(nArray13);
        }
        Curve25519Field.multiply(nArray13, PRECOMP_POW2, nArray13);
        Curve25519Field.square(nArray13, nArray14);
        if (Nat256.eq(nArray, nArray14)) {
            return new Curve25519FieldElement(nArray13);
        }
        return null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Curve25519FieldElement)) {
            return false;
        }
        Curve25519FieldElement curve25519FieldElement = (Curve25519FieldElement)object;
        return Nat256.eq(this.x, curve25519FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 8);
    }
}

