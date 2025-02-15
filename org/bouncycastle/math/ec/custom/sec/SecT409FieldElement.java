/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT409Field;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat448;
import org.bouncycastle.util.Arrays;

public class SecT409FieldElement
extends ECFieldElement {
    protected long[] x;

    public SecT409FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 409) {
            throw new IllegalArgumentException("x value invalid for SecT409FieldElement");
        }
        this.x = SecT409Field.fromBigInteger(bigInteger);
    }

    public SecT409FieldElement() {
        this.x = Nat448.create64();
    }

    protected SecT409FieldElement(long[] lArray) {
        this.x = lArray;
    }

    public boolean isOne() {
        return Nat448.isOne64(this.x);
    }

    public boolean isZero() {
        return Nat448.isZero64(this.x);
    }

    public boolean testBitZero() {
        return (this.x[0] & 1L) != 0L;
    }

    public BigInteger toBigInteger() {
        return Nat448.toBigInteger64(this.x);
    }

    public String getFieldName() {
        return "SecT409Field";
    }

    public int getFieldSize() {
        return 409;
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] lArray = Nat448.create64();
        SecT409Field.add(this.x, ((SecT409FieldElement)eCFieldElement).x, lArray);
        return new SecT409FieldElement(lArray);
    }

    public ECFieldElement addOne() {
        long[] lArray = Nat448.create64();
        SecT409Field.addOne(this.x, lArray);
        return new SecT409FieldElement(lArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return this.add(eCFieldElement);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] lArray = Nat448.create64();
        SecT409Field.multiply(this.x, ((SecT409FieldElement)eCFieldElement).x, lArray);
        return new SecT409FieldElement(lArray);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return this.multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT409FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT409FieldElement)eCFieldElement2).x;
        long[] lArray4 = ((SecT409FieldElement)eCFieldElement3).x;
        long[] lArray5 = Nat.create64(13);
        SecT409Field.multiplyAddToExt(lArray, lArray2, lArray5);
        SecT409Field.multiplyAddToExt(lArray3, lArray4, lArray5);
        long[] lArray6 = Nat448.create64();
        SecT409Field.reduce(lArray5, lArray6);
        return new SecT409FieldElement(lArray6);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return this.multiply(eCFieldElement.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] lArray = Nat448.create64();
        SecT409Field.square(this.x, lArray);
        return new SecT409FieldElement(lArray);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return this.squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT409FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT409FieldElement)eCFieldElement2).x;
        long[] lArray4 = Nat.create64(13);
        SecT409Field.squareAddToExt(lArray, lArray4);
        SecT409Field.multiplyAddToExt(lArray2, lArray3, lArray4);
        long[] lArray5 = Nat448.create64();
        SecT409Field.reduce(lArray4, lArray5);
        return new SecT409FieldElement(lArray5);
    }

    public ECFieldElement squarePow(int n) {
        if (n < 1) {
            return this;
        }
        long[] lArray = Nat448.create64();
        SecT409Field.squareN(this.x, n, lArray);
        return new SecT409FieldElement(lArray);
    }

    public ECFieldElement invert() {
        long[] lArray = Nat448.create64();
        SecT409Field.invert(this.x, lArray);
        return new SecT409FieldElement(lArray);
    }

    public ECFieldElement sqrt() {
        long[] lArray = Nat448.create64();
        SecT409Field.sqrt(this.x, lArray);
        return new SecT409FieldElement(lArray);
    }

    public int getRepresentation() {
        return 2;
    }

    public int getM() {
        return 409;
    }

    public int getK1() {
        return 87;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecT409FieldElement)) {
            return false;
        }
        SecT409FieldElement secT409FieldElement = (SecT409FieldElement)object;
        return Nat448.eq64(this.x, secT409FieldElement.x);
    }

    public int hashCode() {
        return 0x3E68E7 ^ Arrays.hashCode(this.x, 0, 7);
    }
}

