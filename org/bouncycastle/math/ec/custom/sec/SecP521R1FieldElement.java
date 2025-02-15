/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP521R1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP521R1Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;

public class SecP521R1FieldElement
extends ECFieldElement {
    public static final BigInteger Q = SecP521R1Curve.q;
    protected int[] x;

    public SecP521R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP521R1FieldElement");
        }
        this.x = SecP521R1Field.fromBigInteger(bigInteger);
    }

    public SecP521R1FieldElement() {
        this.x = Nat.create(17);
    }

    protected SecP521R1FieldElement(int[] nArray) {
        this.x = nArray;
    }

    public boolean isZero() {
        return Nat.isZero(17, this.x);
    }

    public boolean isOne() {
        return Nat.isOne(17, this.x);
    }

    public boolean testBitZero() {
        return Nat.getBit(this.x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat.toBigInteger(17, this.x);
    }

    public String getFieldName() {
        return "SecP521R1Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat.create(17);
        SecP521R1Field.add(this.x, ((SecP521R1FieldElement)eCFieldElement).x, nArray);
        return new SecP521R1FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat.create(17);
        SecP521R1Field.addOne(this.x, nArray);
        return new SecP521R1FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat.create(17);
        SecP521R1Field.subtract(this.x, ((SecP521R1FieldElement)eCFieldElement).x, nArray);
        return new SecP521R1FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat.create(17);
        SecP521R1Field.multiply(this.x, ((SecP521R1FieldElement)eCFieldElement).x, nArray);
        return new SecP521R1FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat.create(17);
        Mod.invert(SecP521R1Field.P, ((SecP521R1FieldElement)eCFieldElement).x, nArray);
        SecP521R1Field.multiply(nArray, this.x, nArray);
        return new SecP521R1FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat.create(17);
        SecP521R1Field.negate(this.x, nArray);
        return new SecP521R1FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat.create(17);
        SecP521R1Field.square(this.x, nArray);
        return new SecP521R1FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat.create(17);
        Mod.invert(SecP521R1Field.P, this.x, nArray);
        return new SecP521R1FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat.isZero(17, nArray) || Nat.isOne(17, nArray)) {
            return this;
        }
        int[] nArray2 = Nat.create(17);
        int[] nArray3 = Nat.create(17);
        SecP521R1Field.squareN(nArray, 519, nArray2);
        SecP521R1Field.square(nArray2, nArray3);
        return Nat.eq(17, nArray, nArray3) ? new SecP521R1FieldElement(nArray2) : null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecP521R1FieldElement)) {
            return false;
        }
        SecP521R1FieldElement secP521R1FieldElement = (SecP521R1FieldElement)object;
        return Nat.eq(17, this.x, secP521R1FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 17);
    }
}

