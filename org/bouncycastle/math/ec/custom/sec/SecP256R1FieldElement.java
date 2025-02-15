/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP256R1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP256R1Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

public class SecP256R1FieldElement
extends ECFieldElement {
    public static final BigInteger Q = SecP256R1Curve.q;
    protected int[] x;

    public SecP256R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP256R1FieldElement");
        }
        this.x = SecP256R1Field.fromBigInteger(bigInteger);
    }

    public SecP256R1FieldElement() {
        this.x = Nat256.create();
    }

    protected SecP256R1FieldElement(int[] nArray) {
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
        return "SecP256R1Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        SecP256R1Field.add(this.x, ((SecP256R1FieldElement)eCFieldElement).x, nArray);
        return new SecP256R1FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat256.create();
        SecP256R1Field.addOne(this.x, nArray);
        return new SecP256R1FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        SecP256R1Field.subtract(this.x, ((SecP256R1FieldElement)eCFieldElement).x, nArray);
        return new SecP256R1FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        SecP256R1Field.multiply(this.x, ((SecP256R1FieldElement)eCFieldElement).x, nArray);
        return new SecP256R1FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        Mod.invert(SecP256R1Field.P, ((SecP256R1FieldElement)eCFieldElement).x, nArray);
        SecP256R1Field.multiply(nArray, this.x, nArray);
        return new SecP256R1FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat256.create();
        SecP256R1Field.negate(this.x, nArray);
        return new SecP256R1FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat256.create();
        SecP256R1Field.square(this.x, nArray);
        return new SecP256R1FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat256.create();
        Mod.invert(SecP256R1Field.P, this.x, nArray);
        return new SecP256R1FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat256.isZero(nArray) || Nat256.isOne(nArray)) {
            return this;
        }
        int[] nArray2 = Nat256.create();
        int[] nArray3 = Nat256.create();
        SecP256R1Field.square(nArray, nArray2);
        SecP256R1Field.multiply(nArray2, nArray, nArray2);
        SecP256R1Field.squareN(nArray2, 2, nArray3);
        SecP256R1Field.multiply(nArray3, nArray2, nArray3);
        SecP256R1Field.squareN(nArray3, 4, nArray2);
        SecP256R1Field.multiply(nArray2, nArray3, nArray2);
        SecP256R1Field.squareN(nArray2, 8, nArray3);
        SecP256R1Field.multiply(nArray3, nArray2, nArray3);
        SecP256R1Field.squareN(nArray3, 16, nArray2);
        SecP256R1Field.multiply(nArray2, nArray3, nArray2);
        SecP256R1Field.squareN(nArray2, 32, nArray2);
        SecP256R1Field.multiply(nArray2, nArray, nArray2);
        SecP256R1Field.squareN(nArray2, 96, nArray2);
        SecP256R1Field.multiply(nArray2, nArray, nArray2);
        SecP256R1Field.squareN(nArray2, 94, nArray2);
        SecP256R1Field.square(nArray2, nArray3);
        return Nat256.eq(nArray, nArray3) ? new SecP256R1FieldElement(nArray2) : null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecP256R1FieldElement)) {
            return false;
        }
        SecP256R1FieldElement secP256R1FieldElement = (SecP256R1FieldElement)object;
        return Nat256.eq(this.x, secP256R1FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 8);
    }
}

