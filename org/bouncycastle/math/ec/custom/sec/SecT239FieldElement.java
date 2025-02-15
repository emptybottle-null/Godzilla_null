/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT239Field;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

public class SecT239FieldElement
extends ECFieldElement {
    protected long[] x;

    public SecT239FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 239) {
            throw new IllegalArgumentException("x value invalid for SecT239FieldElement");
        }
        this.x = SecT239Field.fromBigInteger(bigInteger);
    }

    public SecT239FieldElement() {
        this.x = Nat256.create64();
    }

    protected SecT239FieldElement(long[] lArray) {
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
        return "SecT239Field";
    }

    public int getFieldSize() {
        return 239;
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] lArray = Nat256.create64();
        SecT239Field.add(this.x, ((SecT239FieldElement)eCFieldElement).x, lArray);
        return new SecT239FieldElement(lArray);
    }

    public ECFieldElement addOne() {
        long[] lArray = Nat256.create64();
        SecT239Field.addOne(this.x, lArray);
        return new SecT239FieldElement(lArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return this.add(eCFieldElement);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] lArray = Nat256.create64();
        SecT239Field.multiply(this.x, ((SecT239FieldElement)eCFieldElement).x, lArray);
        return new SecT239FieldElement(lArray);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return this.multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT239FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT239FieldElement)eCFieldElement2).x;
        long[] lArray4 = ((SecT239FieldElement)eCFieldElement3).x;
        long[] lArray5 = Nat256.createExt64();
        SecT239Field.multiplyAddToExt(lArray, lArray2, lArray5);
        SecT239Field.multiplyAddToExt(lArray3, lArray4, lArray5);
        long[] lArray6 = Nat256.create64();
        SecT239Field.reduce(lArray5, lArray6);
        return new SecT239FieldElement(lArray6);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return this.multiply(eCFieldElement.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] lArray = Nat256.create64();
        SecT239Field.square(this.x, lArray);
        return new SecT239FieldElement(lArray);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return this.squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT239FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT239FieldElement)eCFieldElement2).x;
        long[] lArray4 = Nat256.createExt64();
        SecT239Field.squareAddToExt(lArray, lArray4);
        SecT239Field.multiplyAddToExt(lArray2, lArray3, lArray4);
        long[] lArray5 = Nat256.create64();
        SecT239Field.reduce(lArray4, lArray5);
        return new SecT239FieldElement(lArray5);
    }

    public ECFieldElement squarePow(int n) {
        if (n < 1) {
            return this;
        }
        long[] lArray = Nat256.create64();
        SecT239Field.squareN(this.x, n, lArray);
        return new SecT239FieldElement(lArray);
    }

    public ECFieldElement invert() {
        long[] lArray = Nat256.create64();
        SecT239Field.invert(this.x, lArray);
        return new SecT239FieldElement(lArray);
    }

    public ECFieldElement sqrt() {
        long[] lArray = Nat256.create64();
        SecT239Field.sqrt(this.x, lArray);
        return new SecT239FieldElement(lArray);
    }

    public int getRepresentation() {
        return 2;
    }

    public int getM() {
        return 239;
    }

    public int getK1() {
        return 158;
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
        if (!(object instanceof SecT239FieldElement)) {
            return false;
        }
        SecT239FieldElement secT239FieldElement = (SecT239FieldElement)object;
        return Nat256.eq64(this.x, secT239FieldElement.x);
    }

    public int hashCode() {
        return 0x16CAFFE ^ Arrays.hashCode(this.x, 0, 4);
    }
}

