/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP192R1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP192R1Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.Arrays;

public class SecP192R1FieldElement
extends ECFieldElement {
    public static final BigInteger Q = SecP192R1Curve.q;
    protected int[] x;

    public SecP192R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192R1FieldElement");
        }
        this.x = SecP192R1Field.fromBigInteger(bigInteger);
    }

    public SecP192R1FieldElement() {
        this.x = Nat192.create();
    }

    protected SecP192R1FieldElement(int[] nArray) {
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
        return "SecP192R1Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat192.create();
        SecP192R1Field.add(this.x, ((SecP192R1FieldElement)eCFieldElement).x, nArray);
        return new SecP192R1FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat192.create();
        SecP192R1Field.addOne(this.x, nArray);
        return new SecP192R1FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat192.create();
        SecP192R1Field.subtract(this.x, ((SecP192R1FieldElement)eCFieldElement).x, nArray);
        return new SecP192R1FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat192.create();
        SecP192R1Field.multiply(this.x, ((SecP192R1FieldElement)eCFieldElement).x, nArray);
        return new SecP192R1FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat192.create();
        Mod.invert(SecP192R1Field.P, ((SecP192R1FieldElement)eCFieldElement).x, nArray);
        SecP192R1Field.multiply(nArray, this.x, nArray);
        return new SecP192R1FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat192.create();
        SecP192R1Field.negate(this.x, nArray);
        return new SecP192R1FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat192.create();
        SecP192R1Field.square(this.x, nArray);
        return new SecP192R1FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat192.create();
        Mod.invert(SecP192R1Field.P, this.x, nArray);
        return new SecP192R1FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat192.isZero(nArray) || Nat192.isOne(nArray)) {
            return this;
        }
        int[] nArray2 = Nat192.create();
        int[] nArray3 = Nat192.create();
        SecP192R1Field.square(nArray, nArray2);
        SecP192R1Field.multiply(nArray2, nArray, nArray2);
        SecP192R1Field.squareN(nArray2, 2, nArray3);
        SecP192R1Field.multiply(nArray3, nArray2, nArray3);
        SecP192R1Field.squareN(nArray3, 4, nArray2);
        SecP192R1Field.multiply(nArray2, nArray3, nArray2);
        SecP192R1Field.squareN(nArray2, 8, nArray3);
        SecP192R1Field.multiply(nArray3, nArray2, nArray3);
        SecP192R1Field.squareN(nArray3, 16, nArray2);
        SecP192R1Field.multiply(nArray2, nArray3, nArray2);
        SecP192R1Field.squareN(nArray2, 32, nArray3);
        SecP192R1Field.multiply(nArray3, nArray2, nArray3);
        SecP192R1Field.squareN(nArray3, 64, nArray2);
        SecP192R1Field.multiply(nArray2, nArray3, nArray2);
        SecP192R1Field.squareN(nArray2, 62, nArray2);
        SecP192R1Field.square(nArray2, nArray3);
        return Nat192.eq(nArray, nArray3) ? new SecP192R1FieldElement(nArray2) : null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecP192R1FieldElement)) {
            return false;
        }
        SecP192R1FieldElement secP192R1FieldElement = (SecP192R1FieldElement)object;
        return Nat192.eq(this.x, secP192R1FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 6);
    }
}

