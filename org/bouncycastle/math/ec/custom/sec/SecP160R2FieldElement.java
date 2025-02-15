/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP160R2Curve;
import org.bouncycastle.math.ec.custom.sec.SecP160R2Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat160;
import org.bouncycastle.util.Arrays;

public class SecP160R2FieldElement
extends ECFieldElement {
    public static final BigInteger Q = SecP160R2Curve.q;
    protected int[] x;

    public SecP160R2FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP160R2FieldElement");
        }
        this.x = SecP160R2Field.fromBigInteger(bigInteger);
    }

    public SecP160R2FieldElement() {
        this.x = Nat160.create();
    }

    protected SecP160R2FieldElement(int[] nArray) {
        this.x = nArray;
    }

    public boolean isZero() {
        return Nat160.isZero(this.x);
    }

    public boolean isOne() {
        return Nat160.isOne(this.x);
    }

    public boolean testBitZero() {
        return Nat160.getBit(this.x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat160.toBigInteger(this.x);
    }

    public String getFieldName() {
        return "SecP160R2Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat160.create();
        SecP160R2Field.add(this.x, ((SecP160R2FieldElement)eCFieldElement).x, nArray);
        return new SecP160R2FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat160.create();
        SecP160R2Field.addOne(this.x, nArray);
        return new SecP160R2FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat160.create();
        SecP160R2Field.subtract(this.x, ((SecP160R2FieldElement)eCFieldElement).x, nArray);
        return new SecP160R2FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat160.create();
        SecP160R2Field.multiply(this.x, ((SecP160R2FieldElement)eCFieldElement).x, nArray);
        return new SecP160R2FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat160.create();
        Mod.invert(SecP160R2Field.P, ((SecP160R2FieldElement)eCFieldElement).x, nArray);
        SecP160R2Field.multiply(nArray, this.x, nArray);
        return new SecP160R2FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat160.create();
        SecP160R2Field.negate(this.x, nArray);
        return new SecP160R2FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat160.create();
        SecP160R2Field.square(this.x, nArray);
        return new SecP160R2FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat160.create();
        Mod.invert(SecP160R2Field.P, this.x, nArray);
        return new SecP160R2FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat160.isZero(nArray) || Nat160.isOne(nArray)) {
            return this;
        }
        int[] nArray2 = Nat160.create();
        SecP160R2Field.square(nArray, nArray2);
        SecP160R2Field.multiply(nArray2, nArray, nArray2);
        int[] nArray3 = Nat160.create();
        SecP160R2Field.square(nArray2, nArray3);
        SecP160R2Field.multiply(nArray3, nArray, nArray3);
        int[] nArray4 = Nat160.create();
        SecP160R2Field.square(nArray3, nArray4);
        SecP160R2Field.multiply(nArray4, nArray, nArray4);
        int[] nArray5 = Nat160.create();
        SecP160R2Field.squareN(nArray4, 3, nArray5);
        SecP160R2Field.multiply(nArray5, nArray3, nArray5);
        int[] nArray6 = nArray4;
        SecP160R2Field.squareN(nArray5, 7, nArray6);
        SecP160R2Field.multiply(nArray6, nArray5, nArray6);
        int[] nArray7 = nArray5;
        SecP160R2Field.squareN(nArray6, 3, nArray7);
        SecP160R2Field.multiply(nArray7, nArray3, nArray7);
        int[] nArray8 = Nat160.create();
        SecP160R2Field.squareN(nArray7, 14, nArray8);
        SecP160R2Field.multiply(nArray8, nArray6, nArray8);
        int[] nArray9 = nArray6;
        SecP160R2Field.squareN(nArray8, 31, nArray9);
        SecP160R2Field.multiply(nArray9, nArray8, nArray9);
        int[] nArray10 = nArray8;
        SecP160R2Field.squareN(nArray9, 62, nArray10);
        SecP160R2Field.multiply(nArray10, nArray9, nArray10);
        int[] nArray11 = nArray9;
        SecP160R2Field.squareN(nArray10, 3, nArray11);
        SecP160R2Field.multiply(nArray11, nArray3, nArray11);
        int[] nArray12 = nArray11;
        SecP160R2Field.squareN(nArray12, 18, nArray12);
        SecP160R2Field.multiply(nArray12, nArray7, nArray12);
        SecP160R2Field.squareN(nArray12, 2, nArray12);
        SecP160R2Field.multiply(nArray12, nArray, nArray12);
        SecP160R2Field.squareN(nArray12, 3, nArray12);
        SecP160R2Field.multiply(nArray12, nArray2, nArray12);
        SecP160R2Field.squareN(nArray12, 6, nArray12);
        SecP160R2Field.multiply(nArray12, nArray3, nArray12);
        SecP160R2Field.squareN(nArray12, 2, nArray12);
        SecP160R2Field.multiply(nArray12, nArray, nArray12);
        int[] nArray13 = nArray2;
        SecP160R2Field.square(nArray12, nArray13);
        return Nat160.eq(nArray, nArray13) ? new SecP160R2FieldElement(nArray12) : null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecP160R2FieldElement)) {
            return false;
        }
        SecP160R2FieldElement secP160R2FieldElement = (SecP160R2FieldElement)object;
        return Nat160.eq(this.x, secP160R2FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 5);
    }
}

