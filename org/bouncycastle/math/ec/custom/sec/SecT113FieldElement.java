/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT113Field;
import org.bouncycastle.math.raw.Nat128;
import org.bouncycastle.util.Arrays;

public class SecT113FieldElement
extends ECFieldElement {
    protected long[] x;

    public SecT113FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 113) {
            throw new IllegalArgumentException("x value invalid for SecT113FieldElement");
        }
        this.x = SecT113Field.fromBigInteger(bigInteger);
    }

    public SecT113FieldElement() {
        this.x = Nat128.create64();
    }

    protected SecT113FieldElement(long[] lArray) {
        this.x = lArray;
    }

    public boolean isOne() {
        return Nat128.isOne64(this.x);
    }

    public boolean isZero() {
        return Nat128.isZero64(this.x);
    }

    public boolean testBitZero() {
        return (this.x[0] & 1L) != 0L;
    }

    public BigInteger toBigInteger() {
        return Nat128.toBigInteger64(this.x);
    }

    public String getFieldName() {
        return "SecT113Field";
    }

    public int getFieldSize() {
        return 113;
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] lArray = Nat128.create64();
        SecT113Field.add(this.x, ((SecT113FieldElement)eCFieldElement).x, lArray);
        return new SecT113FieldElement(lArray);
    }

    public ECFieldElement addOne() {
        long[] lArray = Nat128.create64();
        SecT113Field.addOne(this.x, lArray);
        return new SecT113FieldElement(lArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return this.add(eCFieldElement);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] lArray = Nat128.create64();
        SecT113Field.multiply(this.x, ((SecT113FieldElement)eCFieldElement).x, lArray);
        return new SecT113FieldElement(lArray);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return this.multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT113FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT113FieldElement)eCFieldElement2).x;
        long[] lArray4 = ((SecT113FieldElement)eCFieldElement3).x;
        long[] lArray5 = Nat128.createExt64();
        SecT113Field.multiplyAddToExt(lArray, lArray2, lArray5);
        SecT113Field.multiplyAddToExt(lArray3, lArray4, lArray5);
        long[] lArray6 = Nat128.create64();
        SecT113Field.reduce(lArray5, lArray6);
        return new SecT113FieldElement(lArray6);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return this.multiply(eCFieldElement.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] lArray = Nat128.create64();
        SecT113Field.square(this.x, lArray);
        return new SecT113FieldElement(lArray);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return this.squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT113FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT113FieldElement)eCFieldElement2).x;
        long[] lArray4 = Nat128.createExt64();
        SecT113Field.squareAddToExt(lArray, lArray4);
        SecT113Field.multiplyAddToExt(lArray2, lArray3, lArray4);
        long[] lArray5 = Nat128.create64();
        SecT113Field.reduce(lArray4, lArray5);
        return new SecT113FieldElement(lArray5);
    }

    public ECFieldElement squarePow(int n) {
        if (n < 1) {
            return this;
        }
        long[] lArray = Nat128.create64();
        SecT113Field.squareN(this.x, n, lArray);
        return new SecT113FieldElement(lArray);
    }

    public ECFieldElement invert() {
        long[] lArray = Nat128.create64();
        SecT113Field.invert(this.x, lArray);
        return new SecT113FieldElement(lArray);
    }

    public ECFieldElement sqrt() {
        long[] lArray = Nat128.create64();
        SecT113Field.sqrt(this.x, lArray);
        return new SecT113FieldElement(lArray);
    }

    public int getRepresentation() {
        return 2;
    }

    public int getM() {
        return 113;
    }

    public int getK1() {
        return 9;
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
        if (!(object instanceof SecT113FieldElement)) {
            return false;
        }
        SecT113FieldElement secT113FieldElement = (SecT113FieldElement)object;
        return Nat128.eq64(this.x, secT113FieldElement.x);
    }

    public int hashCode() {
        return 0x1B971 ^ Arrays.hashCode(this.x, 0, 2);
    }
}

