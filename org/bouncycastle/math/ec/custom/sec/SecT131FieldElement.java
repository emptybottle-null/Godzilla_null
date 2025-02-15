/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT131Field;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.Arrays;

public class SecT131FieldElement
extends ECFieldElement {
    protected long[] x;

    public SecT131FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 131) {
            throw new IllegalArgumentException("x value invalid for SecT131FieldElement");
        }
        this.x = SecT131Field.fromBigInteger(bigInteger);
    }

    public SecT131FieldElement() {
        this.x = Nat192.create64();
    }

    protected SecT131FieldElement(long[] lArray) {
        this.x = lArray;
    }

    public boolean isOne() {
        return Nat192.isOne64(this.x);
    }

    public boolean isZero() {
        return Nat192.isZero64(this.x);
    }

    public boolean testBitZero() {
        return (this.x[0] & 1L) != 0L;
    }

    public BigInteger toBigInteger() {
        return Nat192.toBigInteger64(this.x);
    }

    public String getFieldName() {
        return "SecT131Field";
    }

    public int getFieldSize() {
        return 131;
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] lArray = Nat192.create64();
        SecT131Field.add(this.x, ((SecT131FieldElement)eCFieldElement).x, lArray);
        return new SecT131FieldElement(lArray);
    }

    public ECFieldElement addOne() {
        long[] lArray = Nat192.create64();
        SecT131Field.addOne(this.x, lArray);
        return new SecT131FieldElement(lArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return this.add(eCFieldElement);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] lArray = Nat192.create64();
        SecT131Field.multiply(this.x, ((SecT131FieldElement)eCFieldElement).x, lArray);
        return new SecT131FieldElement(lArray);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return this.multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT131FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT131FieldElement)eCFieldElement2).x;
        long[] lArray4 = ((SecT131FieldElement)eCFieldElement3).x;
        long[] lArray5 = Nat.create64(5);
        SecT131Field.multiplyAddToExt(lArray, lArray2, lArray5);
        SecT131Field.multiplyAddToExt(lArray3, lArray4, lArray5);
        long[] lArray6 = Nat192.create64();
        SecT131Field.reduce(lArray5, lArray6);
        return new SecT131FieldElement(lArray6);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return this.multiply(eCFieldElement.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] lArray = Nat192.create64();
        SecT131Field.square(this.x, lArray);
        return new SecT131FieldElement(lArray);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return this.squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT131FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT131FieldElement)eCFieldElement2).x;
        long[] lArray4 = Nat.create64(5);
        SecT131Field.squareAddToExt(lArray, lArray4);
        SecT131Field.multiplyAddToExt(lArray2, lArray3, lArray4);
        long[] lArray5 = Nat192.create64();
        SecT131Field.reduce(lArray4, lArray5);
        return new SecT131FieldElement(lArray5);
    }

    public ECFieldElement squarePow(int n) {
        if (n < 1) {
            return this;
        }
        long[] lArray = Nat192.create64();
        SecT131Field.squareN(this.x, n, lArray);
        return new SecT131FieldElement(lArray);
    }

    public ECFieldElement invert() {
        long[] lArray = Nat192.create64();
        SecT131Field.invert(this.x, lArray);
        return new SecT131FieldElement(lArray);
    }

    public ECFieldElement sqrt() {
        long[] lArray = Nat192.create64();
        SecT131Field.sqrt(this.x, lArray);
        return new SecT131FieldElement(lArray);
    }

    public int getRepresentation() {
        return 3;
    }

    public int getM() {
        return 131;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 3;
    }

    public int getK3() {
        return 8;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecT131FieldElement)) {
            return false;
        }
        SecT131FieldElement secT131FieldElement = (SecT131FieldElement)object;
        return Nat192.eq64(this.x, secT131FieldElement.x);
    }

    public int hashCode() {
        return 0x202F8 ^ Arrays.hashCode(this.x, 0, 3);
    }
}

