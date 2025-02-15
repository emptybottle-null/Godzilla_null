/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP160R1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP160R1Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat160;
import org.bouncycastle.util.Arrays;

public class SecP160R1FieldElement
extends ECFieldElement {
    public static final BigInteger Q = SecP160R1Curve.q;
    protected int[] x;

    public SecP160R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP160R1FieldElement");
        }
        this.x = SecP160R1Field.fromBigInteger(bigInteger);
    }

    public SecP160R1FieldElement() {
        this.x = Nat160.create();
    }

    protected SecP160R1FieldElement(int[] nArray) {
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
        return "SecP160R1Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat160.create();
        SecP160R1Field.add(this.x, ((SecP160R1FieldElement)eCFieldElement).x, nArray);
        return new SecP160R1FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat160.create();
        SecP160R1Field.addOne(this.x, nArray);
        return new SecP160R1FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat160.create();
        SecP160R1Field.subtract(this.x, ((SecP160R1FieldElement)eCFieldElement).x, nArray);
        return new SecP160R1FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat160.create();
        SecP160R1Field.multiply(this.x, ((SecP160R1FieldElement)eCFieldElement).x, nArray);
        return new SecP160R1FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat160.create();
        Mod.invert(SecP160R1Field.P, ((SecP160R1FieldElement)eCFieldElement).x, nArray);
        SecP160R1Field.multiply(nArray, this.x, nArray);
        return new SecP160R1FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat160.create();
        SecP160R1Field.negate(this.x, nArray);
        return new SecP160R1FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat160.create();
        SecP160R1Field.square(this.x, nArray);
        return new SecP160R1FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat160.create();
        Mod.invert(SecP160R1Field.P, this.x, nArray);
        return new SecP160R1FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat160.isZero(nArray) || Nat160.isOne(nArray)) {
            return this;
        }
        int[] nArray2 = Nat160.create();
        SecP160R1Field.square(nArray, nArray2);
        SecP160R1Field.multiply(nArray2, nArray, nArray2);
        int[] nArray3 = Nat160.create();
        SecP160R1Field.squareN(nArray2, 2, nArray3);
        SecP160R1Field.multiply(nArray3, nArray2, nArray3);
        int[] nArray4 = nArray2;
        SecP160R1Field.squareN(nArray3, 4, nArray4);
        SecP160R1Field.multiply(nArray4, nArray3, nArray4);
        int[] nArray5 = nArray3;
        SecP160R1Field.squareN(nArray4, 8, nArray5);
        SecP160R1Field.multiply(nArray5, nArray4, nArray5);
        int[] nArray6 = nArray4;
        SecP160R1Field.squareN(nArray5, 16, nArray6);
        SecP160R1Field.multiply(nArray6, nArray5, nArray6);
        int[] nArray7 = nArray5;
        SecP160R1Field.squareN(nArray6, 32, nArray7);
        SecP160R1Field.multiply(nArray7, nArray6, nArray7);
        int[] nArray8 = nArray6;
        SecP160R1Field.squareN(nArray7, 64, nArray8);
        SecP160R1Field.multiply(nArray8, nArray7, nArray8);
        int[] nArray9 = nArray7;
        SecP160R1Field.square(nArray8, nArray9);
        SecP160R1Field.multiply(nArray9, nArray, nArray9);
        int[] nArray10 = nArray9;
        SecP160R1Field.squareN(nArray10, 29, nArray10);
        int[] nArray11 = nArray8;
        SecP160R1Field.square(nArray10, nArray11);
        return Nat160.eq(nArray, nArray11) ? new SecP160R1FieldElement(nArray10) : null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecP160R1FieldElement)) {
            return false;
        }
        SecP160R1FieldElement secP160R1FieldElement = (SecP160R1FieldElement)object;
        return Nat160.eq(this.x, secP160R1FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 5);
    }
}

