/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT163Field;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.Arrays;

public class SecT163FieldElement
extends ECFieldElement {
    protected long[] x;

    public SecT163FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 163) {
            throw new IllegalArgumentException("x value invalid for SecT163FieldElement");
        }
        this.x = SecT163Field.fromBigInteger(bigInteger);
    }

    public SecT163FieldElement() {
        this.x = Nat192.create64();
    }

    protected SecT163FieldElement(long[] lArray) {
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
        return "SecT163Field";
    }

    public int getFieldSize() {
        return 163;
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] lArray = Nat192.create64();
        SecT163Field.add(this.x, ((SecT163FieldElement)eCFieldElement).x, lArray);
        return new SecT163FieldElement(lArray);
    }

    public ECFieldElement addOne() {
        long[] lArray = Nat192.create64();
        SecT163Field.addOne(this.x, lArray);
        return new SecT163FieldElement(lArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return this.add(eCFieldElement);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] lArray = Nat192.create64();
        SecT163Field.multiply(this.x, ((SecT163FieldElement)eCFieldElement).x, lArray);
        return new SecT163FieldElement(lArray);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return this.multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT163FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT163FieldElement)eCFieldElement2).x;
        long[] lArray4 = ((SecT163FieldElement)eCFieldElement3).x;
        long[] lArray5 = Nat192.createExt64();
        SecT163Field.multiplyAddToExt(lArray, lArray2, lArray5);
        SecT163Field.multiplyAddToExt(lArray3, lArray4, lArray5);
        long[] lArray6 = Nat192.create64();
        SecT163Field.reduce(lArray5, lArray6);
        return new SecT163FieldElement(lArray6);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return this.multiply(eCFieldElement.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] lArray = Nat192.create64();
        SecT163Field.square(this.x, lArray);
        return new SecT163FieldElement(lArray);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return this.squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT163FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT163FieldElement)eCFieldElement2).x;
        long[] lArray4 = Nat192.createExt64();
        SecT163Field.squareAddToExt(lArray, lArray4);
        SecT163Field.multiplyAddToExt(lArray2, lArray3, lArray4);
        long[] lArray5 = Nat192.create64();
        SecT163Field.reduce(lArray4, lArray5);
        return new SecT163FieldElement(lArray5);
    }

    public ECFieldElement squarePow(int n) {
        if (n < 1) {
            return this;
        }
        long[] lArray = Nat192.create64();
        SecT163Field.squareN(this.x, n, lArray);
        return new SecT163FieldElement(lArray);
    }

    public ECFieldElement invert() {
        long[] lArray = Nat192.create64();
        SecT163Field.invert(this.x, lArray);
        return new SecT163FieldElement(lArray);
    }

    public ECFieldElement sqrt() {
        long[] lArray = Nat192.create64();
        SecT163Field.sqrt(this.x, lArray);
        return new SecT163FieldElement(lArray);
    }

    public int getRepresentation() {
        return 3;
    }

    public int getM() {
        return 163;
    }

    public int getK1() {
        return 3;
    }

    public int getK2() {
        return 6;
    }

    public int getK3() {
        return 7;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecT163FieldElement)) {
            return false;
        }
        SecT163FieldElement secT163FieldElement = (SecT163FieldElement)object;
        return Nat192.eq64(this.x, secT163FieldElement.x);
    }

    public int hashCode() {
        return 0x27FB3 ^ Arrays.hashCode(this.x, 0, 3);
    }
}

