/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP224K1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP224K1Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat224;
import org.bouncycastle.util.Arrays;

public class SecP224K1FieldElement
extends ECFieldElement {
    public static final BigInteger Q = SecP224K1Curve.q;
    private static final int[] PRECOMP_POW2 = new int[]{868209154, -587542221, 579297866, -1014948952, -1470801668, 514782679, -1897982644};
    protected int[] x;

    public SecP224K1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224K1FieldElement");
        }
        this.x = SecP224K1Field.fromBigInteger(bigInteger);
    }

    public SecP224K1FieldElement() {
        this.x = Nat224.create();
    }

    protected SecP224K1FieldElement(int[] nArray) {
        this.x = nArray;
    }

    public boolean isZero() {
        return Nat224.isZero(this.x);
    }

    public boolean isOne() {
        return Nat224.isOne(this.x);
    }

    public boolean testBitZero() {
        return Nat224.getBit(this.x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat224.toBigInteger(this.x);
    }

    public String getFieldName() {
        return "SecP224K1Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat224.create();
        SecP224K1Field.add(this.x, ((SecP224K1FieldElement)eCFieldElement).x, nArray);
        return new SecP224K1FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat224.create();
        SecP224K1Field.addOne(this.x, nArray);
        return new SecP224K1FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat224.create();
        SecP224K1Field.subtract(this.x, ((SecP224K1FieldElement)eCFieldElement).x, nArray);
        return new SecP224K1FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat224.create();
        SecP224K1Field.multiply(this.x, ((SecP224K1FieldElement)eCFieldElement).x, nArray);
        return new SecP224K1FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat224.create();
        Mod.invert(SecP224K1Field.P, ((SecP224K1FieldElement)eCFieldElement).x, nArray);
        SecP224K1Field.multiply(nArray, this.x, nArray);
        return new SecP224K1FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat224.create();
        SecP224K1Field.negate(this.x, nArray);
        return new SecP224K1FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat224.create();
        SecP224K1Field.square(this.x, nArray);
        return new SecP224K1FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat224.create();
        Mod.invert(SecP224K1Field.P, this.x, nArray);
        return new SecP224K1FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat224.isZero(nArray) || Nat224.isOne(nArray)) {
            return this;
        }
        int[] nArray2 = Nat224.create();
        SecP224K1Field.square(nArray, nArray2);
        SecP224K1Field.multiply(nArray2, nArray, nArray2);
        int[] nArray3 = nArray2;
        SecP224K1Field.square(nArray2, nArray3);
        SecP224K1Field.multiply(nArray3, nArray, nArray3);
        int[] nArray4 = Nat224.create();
        SecP224K1Field.square(nArray3, nArray4);
        SecP224K1Field.multiply(nArray4, nArray, nArray4);
        int[] nArray5 = Nat224.create();
        SecP224K1Field.squareN(nArray4, 4, nArray5);
        SecP224K1Field.multiply(nArray5, nArray4, nArray5);
        int[] nArray6 = Nat224.create();
        SecP224K1Field.squareN(nArray5, 3, nArray6);
        SecP224K1Field.multiply(nArray6, nArray3, nArray6);
        int[] nArray7 = nArray6;
        SecP224K1Field.squareN(nArray6, 8, nArray7);
        SecP224K1Field.multiply(nArray7, nArray5, nArray7);
        int[] nArray8 = nArray5;
        SecP224K1Field.squareN(nArray7, 4, nArray8);
        SecP224K1Field.multiply(nArray8, nArray4, nArray8);
        int[] nArray9 = nArray4;
        SecP224K1Field.squareN(nArray8, 19, nArray9);
        SecP224K1Field.multiply(nArray9, nArray7, nArray9);
        int[] nArray10 = Nat224.create();
        SecP224K1Field.squareN(nArray9, 42, nArray10);
        SecP224K1Field.multiply(nArray10, nArray9, nArray10);
        int[] nArray11 = nArray9;
        SecP224K1Field.squareN(nArray10, 23, nArray11);
        SecP224K1Field.multiply(nArray11, nArray8, nArray11);
        int[] nArray12 = nArray8;
        SecP224K1Field.squareN(nArray11, 84, nArray12);
        SecP224K1Field.multiply(nArray12, nArray10, nArray12);
        int[] nArray13 = nArray12;
        SecP224K1Field.squareN(nArray13, 20, nArray13);
        SecP224K1Field.multiply(nArray13, nArray7, nArray13);
        SecP224K1Field.squareN(nArray13, 3, nArray13);
        SecP224K1Field.multiply(nArray13, nArray, nArray13);
        SecP224K1Field.squareN(nArray13, 2, nArray13);
        SecP224K1Field.multiply(nArray13, nArray, nArray13);
        SecP224K1Field.squareN(nArray13, 4, nArray13);
        SecP224K1Field.multiply(nArray13, nArray3, nArray13);
        SecP224K1Field.square(nArray13, nArray13);
        int[] nArray14 = nArray10;
        SecP224K1Field.square(nArray13, nArray14);
        if (Nat224.eq(nArray, nArray14)) {
            return new SecP224K1FieldElement(nArray13);
        }
        SecP224K1Field.multiply(nArray13, PRECOMP_POW2, nArray13);
        SecP224K1Field.square(nArray13, nArray14);
        if (Nat224.eq(nArray, nArray14)) {
            return new SecP224K1FieldElement(nArray13);
        }
        return null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecP224K1FieldElement)) {
            return false;
        }
        SecP224K1FieldElement secP224K1FieldElement = (SecP224K1FieldElement)object;
        return Nat224.eq(this.x, secP224K1FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 7);
    }
}

