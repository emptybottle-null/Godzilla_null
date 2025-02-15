/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.pqc.math.linearalgebra.GF2Polynomial;
import org.bouncycastle.pqc.math.linearalgebra.GF2nElement;
import org.bouncycastle.pqc.math.linearalgebra.GF2nField;
import org.bouncycastle.pqc.math.linearalgebra.GF2nONBElement;
import org.bouncycastle.pqc.math.linearalgebra.GF2nONBField;
import org.bouncycastle.pqc.math.linearalgebra.GF2nPolynomial;
import org.bouncycastle.pqc.math.linearalgebra.GF2nPolynomialElement;

public class GF2nPolynomialField
extends GF2nField {
    GF2Polynomial[] squaringMatrix;
    private boolean isTrinomial = false;
    private boolean isPentanomial = false;
    private int tc;
    private int[] pc = new int[3];

    public GF2nPolynomialField(int n, SecureRandom secureRandom) {
        super(secureRandom);
        if (n < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.mDegree = n;
        this.computeFieldPolynomial();
        this.computeSquaringMatrix();
        this.fields = new Vector();
        this.matrices = new Vector();
    }

    public GF2nPolynomialField(int n, SecureRandom secureRandom, boolean bl) {
        super(secureRandom);
        if (n < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.mDegree = n;
        if (bl) {
            this.computeFieldPolynomial();
        } else {
            this.computeFieldPolynomial2();
        }
        this.computeSquaringMatrix();
        this.fields = new Vector();
        this.matrices = new Vector();
    }

    public GF2nPolynomialField(int n, SecureRandom secureRandom, GF2Polynomial gF2Polynomial) throws RuntimeException {
        super(secureRandom);
        if (n < 3) {
            throw new IllegalArgumentException("degree must be at least 3");
        }
        if (gF2Polynomial.getLength() != n + 1) {
            throw new RuntimeException();
        }
        if (!gF2Polynomial.isIrreducible()) {
            throw new RuntimeException();
        }
        this.mDegree = n;
        this.fieldPolynomial = gF2Polynomial;
        this.computeSquaringMatrix();
        int n2 = 2;
        for (int i = 1; i < this.fieldPolynomial.getLength() - 1; ++i) {
            if (!this.fieldPolynomial.testBit(i)) continue;
            if (++n2 == 3) {
                this.tc = i;
            }
            if (n2 > 5) continue;
            this.pc[n2 - 3] = i;
        }
        if (n2 == 3) {
            this.isTrinomial = true;
        }
        if (n2 == 5) {
            this.isPentanomial = true;
        }
        this.fields = new Vector();
        this.matrices = new Vector();
    }

    public boolean isTrinomial() {
        return this.isTrinomial;
    }

    public boolean isPentanomial() {
        return this.isPentanomial;
    }

    public int getTc() throws RuntimeException {
        if (!this.isTrinomial) {
            throw new RuntimeException();
        }
        return this.tc;
    }

    public int[] getPc() throws RuntimeException {
        if (!this.isPentanomial) {
            throw new RuntimeException();
        }
        int[] nArray = new int[3];
        System.arraycopy(this.pc, 0, nArray, 0, 3);
        return nArray;
    }

    public GF2Polynomial getSquaringVector(int n) {
        return new GF2Polynomial(this.squaringMatrix[n]);
    }

    protected GF2nElement getRandomRoot(GF2Polynomial gF2Polynomial) {
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(gF2Polynomial, this);
        int n = gF2nPolynomial.getDegree();
        while (n > 1) {
            GF2nPolynomial gF2nPolynomial2;
            int n2;
            do {
                GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this, this.random);
                GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(2, GF2nPolynomialElement.ZERO(this));
                gF2nPolynomial3.set(1, gF2nPolynomialElement);
                GF2nPolynomial gF2nPolynomial4 = new GF2nPolynomial(gF2nPolynomial3);
                for (int i = 1; i <= this.mDegree - 1; ++i) {
                    gF2nPolynomial4 = gF2nPolynomial4.multiplyAndReduce(gF2nPolynomial4, gF2nPolynomial);
                    gF2nPolynomial4 = gF2nPolynomial4.add(gF2nPolynomial3);
                }
                gF2nPolynomial2 = gF2nPolynomial4.gcd(gF2nPolynomial);
                n2 = gF2nPolynomial2.getDegree();
                n = gF2nPolynomial.getDegree();
            } while (n2 == 0 || n2 == n);
            gF2nPolynomial = n2 << 1 > n ? gF2nPolynomial.quotient(gF2nPolynomial2) : new GF2nPolynomial(gF2nPolynomial2);
            n = gF2nPolynomial.getDegree();
        }
        return gF2nPolynomial.at(0);
    }

    protected void computeCOBMatrix(GF2nField gF2nField) {
        GF2nElement[] gF2nElementArray;
        GF2nElement gF2nElement;
        int n;
        if (this.mDegree != gF2nField.mDegree) {
            throw new IllegalArgumentException("GF2nPolynomialField.computeCOBMatrix: B1 has a different degree and thus cannot be coverted to!");
        }
        if (gF2nField instanceof GF2nONBField) {
            gF2nField.computeCOBMatrix(this);
            return;
        }
        GF2Polynomial[] gF2PolynomialArray = new GF2Polynomial[this.mDegree];
        for (n = 0; n < this.mDegree; ++n) {
            gF2PolynomialArray[n] = new GF2Polynomial(this.mDegree);
        }
        while ((gF2nElement = gF2nField.getRandomRoot(this.fieldPolynomial)).isZero()) {
        }
        if (gF2nElement instanceof GF2nONBElement) {
            gF2nElementArray = new GF2nONBElement[this.mDegree];
            gF2nElementArray[this.mDegree - 1] = GF2nONBElement.ONE((GF2nONBField)gF2nField);
        } else {
            gF2nElementArray = new GF2nPolynomialElement[this.mDegree];
            gF2nElementArray[this.mDegree - 1] = GF2nPolynomialElement.ONE((GF2nPolynomialField)gF2nField);
        }
        gF2nElementArray[this.mDegree - 2] = gF2nElement;
        for (n = this.mDegree - 3; n >= 0; --n) {
            gF2nElementArray[n] = (GF2nElement)gF2nElementArray[n + 1].multiply(gF2nElement);
        }
        if (gF2nField instanceof GF2nONBField) {
            for (n = 0; n < this.mDegree; ++n) {
                for (int i = 0; i < this.mDegree; ++i) {
                    if (!gF2nElementArray[n].testBit(this.mDegree - i - 1)) continue;
                    gF2PolynomialArray[this.mDegree - i - 1].setBit(this.mDegree - n - 1);
                }
            }
        } else {
            for (n = 0; n < this.mDegree; ++n) {
                for (int i = 0; i < this.mDegree; ++i) {
                    if (!gF2nElementArray[n].testBit(i)) continue;
                    gF2PolynomialArray[this.mDegree - i - 1].setBit(this.mDegree - n - 1);
                }
            }
        }
        this.fields.addElement(gF2nField);
        this.matrices.addElement(gF2PolynomialArray);
        gF2nField.fields.addElement(this);
        gF2nField.matrices.addElement(this.invertMatrix(gF2PolynomialArray));
    }

    private void computeSquaringMatrix() {
        int n;
        GF2Polynomial[] gF2PolynomialArray = new GF2Polynomial[this.mDegree - 1];
        this.squaringMatrix = new GF2Polynomial[this.mDegree];
        for (n = 0; n < this.squaringMatrix.length; ++n) {
            this.squaringMatrix[n] = new GF2Polynomial(this.mDegree, "ZERO");
        }
        for (n = 0; n < this.mDegree - 1; ++n) {
            gF2PolynomialArray[n] = new GF2Polynomial(1, "ONE").shiftLeft(this.mDegree + n).remainder(this.fieldPolynomial);
        }
        for (n = 1; n <= Math.abs(this.mDegree >> 1); ++n) {
            for (int i = 1; i <= this.mDegree; ++i) {
                if (!gF2PolynomialArray[this.mDegree - (n << 1)].testBit(this.mDegree - i)) continue;
                this.squaringMatrix[i - 1].setBit(this.mDegree - n);
            }
        }
        for (n = Math.abs(this.mDegree >> 1) + 1; n <= this.mDegree; ++n) {
            this.squaringMatrix[(n << 1) - this.mDegree - 1].setBit(this.mDegree - n);
        }
    }

    protected void computeFieldPolynomial() {
        if (this.testTrinomials()) {
            return;
        }
        if (this.testPentanomials()) {
            return;
        }
        this.testRandom();
    }

    protected void computeFieldPolynomial2() {
        if (this.testTrinomials()) {
            return;
        }
        if (this.testPentanomials()) {
            return;
        }
        this.testRandom();
    }

    private boolean testTrinomials() {
        boolean bl = false;
        int n = 0;
        this.fieldPolynomial = new GF2Polynomial(this.mDegree + 1);
        this.fieldPolynomial.setBit(0);
        this.fieldPolynomial.setBit(this.mDegree);
        for (int i = 1; i < this.mDegree && !bl; ++i) {
            this.fieldPolynomial.setBit(i);
            bl = this.fieldPolynomial.isIrreducible();
            ++n;
            if (bl) {
                this.isTrinomial = true;
                this.tc = i;
                return bl;
            }
            this.fieldPolynomial.resetBit(i);
            bl = this.fieldPolynomial.isIrreducible();
        }
        return bl;
    }

    private boolean testPentanomials() {
        boolean bl = false;
        int n = 0;
        this.fieldPolynomial = new GF2Polynomial(this.mDegree + 1);
        this.fieldPolynomial.setBit(0);
        this.fieldPolynomial.setBit(this.mDegree);
        for (int i = 1; i <= this.mDegree - 3 && !bl; ++i) {
            this.fieldPolynomial.setBit(i);
            for (int j = i + 1; j <= this.mDegree - 2 && !bl; ++j) {
                this.fieldPolynomial.setBit(j);
                for (int k = j + 1; k <= this.mDegree - 1 && !bl; ++k) {
                    this.fieldPolynomial.setBit(k);
                    if ((this.mDegree & 1) != 0 | (i & 1) != 0 | (j & 1) != 0 | (k & 1) != 0) {
                        bl = this.fieldPolynomial.isIrreducible();
                        ++n;
                        if (bl) {
                            this.isPentanomial = true;
                            this.pc[0] = i;
                            this.pc[1] = j;
                            this.pc[2] = k;
                            return bl;
                        }
                    }
                    this.fieldPolynomial.resetBit(k);
                }
                this.fieldPolynomial.resetBit(j);
            }
            this.fieldPolynomial.resetBit(i);
        }
        return bl;
    }

    private boolean testRandom() {
        boolean bl = false;
        this.fieldPolynomial = new GF2Polynomial(this.mDegree + 1);
        int n = 0;
        while (!bl) {
            ++n;
            this.fieldPolynomial.randomize();
            this.fieldPolynomial.setBit(this.mDegree);
            this.fieldPolynomial.setBit(0);
            if (!this.fieldPolynomial.isIrreducible()) continue;
            bl = true;
            return bl;
        }
        return bl;
    }
}

