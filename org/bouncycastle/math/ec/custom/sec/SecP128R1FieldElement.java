/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP128R1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP128R1Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat128;
import org.bouncycastle.util.Arrays;

public class SecP128R1FieldElement
extends ECFieldElement {
    public static final BigInteger Q = SecP128R1Curve.q;
    protected int[] x;

    public SecP128R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP128R1FieldElement");
        }
        this.x = SecP128R1Field.fromBigInteger(bigInteger);
    }

    public SecP128R1FieldElement() {
        this.x = Nat128.create();
    }

    protected SecP128R1FieldElement(int[] nArray) {
        this.x = nArray;
    }

    public boolean isZero() {
        return Nat128.isZero(this.x);
    }

    public boolean isOne() {
        return Nat128.isOne(this.x);
    }

    public boolean testBitZero() {
        return Nat128.getBit(this.x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat128.toBigInteger(this.x);
    }

    public String getFieldName() {
        return "SecP128R1Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat128.create();
        SecP128R1Field.add(this.x, ((SecP128R1FieldElement)eCFieldElement).x, nArray);
        return new SecP128R1FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat128.create();
        SecP128R1Field.addOne(this.x, nArray);
        return new SecP128R1FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat128.create();
        SecP128R1Field.subtract(this.x, ((SecP128R1FieldElement)eCFieldElement).x, nArray);
        return new SecP128R1FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat128.create();
        SecP128R1Field.multiply(this.x, ((SecP128R1FieldElement)eCFieldElement).x, nArray);
        return new SecP128R1FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat128.create();
        Mod.invert(SecP128R1Field.P, ((SecP128R1FieldElement)eCFieldElement).x, nArray);
        SecP128R1Field.multiply(nArray, this.x, nArray);
        return new SecP128R1FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat128.create();
        SecP128R1Field.negate(this.x, nArray);
        return new SecP128R1FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat128.create();
        SecP128R1Field.square(this.x, nArray);
        return new SecP128R1FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat128.create();
        Mod.invert(SecP128R1Field.P, this.x, nArray);
        return new SecP128R1FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat128.isZero(nArray) || Nat128.isOne(nArray)) {
            return this;
        }
        int[] nArray2 = Nat128.create();
        SecP128R1Field.square(nArray, nArray2);
        SecP128R1Field.multiply(nArray2, nArray, nArray2);
        int[] nArray3 = Nat128.create();
        SecP128R1Field.squareN(nArray2, 2, nArray3);
        SecP128R1Field.multiply(nArray3, nArray2, nArray3);
        int[] nArray4 = Nat128.create();
        SecP128R1Field.squareN(nArray3, 4, nArray4);
        SecP128R1Field.multiply(nArray4, nArray3, nArray4);
        int[] nArray5 = nArray3;
        SecP128R1Field.squareN(nArray4, 2, nArray5);
        SecP128R1Field.multiply(nArray5, nArray2, nArray5);
        int[] nArray6 = nArray2;
        SecP128R1Field.squareN(nArray5, 10, nArray6);
        SecP128R1Field.multiply(nArray6, nArray5, nArray6);
        int[] nArray7 = nArray4;
        SecP128R1Field.squareN(nArray6, 10, nArray7);
        SecP128R1Field.multiply(nArray7, nArray5, nArray7);
        int[] nArray8 = nArray5;
        SecP128R1Field.square(nArray7, nArray8);
        SecP128R1Field.multiply(nArray8, nArray, nArray8);
        int[] nArray9 = nArray8;
        SecP128R1Field.squareN(nArray9, 95, nArray9);
        int[] nArray10 = nArray7;
        SecP128R1Field.square(nArray9, nArray10);
        return Nat128.eq(nArray, nArray10) ? new SecP128R1FieldElement(nArray9) : null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecP128R1FieldElement)) {
            return false;
        }
        SecP128R1FieldElement secP128R1FieldElement = (SecP128R1FieldElement)object;
        return Nat128.eq(this.x, secP128R1FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 4);
    }
}

