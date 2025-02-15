/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecT571Field;
import org.bouncycastle.math.raw.Nat576;
import org.bouncycastle.util.Arrays;

public class SecT571FieldElement
extends ECFieldElement {
    protected long[] x;

    public SecT571FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 571) {
            throw new IllegalArgumentException("x value invalid for SecT571FieldElement");
        }
        this.x = SecT571Field.fromBigInteger(bigInteger);
    }

    public SecT571FieldElement() {
        this.x = Nat576.create64();
    }

    protected SecT571FieldElement(long[] lArray) {
        this.x = lArray;
    }

    public boolean isOne() {
        return Nat576.isOne64(this.x);
    }

    public boolean isZero() {
        return Nat576.isZero64(this.x);
    }

    public boolean testBitZero() {
        return (this.x[0] & 1L) != 0L;
    }

    public BigInteger toBigInteger() {
        return Nat576.toBigInteger64(this.x);
    }

    public String getFieldName() {
        return "SecT571Field";
    }

    public int getFieldSize() {
        return 571;
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] lArray = Nat576.create64();
        SecT571Field.add(this.x, ((SecT571FieldElement)eCFieldElement).x, lArray);
        return new SecT571FieldElement(lArray);
    }

    public ECFieldElement addOne() {
        long[] lArray = Nat576.create64();
        SecT571Field.addOne(this.x, lArray);
        return new SecT571FieldElement(lArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return this.add(eCFieldElement);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] lArray = Nat576.create64();
        SecT571Field.multiply(this.x, ((SecT571FieldElement)eCFieldElement).x, lArray);
        return new SecT571FieldElement(lArray);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return this.multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT571FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT571FieldElement)eCFieldElement2).x;
        long[] lArray4 = ((SecT571FieldElement)eCFieldElement3).x;
        long[] lArray5 = Nat576.createExt64();
        SecT571Field.multiplyAddToExt(lArray, lArray2, lArray5);
        SecT571Field.multiplyAddToExt(lArray3, lArray4, lArray5);
        long[] lArray6 = Nat576.create64();
        SecT571Field.reduce(lArray5, lArray6);
        return new SecT571FieldElement(lArray6);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return this.multiply(eCFieldElement.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] lArray = Nat576.create64();
        SecT571Field.square(this.x, lArray);
        return new SecT571FieldElement(lArray);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return this.squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] lArray = this.x;
        long[] lArray2 = ((SecT571FieldElement)eCFieldElement).x;
        long[] lArray3 = ((SecT571FieldElement)eCFieldElement2).x;
        long[] lArray4 = Nat576.createExt64();
        SecT571Field.squareAddToExt(lArray, lArray4);
        SecT571Field.multiplyAddToExt(lArray2, lArray3, lArray4);
        long[] lArray5 = Nat576.create64();
        SecT571Field.reduce(lArray4, lArray5);
        return new SecT571FieldElement(lArray5);
    }

    public ECFieldElement squarePow(int n) {
        if (n < 1) {
            return this;
        }
        long[] lArray = Nat576.create64();
        SecT571Field.squareN(this.x, n, lArray);
        return new SecT571FieldElement(lArray);
    }

    public ECFieldElement invert() {
        long[] lArray = Nat576.create64();
        SecT571Field.invert(this.x, lArray);
        return new SecT571FieldElement(lArray);
    }

    public ECFieldElement sqrt() {
        long[] lArray = Nat576.create64();
        SecT571Field.sqrt(this.x, lArray);
        return new SecT571FieldElement(lArray);
    }

    public int getRepresentation() {
        return 3;
    }

    public int getM() {
        return 571;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 5;
    }

    public int getK3() {
        return 10;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecT571FieldElement)) {
            return false;
        }
        SecT571FieldElement secT571FieldElement = (SecT571FieldElement)object;
        return Nat576.eq64(this.x, secT571FieldElement.x);
    }

    public int hashCode() {
        return 0x5724CC ^ Arrays.hashCode(this.x, 0, 9);
    }
}

