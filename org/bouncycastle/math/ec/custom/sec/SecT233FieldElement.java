/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT233Field;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

public class SecT233FieldElement
extends ECFieldElement {
    protected long[] x;

    public SecT233FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 233) {
            throw new IllegalArgumentException("x value invalid for SecT233FieldElement");
        }
        this.x = SecT233Field.fromBigInteger(bigInteger);
    }

    public SecT233FieldElement() {
        this.x = Nat256.create64();
    }

    protected SecT233FieldElement(long[] lArray) {
        this.x = lArray;
    }

    public boolean isOne() {
        return Nat256.isOne64(this.x);
    }

    public boolean isZero() {
        return Nat256.isZero64(this.x);
    }

    public boolean testBitZero() {
        return (this.x[0] & 1L) != 0L;
    }

    public BigInteger toBigInteger() {
        return Nat256.toBigInteger64(this.x);
    }

    public String getFieldName() {
        return "SecT233Field";
    }

    public int getFieldSize() {
        return 233;
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] lArray = Nat256.create64();
        SecT233Field.add(this.x, ((SecT233FieldElement)eCFieldElement).x, lArray);
        return new SecT233FieldElement(lArray);
    }

    public ECFieldElement addOne() {
        long[] lArray = Nat256.create64();
        SecT233Field.addOne(this.x, lArray);
        return new SecT233FieldElement(lArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return this.add(eCFieldElement);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] lArray = Nat256.create64();
        SecT233Field.multiply(this.x, ((SecT233FieldElement)eCFieldElement).x, lArray);
        return new SecT233FieldElement(lArray);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return this.multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT233FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT233FieldElement)eCFieldElement2).x;
        long[] lArray4 = ((SecT233FieldElement)eCFieldElement3).x;
        long[] lArray5 = Nat256.createExt64();
        SecT233Field.multiplyAddToExt(lArray, lArray2, lArray5);
        SecT233Field.multiplyAddToExt(lArray3, lArray4, lArray5);
        long[] lArray6 = Nat256.create64();
        SecT233Field.reduce(lArray5, lArray6);
        return new SecT233FieldElement(lArray6);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return this.multiply(eCFieldElement.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] lArray = Nat256.create64();
        SecT233Field.square(this.x, lArray);
        return new SecT233FieldElement(lArray);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return this.squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT233FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT233FieldElement)eCFieldElement2).x;
        long[] lArray4 = Nat256.createExt64();
        SecT233Field.squareAddToExt(lArray, lArray4);
        SecT233Field.multiplyAddToExt(lArray2, lArray3, lArray4);
        long[] lArray5 = Nat256.create64();
        SecT233Field.reduce(lArray4, lArray5);
        return new SecT233FieldElement(lArray5);
    }

    public ECFieldElement squarePow(int n) {
        if (n < 1) {
            return this;
        }
        long[] lArray = Nat256.create64();
        SecT233Field.squareN(this.x, n, lArray);
        return new SecT233FieldElement(lArray);
    }

    public ECFieldElement invert() {
        long[] lArray = Nat256.create64();
        SecT233Field.invert(this.x, lArray);
        return new SecT233FieldElement(lArray);
    }

    public ECFieldElement sqrt() {
        long[] lArray = Nat256.create64();
        SecT233Field.sqrt(this.x, lArray);
        return new SecT233FieldElement(lArray);
    }

    public int getRepresentation() {
        return 2;
    }

    public int getM() {
        return 233;
    }

    public int getK1() {
        return 74;
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
        if (!(object instanceof SecT233FieldElement)) {
            return false;
        }
        SecT233FieldElement secT233FieldElement = (SecT233FieldElement)object;
        return Nat256.eq64(this.x, secT233FieldElement.x);
    }

    public int hashCode() {
        return 0x238DDA ^ Arrays.hashCode(this.x, 0, 4);
    }
}

