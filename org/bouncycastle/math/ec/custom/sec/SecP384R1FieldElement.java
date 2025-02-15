/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP384R1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP384R1Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;

public class SecP384R1FieldElement
extends ECFieldElement {
    public static final BigInteger Q = SecP384R1Curve.q;
    protected int[] x;

    public SecP384R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP384R1FieldElement");
        }
        this.x = SecP384R1Field.fromBigInteger(bigInteger);
    }

    public SecP384R1FieldElement() {
        this.x = Nat.create(12);
    }

    protected SecP384R1FieldElement(int[] nArray) {
        this.x = nArray;
    }

    public boolean isZero() {
        return Nat.isZero(12, this.x);
    }

    public boolean isOne() {
        return Nat.isOne(12, this.x);
    }

    public boolean testBitZero() {
        return Nat.getBit(this.x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat.toBigInteger(12, this.x);
    }

    public String getFieldName() {
        return "SecP384R1Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat.create(12);
        SecP384R1Field.add(this.x, ((SecP384R1FieldElement)eCFieldElement).x, nArray);
        return new SecP384R1FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat.create(12);
        SecP384R1Field.addOne(this.x, nArray);
        return new SecP384R1FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat.create(12);
        SecP384R1Field.subtract(this.x, ((SecP384R1FieldElement)eCFieldElement).x, nArray);
        return new SecP384R1FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat.create(12);
        SecP384R1Field.multiply(this.x, ((SecP384R1FieldElement)eCFieldElement).x, nArray);
        return new SecP384R1FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat.create(12);
        Mod.invert(SecP384R1Field.P, ((SecP384R1FieldElement)eCFieldElement).x, nArray);
        SecP384R1Field.multiply(nArray, this.x, nArray);
        return new SecP384R1FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat.create(12);
        SecP384R1Field.negate(this.x, nArray);
        return new SecP384R1FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat.create(12);
        SecP384R1Field.square(this.x, nArray);
        return new SecP384R1FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat.create(12);
        Mod.invert(SecP384R1Field.P, this.x, nArray);
        return new SecP384R1FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat.isZero(12, nArray) || Nat.isOne(12, nArray)) {
            return this;
        }
        int[] nArray2 = Nat.create(12);
        int[] nArray3 = Nat.create(12);
        int[] nArray4 = Nat.create(12);
        int[] nArray5 = Nat.create(12);
        SecP384R1Field.square(nArray, nArray2);
        SecP384R1Field.multiply(nArray2, nArray, nArray2);
        SecP384R1Field.squareN(nArray2, 2, nArray3);
        SecP384R1Field.multiply(nArray3, nArray2, nArray3);
        SecP384R1Field.square(nArray3, nArray3);
        SecP384R1Field.multiply(nArray3, nArray, nArray3);
        SecP384R1Field.squareN(nArray3, 5, nArray4);
        SecP384R1Field.multiply(nArray4, nArray3, nArray4);
        SecP384R1Field.squareN(nArray4, 5, nArray5);
        SecP384R1Field.multiply(nArray5, nArray3, nArray5);
        SecP384R1Field.squareN(nArray5, 15, nArray3);
        SecP384R1Field.multiply(nArray3, nArray5, nArray3);
        SecP384R1Field.squareN(nArray3, 2, nArray4);
        SecP384R1Field.multiply(nArray2, nArray4, nArray2);
        SecP384R1Field.squareN(nArray4, 28, nArray4);
        SecP384R1Field.multiply(nArray3, nArray4, nArray3);
        SecP384R1Field.squareN(nArray3, 60, nArray4);
        SecP384R1Field.multiply(nArray4, nArray3, nArray4);
        int[] nArray6 = nArray3;
        SecP384R1Field.squareN(nArray4, 120, nArray6);
        SecP384R1Field.multiply(nArray6, nArray4, nArray6);
        SecP384R1Field.squareN(nArray6, 15, nArray6);
        SecP384R1Field.multiply(nArray6, nArray5, nArray6);
        SecP384R1Field.squareN(nArray6, 33, nArray6);
        SecP384R1Field.multiply(nArray6, nArray2, nArray6);
        SecP384R1Field.squareN(nArray6, 64, nArray6);
        SecP384R1Field.multiply(nArray6, nArray, nArray6);
        SecP384R1Field.squareN(nArray6, 30, nArray2);
        SecP384R1Field.square(nArray2, nArray3);
        return Nat.eq(12, nArray, nArray3) ? new SecP384R1FieldElement(nArray2) : null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecP384R1FieldElement)) {
            return false;
        }
        SecP384R1FieldElement secP384R1FieldElement = (SecP384R1FieldElement)object;
        return Nat.eq(12, this.x, secP384R1FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 12);
    }
}

