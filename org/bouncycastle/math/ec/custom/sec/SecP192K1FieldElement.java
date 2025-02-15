/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP192K1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP192K1Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.Arrays;

public class SecP192K1FieldElement
extends ECFieldElement {
    public static final BigInteger Q = SecP192K1Curve.q;
    protected int[] x;

    public SecP192K1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192K1FieldElement");
        }
        this.x = SecP192K1Field.fromBigInteger(bigInteger);
    }

    public SecP192K1FieldElement() {
        this.x = Nat192.create();
    }

    protected SecP192K1FieldElement(int[] nArray) {
        this.x = nArray;
    }

    public boolean isZero() {
        return Nat192.isZero(this.x);
    }

    public boolean isOne() {
        return Nat192.isOne(this.x);
    }

    public boolean testBitZero() {
        return Nat192.getBit(this.x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat192.toBigInteger(this.x);
    }

    public String getFieldName() {
        return "SecP192K1Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat192.create();
        SecP192K1Field.add(this.x, ((SecP192K1FieldElement)eCFieldElement).x, nArray);
        return new SecP192K1FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat192.create();
        SecP192K1Field.addOne(this.x, nArray);
        return new SecP192K1FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat192.create();
        SecP192K1Field.subtract(this.x, ((SecP192K1FieldElement)eCFieldElement).x, nArray);
        return new SecP192K1FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat192.create();
        SecP192K1Field.multiply(this.x, ((SecP192K1FieldElement)eCFieldElement).x, nArray);
        return new SecP192K1FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat192.create();
        Mod.invert(SecP192K1Field.P, ((SecP192K1FieldElement)eCFieldElement).x, nArray);
        SecP192K1Field.multiply(nArray, this.x, nArray);
        return new SecP192K1FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat192.create();
        SecP192K1Field.negate(this.x, nArray);
        return new SecP192K1FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat192.create();
        SecP192K1Field.square(this.x, nArray);
        return new SecP192K1FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat192.create();
        Mod.invert(SecP192K1Field.P, this.x, nArray);
        return new SecP192K1FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat192.isZero(nArray) || Nat192.isOne(nArray)) {
            return this;
        }
        int[] nArray2 = Nat192.create();
        SecP192K1Field.square(nArray, nArray2);
        SecP192K1Field.multiply(nArray2, nArray, nArray2);
        int[] nArray3 = Nat192.create();
        SecP192K1Field.square(nArray2, nArray3);
        SecP192K1Field.multiply(nArray3, nArray, nArray3);
        int[] nArray4 = Nat192.create();
        SecP192K1Field.squareN(nArray3, 3, nArray4);
        SecP192K1Field.multiply(nArray4, nArray3, nArray4);
        int[] nArray5 = nArray4;
        SecP192K1Field.squareN(nArray4, 2, nArray5);
        SecP192K1Field.multiply(nArray5, nArray2, nArray5);
        int[] nArray6 = nArray2;
        SecP192K1Field.squareN(nArray5, 8, nArray6);
        SecP192K1Field.multiply(nArray6, nArray5, nArray6);
        int[] nArray7 = nArray5;
        SecP192K1Field.squareN(nArray6, 3, nArray7);
        SecP192K1Field.multiply(nArray7, nArray3, nArray7);
        int[] nArray8 = Nat192.create();
        SecP192K1Field.squareN(nArray7, 16, nArray8);
        SecP192K1Field.multiply(nArray8, nArray6, nArray8);
        int[] nArray9 = nArray6;
        SecP192K1Field.squareN(nArray8, 35, nArray9);
        SecP192K1Field.multiply(nArray9, nArray8, nArray9);
        int[] nArray10 = nArray8;
        SecP192K1Field.squareN(nArray9, 70, nArray10);
        SecP192K1Field.multiply(nArray10, nArray9, nArray10);
        int[] nArray11 = nArray9;
        SecP192K1Field.squareN(nArray10, 19, nArray11);
        SecP192K1Field.multiply(nArray11, nArray7, nArray11);
        int[] nArray12 = nArray11;
        SecP192K1Field.squareN(nArray12, 20, nArray12);
        SecP192K1Field.multiply(nArray12, nArray7, nArray12);
        SecP192K1Field.squareN(nArray12, 4, nArray12);
        SecP192K1Field.multiply(nArray12, nArray3, nArray12);
        SecP192K1Field.squareN(nArray12, 6, nArray12);
        SecP192K1Field.multiply(nArray12, nArray3, nArray12);
        SecP192K1Field.square(nArray12, nArray12);
        int[] nArray13 = nArray3;
        SecP192K1Field.square(nArray12, nArray13);
        return Nat192.eq(nArray, nArray13) ? new SecP192K1FieldElement(nArray12) : null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecP192K1FieldElement)) {
            return false;
        }
        SecP192K1FieldElement secP192K1FieldElement = (SecP192K1FieldElement)object;
        return Nat192.eq(this.x, secP192K1FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 6);
    }
}

