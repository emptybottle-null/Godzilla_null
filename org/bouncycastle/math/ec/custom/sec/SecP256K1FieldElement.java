/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP256K1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP256K1Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

public class SecP256K1FieldElement
extends ECFieldElement {
    public static final BigInteger Q = SecP256K1Curve.q;
    protected int[] x;

    public SecP256K1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP256K1FieldElement");
        }
        this.x = SecP256K1Field.fromBigInteger(bigInteger);
    }

    public SecP256K1FieldElement() {
        this.x = Nat256.create();
    }

    protected SecP256K1FieldElement(int[] nArray) {
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
        return "SecP256K1Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        SecP256K1Field.add(this.x, ((SecP256K1FieldElement)eCFieldElement).x, nArray);
        return new SecP256K1FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat256.create();
        SecP256K1Field.addOne(this.x, nArray);
        return new SecP256K1FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        SecP256K1Field.subtract(this.x, ((SecP256K1FieldElement)eCFieldElement).x, nArray);
        return new SecP256K1FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        SecP256K1Field.multiply(this.x, ((SecP256K1FieldElement)eCFieldElement).x, nArray);
        return new SecP256K1FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        Mod.invert(SecP256K1Field.P, ((SecP256K1FieldElement)eCFieldElement).x, nArray);
        SecP256K1Field.multiply(nArray, this.x, nArray);
        return new SecP256K1FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat256.create();
        SecP256K1Field.negate(this.x, nArray);
        return new SecP256K1FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat256.create();
        SecP256K1Field.square(this.x, nArray);
        return new SecP256K1FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat256.create();
        Mod.invert(SecP256K1Field.P, this.x, nArray);
        return new SecP256K1FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat256.isZero(nArray) || Nat256.isOne(nArray)) {
            return this;
        }
        int[] nArray2 = Nat256.create();
        SecP256K1Field.square(nArray, nArray2);
        SecP256K1Field.multiply(nArray2, nArray, nArray2);
        int[] nArray3 = Nat256.create();
        SecP256K1Field.square(nArray2, nArray3);
        SecP256K1Field.multiply(nArray3, nArray, nArray3);
        int[] nArray4 = Nat256.create();
        SecP256K1Field.squareN(nArray3, 3, nArray4);
        SecP256K1Field.multiply(nArray4, nArray3, nArray4);
        int[] nArray5 = nArray4;
        SecP256K1Field.squareN(nArray4, 3, nArray5);
        SecP256K1Field.multiply(nArray5, nArray3, nArray5);
        int[] nArray6 = nArray5;
        SecP256K1Field.squareN(nArray5, 2, nArray6);
        SecP256K1Field.multiply(nArray6, nArray2, nArray6);
        int[] nArray7 = Nat256.create();
        SecP256K1Field.squareN(nArray6, 11, nArray7);
        SecP256K1Field.multiply(nArray7, nArray6, nArray7);
        int[] nArray8 = nArray6;
        SecP256K1Field.squareN(nArray7, 22, nArray8);
        SecP256K1Field.multiply(nArray8, nArray7, nArray8);
        int[] nArray9 = Nat256.create();
        SecP256K1Field.squareN(nArray8, 44, nArray9);
        SecP256K1Field.multiply(nArray9, nArray8, nArray9);
        int[] nArray10 = Nat256.create();
        SecP256K1Field.squareN(nArray9, 88, nArray10);
        SecP256K1Field.multiply(nArray10, nArray9, nArray10);
        int[] nArray11 = nArray9;
        SecP256K1Field.squareN(nArray10, 44, nArray11);
        SecP256K1Field.multiply(nArray11, nArray8, nArray11);
        int[] nArray12 = nArray8;
        SecP256K1Field.squareN(nArray11, 3, nArray12);
        SecP256K1Field.multiply(nArray12, nArray3, nArray12);
        int[] nArray13 = nArray12;
        SecP256K1Field.squareN(nArray13, 23, nArray13);
        SecP256K1Field.multiply(nArray13, nArray7, nArray13);
        SecP256K1Field.squareN(nArray13, 6, nArray13);
        SecP256K1Field.multiply(nArray13, nArray2, nArray13);
        SecP256K1Field.squareN(nArray13, 2, nArray13);
        int[] nArray14 = nArray2;
        SecP256K1Field.square(nArray13, nArray14);
        return Nat256.eq(nArray, nArray14) ? new SecP256K1FieldElement(nArray13) : null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecP256K1FieldElement)) {
            return false;
        }
        SecP256K1FieldElement secP256K1FieldElement = (SecP256K1FieldElement)object;
        return Nat256.eq(this.x, secP256K1FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 8);
    }
}

