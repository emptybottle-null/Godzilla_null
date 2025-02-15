/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT283Field;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat320;
import org.bouncycastle.util.Arrays;

public class SecT283FieldElement
extends ECFieldElement {
    protected long[] x;

    public SecT283FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 283) {
            throw new IllegalArgumentException("x value invalid for SecT283FieldElement");
        }
        this.x = SecT283Field.fromBigInteger(bigInteger);
    }

    public SecT283FieldElement() {
        this.x = Nat320.create64();
    }

    protected SecT283FieldElement(long[] lArray) {
        this.x = lArray;
    }

    public boolean isOne() {
        return Nat320.isOne64(this.x);
    }

    public boolean isZero() {
        return Nat320.isZero64(this.x);
    }

    public boolean testBitZero() {
        return (this.x[0] & 1L) != 0L;
    }

    public BigInteger toBigInteger() {
        return Nat320.toBigInteger64(this.x);
    }

    public String getFieldName() {
        return "SecT283Field";
    }

    public int getFieldSize() {
        return 283;
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] lArray = Nat320.create64();
        SecT283Field.add(this.x, ((SecT283FieldElement)eCFieldElement).x, lArray);
        return new SecT283FieldElement(lArray);
    }

    public ECFieldElement addOne() {
        long[] lArray = Nat320.create64();
        SecT283Field.addOne(this.x, lArray);
        return new SecT283FieldElement(lArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return this.add(eCFieldElement);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] lArray = Nat320.create64();
        SecT283Field.multiply(this.x, ((SecT283FieldElement)eCFieldElement).x, lArray);
        return new SecT283FieldElement(lArray);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return this.multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT283FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT283FieldElement)eCFieldElement2).x;
        long[] lArray4 = ((SecT283FieldElement)eCFieldElement3).x;
        long[] lArray5 = Nat.create64(9);
        SecT283Field.multiplyAddToExt(lArray, lArray2, lArray5);
        SecT283Field.multiplyAddToExt(lArray3, lArray4, lArray5);
        long[] lArray6 = Nat320.create64();
        SecT283Field.reduce(lArray5, lArray6);
        return new SecT283FieldElement(lArray6);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return this.multiply(eCFieldElement.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] lArray = Nat320.create64();
        SecT283Field.square(this.x, lArray);
        return new SecT283FieldElement(lArray);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return this.squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT283FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT283FieldElement)eCFieldElement2).x;
        long[] lArray4 = Nat.create64(9);
        SecT283Field.squareAddToExt(lArray, lArray4);
        SecT283Field.multiplyAddToExt(lArray2, lArray3, lArray4);
        long[] lArray5 = Nat320.create64();
        SecT283Field.reduce(lArray4, lArray5);
        return new SecT283FieldElement(lArray5);
    }

    public ECFieldElement squarePow(int n) {
        if (n < 1) {
            return this;
        }
        long[] lArray = Nat320.create64();
        SecT283Field.squareN(this.x, n, lArray);
        return new SecT283FieldElement(lArray);
    }

    public ECFieldElement invert() {
        long[] lArray = Nat320.create64();
        SecT283Field.invert(this.x, lArray);
        return new SecT283FieldElement(lArray);
    }

    public ECFieldElement sqrt() {
        long[] lArray = Nat320.create64();
        SecT283Field.sqrt(this.x, lArray);
        return new SecT283FieldElement(lArray);
    }

    public int getRepresentation() {
        return 3;
    }

    public int getM() {
        return 283;
    }

    public int getK1() {
        return 5;
    }

    public int getK2() {
        return 7;
    }

    public int getK3() {
        return 12;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecT283FieldElement)) {
            return false;
        }
        SecT283FieldElement secT283FieldElement = (SecT283FieldElement)object;
        return Nat320.eq64(this.x, secT283FieldElement.x);
    }

    public int hashCode() {
        return 0x2B33AB ^ Arrays.hashCode(this.x, 0, 5);
    }
}

