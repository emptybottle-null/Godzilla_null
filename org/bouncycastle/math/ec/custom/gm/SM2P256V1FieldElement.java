/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.gm;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.gm.SM2P256V1Curve;
import org.bouncycastle.math.ec.custom.gm.SM2P256V1Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

public class SM2P256V1FieldElement
extends ECFieldElement {
    public static final BigInteger Q = SM2P256V1Curve.q;
    protected int[] x;

    public SM2P256V1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SM2P256V1FieldElement");
        }
        this.x = SM2P256V1Field.fromBigInteger(bigInteger);
    }

    public SM2P256V1FieldElement() {
        this.x = Nat256.create();
    }

    protected SM2P256V1FieldElement(int[] nArray) {
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
        return "SM2P256V1Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        SM2P256V1Field.add(this.x, ((SM2P256V1FieldElement)eCFieldElement).x, nArray);
        return new SM2P256V1FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat256.create();
        SM2P256V1Field.addOne(this.x, nArray);
        return new SM2P256V1FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        SM2P256V1Field.subtract(this.x, ((SM2P256V1FieldElement)eCFieldElement).x, nArray);
        return new SM2P256V1FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        SM2P256V1Field.multiply(this.x, ((SM2P256V1FieldElement)eCFieldElement).x, nArray);
        return new SM2P256V1FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat256.create();
        Mod.invert(SM2P256V1Field.P, ((SM2P256V1FieldElement)eCFieldElement).x, nArray);
        SM2P256V1Field.multiply(nArray, this.x, nArray);
        return new SM2P256V1FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat256.create();
        SM2P256V1Field.negate(this.x, nArray);
        return new SM2P256V1FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat256.create();
        SM2P256V1Field.square(this.x, nArray);
        return new SM2P256V1FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat256.create();
        Mod.invert(SM2P256V1Field.P, this.x, nArray);
        return new SM2P256V1FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat256.isZero(nArray) || Nat256.isOne(nArray)) {
            return this;
        }
        int[] nArray2 = Nat256.create();
        SM2P256V1Field.square(nArray, nArray2);
        SM2P256V1Field.multiply(nArray2, nArray, nArray2);
        int[] nArray3 = Nat256.create();
        SM2P256V1Field.squareN(nArray2, 2, nArray3);
        SM2P256V1Field.multiply(nArray3, nArray2, nArray3);
        int[] nArray4 = Nat256.create();
        SM2P256V1Field.squareN(nArray3, 2, nArray4);
        SM2P256V1Field.multiply(nArray4, nArray2, nArray4);
        int[] nArray5 = nArray2;
        SM2P256V1Field.squareN(nArray4, 6, nArray5);
        SM2P256V1Field.multiply(nArray5, nArray4, nArray5);
        int[] nArray6 = Nat256.create();
        SM2P256V1Field.squareN(nArray5, 12, nArray6);
        SM2P256V1Field.multiply(nArray6, nArray5, nArray6);
        int[] nArray7 = nArray5;
        SM2P256V1Field.squareN(nArray6, 6, nArray7);
        SM2P256V1Field.multiply(nArray7, nArray4, nArray7);
        int[] nArray8 = nArray4;
        SM2P256V1Field.square(nArray7, nArray8);
        SM2P256V1Field.multiply(nArray8, nArray, nArray8);
        int[] nArray9 = nArray6;
        SM2P256V1Field.squareN(nArray8, 31, nArray9);
        int[] nArray10 = nArray7;
        SM2P256V1Field.multiply(nArray9, nArray8, nArray10);
        SM2P256V1Field.squareN(nArray9, 32, nArray9);
        SM2P256V1Field.multiply(nArray9, nArray10, nArray9);
        SM2P256V1Field.squareN(nArray9, 62, nArray9);
        SM2P256V1Field.multiply(nArray9, nArray10, nArray9);
        SM2P256V1Field.squareN(nArray9, 4, nArray9);
        SM2P256V1Field.multiply(nArray9, nArray3, nArray9);
        SM2P256V1Field.squareN(nArray9, 32, nArray9);
        SM2P256V1Field.multiply(nArray9, nArray, nArray9);
        SM2P256V1Field.squareN(nArray9, 62, nArray9);
        int[] nArray11 = nArray3;
        SM2P256V1Field.square(nArray9, nArray11);
        return Nat256.eq(nArray, nArray11) ? new SM2P256V1FieldElement(nArray9) : null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SM2P256V1FieldElement)) {
            return false;
        }
        SM2P256V1FieldElement sM2P256V1FieldElement = (SM2P256V1FieldElement)object;
        return Nat256.eq(this.x, sM2P256V1FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 8);
    }
}

