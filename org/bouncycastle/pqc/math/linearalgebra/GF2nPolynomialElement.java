/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.math.BigInteger;
import java.util.Random;
import org.bouncycastle.pqc.math.linearalgebra.GF2Polynomial;
import org.bouncycastle.pqc.math.linearalgebra.GF2nElement;
import org.bouncycastle.pqc.math.linearalgebra.GF2nPolynomialField;
import org.bouncycastle.pqc.math.linearalgebra.GFElement;
import org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions;

public class GF2nPolynomialElement
extends GF2nElement {
    private static final int[] bitMask = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 0x100000, 0x200000, 0x400000, 0x800000, 0x1000000, 0x2000000, 0x4000000, 0x8000000, 0x10000000, 0x20000000, 0x40000000, Integer.MIN_VALUE, 0};
    private GF2Polynomial polynomial;

    public GF2nPolynomialElement(GF2nPolynomialField gF2nPolynomialField, Random random) {
        this.mField = gF2nPolynomialField;
        this.mDegree = this.mField.getDegree();
        this.polynomial = new GF2Polynomial(this.mDegree);
        this.randomize(random);
    }

    public GF2nPolynomialElement(GF2nPolynomialField gF2nPolynomialField, GF2Polynomial gF2Polynomial) {
        this.mField = gF2nPolynomialField;
        this.mDegree = this.mField.getDegree();
        this.polynomial = new GF2Polynomial(gF2Polynomial);
        this.polynomial.expandN(this.mDegree);
    }

    public GF2nPolynomialElement(GF2nPolynomialField gF2nPolynomialField, byte[] byArray) {
        this.mField = gF2nPolynomialField;
        this.mDegree = this.mField.getDegree();
        this.polynomial = new GF2Polynomial(this.mDegree, byArray);
        this.polynomial.expandN(this.mDegree);
    }

    public GF2nPolynomialElement(GF2nPolynomialField gF2nPolynomialField, int[] nArray) {
        this.mField = gF2nPolynomialField;
        this.mDegree = this.mField.getDegree();
        this.polynomial = new GF2Polynomial(this.mDegree, nArray);
        this.polynomial.expandN(gF2nPolynomialField.mDegree);
    }

    public GF2nPolynomialElement(GF2nPolynomialElement gF2nPolynomialElement) {
        this.mField = gF2nPolynomialElement.mField;
        this.mDegree = gF2nPolynomialElement.mDegree;
        this.polynomial = new GF2Polynomial(gF2nPolynomialElement.polynomial);
    }

    public Object clone() {
        return new GF2nPolynomialElement(this);
    }

    void assignZero() {
        this.polynomial.assignZero();
    }

    public static GF2nPolynomialElement ZERO(GF2nPolynomialField gF2nPolynomialField) {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(gF2nPolynomialField.getDegree());
        return new GF2nPolynomialElement(gF2nPolynomialField, gF2Polynomial);
    }

    public static GF2nPolynomialElement ONE(GF2nPolynomialField gF2nPolynomialField) {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(gF2nPolynomialField.getDegree(), new int[]{1});
        return new GF2nPolynomialElement(gF2nPolynomialField, gF2Polynomial);
    }

    void assignOne() {
        this.polynomial.assignOne();
    }

    private void randomize(Random random) {
        this.polynomial.expandN(this.mDegree);
        this.polynomial.randomize(random);
    }

    public boolean isZero() {
        return this.polynomial.isZero();
    }

    public boolean isOne() {
        return this.polynomial.isOne();
    }

    public boolean equals(Object object) {
        if (object == null || !(object instanceof GF2nPolynomialElement)) {
            return false;
        }
        GF2nPolynomialElement gF2nPolynomialElement = (GF2nPolynomialElement)object;
        if (this.mField != gF2nPolynomialElement.mField && !this.mField.getFieldPolynomial().equals(gF2nPolynomialElement.mField.getFieldPolynomial())) {
            return false;
        }
        return this.polynomial.equals(gF2nPolynomialElement.polynomial);
    }

    public int hashCode() {
        return this.mField.hashCode() + this.polynomial.hashCode();
    }

    private GF2Polynomial getGF2Polynomial() {
        return new GF2Polynomial(this.polynomial);
    }

    boolean testBit(int n) {
        return this.polynomial.testBit(n);
    }

    public boolean testRightmostBit() {
        return this.polynomial.testBit(0);
    }

    public GFElement add(GFElement gFElement) throws RuntimeException {
        GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this);
        gF2nPolynomialElement.addToThis(gFElement);
        return gF2nPolynomialElement;
    }

    public void addToThis(GFElement gFElement) throws RuntimeException {
        if (!(gFElement instanceof GF2nPolynomialElement)) {
            throw new RuntimeException();
        }
        if (!this.mField.equals(((GF2nPolynomialElement)gFElement).mField)) {
            throw new RuntimeException();
        }
        this.polynomial.addToThis(((GF2nPolynomialElement)gFElement).polynomial);
    }

    public GF2nElement increase() {
        GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this);
        gF2nPolynomialElement.increaseThis();
        return gF2nPolynomialElement;
    }

    public void increaseThis() {
        this.polynomial.increaseThis();
    }

    public GFElement multiply(GFElement gFElement) throws RuntimeException {
        GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this);
        gF2nPolynomialElement.multiplyThisBy(gFElement);
        return gF2nPolynomialElement;
    }

    public void multiplyThisBy(GFElement gFElement) throws RuntimeException {
        if (!(gFElement instanceof GF2nPolynomialElement)) {
            throw new RuntimeException();
        }
        if (!this.mField.equals(((GF2nPolynomialElement)gFElement).mField)) {
            throw new RuntimeException();
        }
        if (this.equals(gFElement)) {
            this.squareThis();
            return;
        }
        this.polynomial = this.polynomial.multiply(((GF2nPolynomialElement)gFElement).polynomial);
        this.reduceThis();
    }

    public GFElement invert() throws ArithmeticException {
        return this.invertMAIA();
    }

    public GF2nPolynomialElement invertEEA() throws ArithmeticException {
        if (this.isZero()) {
            throw new ArithmeticException();
        }
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.mDegree + 32, "ONE");
        gF2Polynomial.reduceN();
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.mDegree + 32);
        gF2Polynomial2.reduceN();
        GF2Polynomial gF2Polynomial3 = this.getGF2Polynomial();
        GF2Polynomial gF2Polynomial4 = this.mField.getFieldPolynomial();
        gF2Polynomial3.reduceN();
        while (!gF2Polynomial3.isOne()) {
            gF2Polynomial3.reduceN();
            gF2Polynomial4.reduceN();
            int n = gF2Polynomial3.getLength() - gF2Polynomial4.getLength();
            if (n < 0) {
                GF2Polynomial gF2Polynomial5 = gF2Polynomial3;
                gF2Polynomial3 = gF2Polynomial4;
                gF2Polynomial4 = gF2Polynomial5;
                gF2Polynomial5 = gF2Polynomial;
                gF2Polynomial = gF2Polynomial2;
                gF2Polynomial2 = gF2Polynomial5;
                n = -n;
                gF2Polynomial2.reduceN();
            }
            gF2Polynomial3.shiftLeftAddThis(gF2Polynomial4, n);
            gF2Polynomial.shiftLeftAddThis(gF2Polynomial2, n);
        }
        gF2Polynomial.reduceN();
        return new GF2nPolynomialElement((GF2nPolynomialField)this.mField, gF2Polynomial);
    }

    public GF2nPolynomialElement invertSquare() throws ArithmeticException {
        if (this.isZero()) {
            throw new ArithmeticException();
        }
        int n = this.mField.getDegree() - 1;
        GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this);
        gF2nPolynomialElement.polynomial.expandN((this.mDegree << 1) + 32);
        gF2nPolynomialElement.polynomial.reduceN();
        int n2 = 1;
        for (int i = IntegerFunctions.floorLog(n) - 1; i >= 0; --i) {
            GF2nPolynomialElement gF2nPolynomialElement2 = new GF2nPolynomialElement(gF2nPolynomialElement);
            for (int j = 1; j <= n2; ++j) {
                gF2nPolynomialElement2.squareThisPreCalc();
            }
            gF2nPolynomialElement.multiplyThisBy(gF2nPolynomialElement2);
            n2 <<= 1;
            if ((n & bitMask[i]) == 0) continue;
            gF2nPolynomialElement.squareThisPreCalc();
            gF2nPolynomialElement.multiplyThisBy(this);
            ++n2;
        }
        gF2nPolynomialElement.squareThisPreCalc();
        return gF2nPolynomialElement;
    }

    public GF2nPolynomialElement invertMAIA() throws ArithmeticException {
        if (this.isZero()) {
            throw new ArithmeticException();
        }
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.mDegree, "ONE");
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.mDegree);
        GF2Polynomial gF2Polynomial3 = this.getGF2Polynomial();
        GF2Polynomial gF2Polynomial4 = this.mField.getFieldPolynomial();
        while (true) {
            if (!gF2Polynomial3.testBit(0)) {
                gF2Polynomial3.shiftRightThis();
                if (!gF2Polynomial.testBit(0)) {
                    gF2Polynomial.shiftRightThis();
                    continue;
                }
                gF2Polynomial.addToThis(this.mField.getFieldPolynomial());
                gF2Polynomial.shiftRightThis();
                continue;
            }
            if (gF2Polynomial3.isOne()) {
                return new GF2nPolynomialElement((GF2nPolynomialField)this.mField, gF2Polynomial);
            }
            gF2Polynomial3.reduceN();
            gF2Polynomial4.reduceN();
            if (gF2Polynomial3.getLength() < gF2Polynomial4.getLength()) {
                GF2Polynomial gF2Polynomial5 = gF2Polynomial3;
                gF2Polynomial3 = gF2Polynomial4;
                gF2Polynomial4 = gF2Polynomial5;
                gF2Polynomial5 = gF2Polynomial;
                gF2Polynomial = gF2Polynomial2;
                gF2Polynomial2 = gF2Polynomial5;
            }
            gF2Polynomial3.addToThis(gF2Polynomial4);
            gF2Polynomial.addToThis(gF2Polynomial2);
        }
    }

    public GF2nElement square() {
        return this.squarePreCalc();
    }

    public void squareThis() {
        this.squareThisPreCalc();
    }

    public GF2nPolynomialElement squareMatrix() {
        GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this);
        gF2nPolynomialElement.squareThisMatrix();
        gF2nPolynomialElement.reduceThis();
        return gF2nPolynomialElement;
    }

    public void squareThisMatrix() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.mDegree);
        for (int i = 0; i < this.mDegree; ++i) {
            if (!this.polynomial.vectorMult(((GF2nPolynomialField)this.mField).squaringMatrix[this.mDegree - i - 1])) continue;
            gF2Polynomial.setBit(i);
        }
        this.polynomial = gF2Polynomial;
    }

    public GF2nPolynomialElement squareBitwise() {
        GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this);
        gF2nPolynomialElement.squareThisBitwise();
        gF2nPolynomialElement.reduceThis();
        return gF2nPolynomialElement;
    }

    public void squareThisBitwise() {
        this.polynomial.squareThisBitwise();
        this.reduceThis();
    }

    public GF2nPolynomialElement squarePreCalc() {
        GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this);
        gF2nPolynomialElement.squareThisPreCalc();
        gF2nPolynomialElement.reduceThis();
        return gF2nPolynomialElement;
    }

    public void squareThisPreCalc() {
        this.polynomial.squareThisPreCalc();
        this.reduceThis();
    }

    public GF2nPolynomialElement power(int n) {
        if (n == 1) {
            return new GF2nPolynomialElement(this);
        }
        GF2nPolynomialElement gF2nPolynomialElement = GF2nPolynomialElement.ONE((GF2nPolynomialField)this.mField);
        if (n == 0) {
            return gF2nPolynomialElement;
        }
        GF2nPolynomialElement gF2nPolynomialElement2 = new GF2nPolynomialElement(this);
        gF2nPolynomialElement2.polynomial.expandN((gF2nPolynomialElement2.mDegree << 1) + 32);
        gF2nPolynomialElement2.polynomial.reduceN();
        for (int i = 0; i < this.mDegree; ++i) {
            if ((n & 1 << i) != 0) {
                gF2nPolynomialElement.multiplyThisBy(gF2nPolynomialElement2);
            }
            gF2nPolynomialElement2.square();
        }
        return gF2nPolynomialElement;
    }

    public GF2nElement squareRoot() {
        GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this);
        gF2nPolynomialElement.squareRootThis();
        return gF2nPolynomialElement;
    }

    public void squareRootThis() {
        this.polynomial.expandN((this.mDegree << 1) + 32);
        this.polynomial.reduceN();
        for (int i = 0; i < this.mField.getDegree() - 1; ++i) {
            this.squareThis();
        }
    }

    public GF2nElement solveQuadraticEquation() throws RuntimeException {
        GF2nPolynomialElement gF2nPolynomialElement;
        GF2nPolynomialElement gF2nPolynomialElement2;
        if (this.isZero()) {
            return GF2nPolynomialElement.ZERO((GF2nPolynomialField)this.mField);
        }
        if ((this.mDegree & 1) == 1) {
            return this.halfTrace();
        }
        do {
            GF2nPolynomialElement gF2nPolynomialElement3 = new GF2nPolynomialElement((GF2nPolynomialField)this.mField, new Random());
            gF2nPolynomialElement = GF2nPolynomialElement.ZERO((GF2nPolynomialField)this.mField);
            gF2nPolynomialElement2 = (GF2nPolynomialElement)gF2nPolynomialElement3.clone();
            for (int i = 1; i < this.mDegree; ++i) {
                gF2nPolynomialElement.squareThis();
                gF2nPolynomialElement2.squareThis();
                gF2nPolynomialElement.addToThis(gF2nPolynomialElement2.multiply(this));
                gF2nPolynomialElement2.addToThis(gF2nPolynomialElement3);
            }
        } while (gF2nPolynomialElement2.isZero());
        if (!this.equals(gF2nPolynomialElement.square().add(gF2nPolynomialElement))) {
            throw new RuntimeException();
        }
        return gF2nPolynomialElement;
    }

    public int trace() {
        GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this);
        for (int i = 1; i < this.mDegree; ++i) {
            gF2nPolynomialElement.squareThis();
            gF2nPolynomialElement.addToThis(this);
        }
        if (gF2nPolynomialElement.isOne()) {
            return 1;
        }
        return 0;
    }

    private GF2nPolynomialElement halfTrace() throws RuntimeException {
        if ((this.mDegree & 1) == 0) {
            throw new RuntimeException();
        }
        GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this);
        for (int i = 1; i <= this.mDegree - 1 >> 1; ++i) {
            gF2nPolynomialElement.squareThis();
            gF2nPolynomialElement.squareThis();
            gF2nPolynomialElement.addToThis(this);
        }
        return gF2nPolynomialElement;
    }

    private void reduceThis() {
        if (this.polynomial.getLength() > this.mDegree) {
            if (((GF2nPolynomialField)this.mField).isTrinomial()) {
                int n;
                try {
                    n = ((GF2nPolynomialField)this.mField).getTc();
                } catch (RuntimeException runtimeException) {
                    throw new RuntimeException("GF2nPolynomialElement.reduce: the field polynomial is not a trinomial");
                }
                if (this.mDegree - n <= 32 || this.polynomial.getLength() > this.mDegree << 1) {
                    this.reduceTrinomialBitwise(n);
                    return;
                }
                this.polynomial.reduceTrinomial(this.mDegree, n);
                return;
            }
            if (((GF2nPolynomialField)this.mField).isPentanomial()) {
                int[] nArray;
                try {
                    nArray = ((GF2nPolynomialField)this.mField).getPc();
                } catch (RuntimeException runtimeException) {
                    throw new RuntimeException("GF2nPolynomialElement.reduce: the field polynomial is not a pentanomial");
                }
                if (this.mDegree - nArray[2] <= 32 || this.polynomial.getLength() > this.mDegree << 1) {
                    this.reducePentanomialBitwise(nArray);
                    return;
                }
                this.polynomial.reducePentanomial(this.mDegree, nArray);
                return;
            }
            this.polynomial = this.polynomial.remainder(this.mField.getFieldPolynomial());
            this.polynomial.expandN(this.mDegree);
            return;
        }
        if (this.polynomial.getLength() < this.mDegree) {
            this.polynomial.expandN(this.mDegree);
        }
    }

    private void reduceTrinomialBitwise(int n) {
        int n2 = this.mDegree - n;
        for (int i = this.polynomial.getLength() - 1; i >= this.mDegree; --i) {
            if (!this.polynomial.testBit(i)) continue;
            this.polynomial.xorBit(i);
            this.polynomial.xorBit(i - n2);
            this.polynomial.xorBit(i - this.mDegree);
        }
        this.polynomial.reduceN();
        this.polynomial.expandN(this.mDegree);
    }

    private void reducePentanomialBitwise(int[] nArray) {
        int n = this.mDegree - nArray[2];
        int n2 = this.mDegree - nArray[1];
        int n3 = this.mDegree - nArray[0];
        for (int i = this.polynomial.getLength() - 1; i >= this.mDegree; --i) {
            if (!this.polynomial.testBit(i)) continue;
            this.polynomial.xorBit(i);
            this.polynomial.xorBit(i - n);
            this.polynomial.xorBit(i - n2);
            this.polynomial.xorBit(i - n3);
            this.polynomial.xorBit(i - this.mDegree);
        }
        this.polynomial.reduceN();
        this.polynomial.expandN(this.mDegree);
    }

    public String toString() {
        return this.polynomial.toString(16);
    }

    public String toString(int n) {
        return this.polynomial.toString(n);
    }

    public byte[] toByteArray() {
        return this.polynomial.toByteArray();
    }

    public BigInteger toFlexiBigInt() {
        return this.polynomial.toFlexiBigInt();
    }
}

