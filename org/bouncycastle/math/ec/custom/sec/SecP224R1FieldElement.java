/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP224R1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP224R1Field;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat224;
import org.bouncycastle.util.Arrays;

public class SecP224R1FieldElement
extends ECFieldElement {
    public static final BigInteger Q = SecP224R1Curve.q;
    protected int[] x;

    public SecP224R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224R1FieldElement");
        }
        this.x = SecP224R1Field.fromBigInteger(bigInteger);
    }

    public SecP224R1FieldElement() {
        this.x = Nat224.create();
    }

    protected SecP224R1FieldElement(int[] nArray) {
        this.x = nArray;
    }

    public boolean isZero() {
        return Nat224.isZero(this.x);
    }

    public boolean isOne() {
        return Nat224.isOne(this.x);
    }

    public boolean testBitZero() {
        return Nat224.getBit(this.x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat224.toBigInteger(this.x);
    }

    public String getFieldName() {
        return "SecP224R1Field";
    }

    public int getFieldSize() {
        return Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] nArray = Nat224.create();
        SecP224R1Field.add(this.x, ((SecP224R1FieldElement)eCFieldElement).x, nArray);
        return new SecP224R1FieldElement(nArray);
    }

    public ECFieldElement addOne() {
        int[] nArray = Nat224.create();
        SecP224R1Field.addOne(this.x, nArray);
        return new SecP224R1FieldElement(nArray);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] nArray = Nat224.create();
        SecP224R1Field.subtract(this.x, ((SecP224R1FieldElement)eCFieldElement).x, nArray);
        return new SecP224R1FieldElement(nArray);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] nArray = Nat224.create();
        SecP224R1Field.multiply(this.x, ((SecP224R1FieldElement)eCFieldElement).x, nArray);
        return new SecP224R1FieldElement(nArray);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] nArray = Nat224.create();
        Mod.invert(SecP224R1Field.P, ((SecP224R1FieldElement)eCFieldElement).x, nArray);
        SecP224R1Field.multiply(nArray, this.x, nArray);
        return new SecP224R1FieldElement(nArray);
    }

    public ECFieldElement negate() {
        int[] nArray = Nat224.create();
        SecP224R1Field.negate(this.x, nArray);
        return new SecP224R1FieldElement(nArray);
    }

    public ECFieldElement square() {
        int[] nArray = Nat224.create();
        SecP224R1Field.square(this.x, nArray);
        return new SecP224R1FieldElement(nArray);
    }

    public ECFieldElement invert() {
        int[] nArray = Nat224.create();
        Mod.invert(SecP224R1Field.P, this.x, nArray);
        return new SecP224R1FieldElement(nArray);
    }

    public ECFieldElement sqrt() {
        int[] nArray = this.x;
        if (Nat224.isZero(nArray) || Nat224.isOne(nArray)) {
            return this;
        }
        int[] nArray2 = Nat224.create();
        SecP224R1Field.negate(nArray, nArray2);
        int[] nArray3 = Mod.random(SecP224R1Field.P);
        int[] nArray4 = Nat224.create();
        if (!SecP224R1FieldElement.isSquare(nArray)) {
            return null;
        }
        while (!SecP224R1FieldElement.trySqrt(nArray2, nArray3, nArray4)) {
            SecP224R1Field.addOne(nArray3, nArray3);
        }
        SecP224R1Field.square(nArray4, nArray3);
        return Nat224.eq(nArray, nArray3) ? new SecP224R1FieldElement(nArray4) : null;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SecP224R1FieldElement)) {
            return false;
        }
        SecP224R1FieldElement secP224R1FieldElement = (SecP224R1FieldElement)object;
        return Nat224.eq(this.x, secP224R1FieldElement.x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 7);
    }

    private static boolean isSquare(int[] nArray) {
        int[] nArray2 = Nat224.create();
        int[] nArray3 = Nat224.create();
        Nat224.copy(nArray, nArray2);
        for (int i = 0; i < 7; ++i) {
            Nat224.copy(nArray2, nArray3);
            SecP224R1Field.squareN(nArray2, 1 << i, nArray2);
            SecP224R1Field.multiply(nArray2, nArray3, nArray2);
        }
        SecP224R1Field.squareN(nArray2, 95, nArray2);
        return Nat224.isOne(nArray2);
    }

    private static void RM(int[] nArray, int[] nArray2, int[] nArray3, int[] nArray4, int[] nArray5, int[] nArray6, int[] nArray7) {
        SecP224R1Field.multiply(nArray5, nArray3, nArray7);
        SecP224R1Field.multiply(nArray7, nArray, nArray7);
        SecP224R1Field.multiply(nArray4, nArray2, nArray6);
        SecP224R1Field.add(nArray6, nArray7, nArray6);
        SecP224R1Field.multiply(nArray4, nArray3, nArray7);
        Nat224.copy(nArray6, nArray4);
        SecP224R1Field.multiply(nArray5, nArray2, nArray5);
        SecP224R1Field.add(nArray5, nArray7, nArray5);
        SecP224R1Field.square(nArray5, nArray6);
        SecP224R1Field.multiply(nArray6, nArray, nArray6);
    }

    private static void RP(int[] nArray, int[] nArray2, int[] nArray3, int[] nArray4, int[] nArray5) {
        Nat224.copy(nArray, nArray4);
        int[] nArray6 = Nat224.create();
        int[] nArray7 = Nat224.create();
        for (int i = 0; i < 7; ++i) {
            Nat224.copy(nArray2, nArray6);
            Nat224.copy(nArray3, nArray7);
            int n = 1 << i;
            while (--n >= 0) {
                SecP224R1FieldElement.RS(nArray2, nArray3, nArray4, nArray5);
            }
            SecP224R1FieldElement.RM(nArray, nArray6, nArray7, nArray2, nArray3, nArray4, nArray5);
        }
    }

    private static void RS(int[] nArray, int[] nArray2, int[] nArray3, int[] nArray4) {
        SecP224R1Field.multiply(nArray2, nArray, nArray2);
        SecP224R1Field.twice(nArray2, nArray2);
        SecP224R1Field.square(nArray, nArray4);
        SecP224R1Field.add(nArray3, nArray4, nArray);
        SecP224R1Field.multiply(nArray3, nArray4, nArray3);
        int n = Nat.shiftUpBits(7, nArray3, 2, 0);
        SecP224R1Field.reduce32(n, nArray3);
    }

    private static boolean trySqrt(int[] nArray, int[] nArray2, int[] nArray3) {
        int[] nArray4 = Nat224.create();
        Nat224.copy(nArray2, nArray4);
        int[] nArray5 = Nat224.create();
        nArray5[0] = 1;
        int[] nArray6 = Nat224.create();
        SecP224R1FieldElement.RP(nArray, nArray4, nArray5, nArray6, nArray3);
        int[] nArray7 = Nat224.create();
        int[] nArray8 = Nat224.create();
        for (int i = 1; i < 96; ++i) {
            Nat224.copy(nArray4, nArray7);
            Nat224.copy(nArray5, nArray8);
            SecP224R1FieldElement.RS(nArray4, nArray5, nArray6, nArray3);
            if (!Nat224.isZero(nArray4)) continue;
            Mod.invert(SecP224R1Field.P, nArray8, nArray3);
            SecP224R1Field.multiply(nArray3, nArray7, nArray3);
            return true;
        }
        return false;
    }
}

