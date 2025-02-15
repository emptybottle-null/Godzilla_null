/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Vector;
import org.bouncycastle.pqc.math.linearalgebra.GF2Polynomial;
import org.bouncycastle.pqc.math.linearalgebra.GF2nElement;
import org.bouncycastle.pqc.math.linearalgebra.GF2nField;
import org.bouncycastle.pqc.math.linearalgebra.GF2nONBElement;
import org.bouncycastle.pqc.math.linearalgebra.GF2nPolynomial;
import org.bouncycastle.pqc.math.linearalgebra.GF2nPolynomialElement;
import org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions;

public class GF2nONBField
extends GF2nField {
    private static final int MAXLONG = 64;
    private int mLength;
    private int mBit;
    private int mType;
    int[][] mMult;

    public GF2nONBField(int n, SecureRandom secureRandom) throws RuntimeException {
        super(secureRandom);
        if (n < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.mDegree = n;
        this.mLength = this.mDegree / 64;
        this.mBit = this.mDegree & 0x3F;
        if (this.mBit == 0) {
            this.mBit = 64;
        } else {
            ++this.mLength;
        }
        this.computeType();
        if (this.mType < 3) {
            this.mMult = new int[this.mDegree][2];
            for (int i = 0; i < this.mDegree; ++i) {
                this.mMult[i][0] = -1;
                this.mMult[i][1] = -1;
            }
        } else {
            throw new RuntimeException("\nThe type of this field is " + this.mType);
        }
        this.computeMultMatrix();
        this.computeFieldPolynomial();
        this.fields = new Vector();
        this.matrices = new Vector();
    }

    int getONBLength() {
        return this.mLength;
    }

    int getONBBit() {
        return this.mBit;
    }

    protected GF2nElement getRandomRoot(GF2Polynomial gF2Polynomial) {
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(gF2Polynomial, this);
        int n = gF2nPolynomial.getDegree();
        while (n > 1) {
            GF2nPolynomial gF2nPolynomial2;
            int n2;
            do {
                GF2nONBElement gF2nONBElement = new GF2nONBElement(this, this.random);
                GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(2, GF2nONBElement.ZERO(this));
                gF2nPolynomial3.set(1, gF2nONBElement);
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
        GF2nElement gF2nElement;
        int n;
        if (this.mDegree != gF2nField.mDegree) {
            throw new IllegalArgumentException("GF2nField.computeCOBMatrix: B1 has a different degree and thus cannot be coverted to!");
        }
        GF2Polynomial[] gF2PolynomialArray = new GF2Polynomial[this.mDegree];
        for (n = 0; n < this.mDegree; ++n) {
            gF2PolynomialArray[n] = new GF2Polynomial(this.mDegree);
        }
        while ((gF2nElement = gF2nField.getRandomRoot(this.fieldPolynomial)).isZero()) {
        }
        GF2nPolynomialElement[] gF2nPolynomialElementArray = new GF2nPolynomialElement[this.mDegree];
        gF2nPolynomialElementArray[0] = (GF2nElement)gF2nElement.clone();
        for (n = 1; n < this.mDegree; ++n) {
            gF2nPolynomialElementArray[n] = ((GF2nElement)gF2nPolynomialElementArray[n - 1]).square();
        }
        for (n = 0; n < this.mDegree; ++n) {
            for (int i = 0; i < this.mDegree; ++i) {
                if (!((GF2nElement)gF2nPolynomialElementArray[n]).testBit(i)) continue;
                gF2PolynomialArray[this.mDegree - i - 1].setBit(this.mDegree - n - 1);
            }
        }
        this.fields.addElement(gF2nField);
        this.matrices.addElement(gF2PolynomialArray);
        gF2nField.fields.addElement(this);
        gF2nField.matrices.addElement(this.invertMatrix(gF2PolynomialArray));
    }

    protected void computeFieldPolynomial() {
        if (this.mType == 1) {
            this.fieldPolynomial = new GF2Polynomial(this.mDegree + 1, "ALL");
        } else if (this.mType == 2) {
            GF2Polynomial gF2Polynomial = new GF2Polynomial(this.mDegree + 1, "ONE");
            GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.mDegree + 1, "X");
            gF2Polynomial2.addToThis(gF2Polynomial);
            for (int i = 1; i < this.mDegree; ++i) {
                GF2Polynomial gF2Polynomial3 = gF2Polynomial;
                gF2Polynomial = gF2Polynomial2;
                gF2Polynomial2 = gF2Polynomial.shiftLeft();
                gF2Polynomial2.addToThis(gF2Polynomial3);
            }
            this.fieldPolynomial = gF2Polynomial2;
        }
    }

    int[][] invMatrix(int[][] nArray) {
        int n;
        int[][] nArray2 = new int[this.mDegree][this.mDegree];
        nArray2 = nArray;
        int[][] nArray3 = new int[this.mDegree][this.mDegree];
        for (n = 0; n < this.mDegree; ++n) {
            nArray3[n][n] = 1;
        }
        for (n = 0; n < this.mDegree; ++n) {
            for (int i = n; i < this.mDegree; ++i) {
                nArray2[this.mDegree - 1 - n][i] = nArray2[n][n];
            }
        }
        return null;
    }

    private void computeType() throws RuntimeException {
        if ((this.mDegree & 7) == 0) {
            throw new RuntimeException("The extension degree is divisible by 8!");
        }
        int n = 0;
        int n2 = 0;
        this.mType = 1;
        int n3 = 0;
        while (n3 != 1) {
            n = this.mType * this.mDegree + 1;
            if (IntegerFunctions.isPrime(n)) {
                n2 = IntegerFunctions.order(2, n);
                n3 = IntegerFunctions.gcd(this.mType * this.mDegree / n2, this.mDegree);
            }
            ++this.mType;
        }
        --this.mType;
        if (this.mType == 1 && IntegerFunctions.isPrime(n = (this.mDegree << 1) + 1) && (n3 = IntegerFunctions.gcd((this.mDegree << 1) / (n2 = IntegerFunctions.order(2, n)), this.mDegree)) == 1) {
            ++this.mType;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void computeMultMatrix() {
        int n;
        int n2;
        if ((this.mType & 7) == 0) throw new RuntimeException("bisher nur fuer Gausssche Normalbasen implementiert");
        int n3 = this.mType * this.mDegree + 1;
        int[] nArray = new int[n3];
        int n4 = this.mType == 1 ? 1 : (this.mType == 2 ? n3 - 1 : this.elementOfOrder(this.mType, n3));
        int n5 = 1;
        for (n2 = 0; n2 < this.mType; ++n2) {
            int n6 = n5;
            for (n = 0; n < this.mDegree; ++n) {
                nArray[n6] = n;
                if ((n6 = (n6 << 1) % n3) >= 0) continue;
                n6 += n3;
            }
            if ((n5 = n4 * n5 % n3) >= 0) continue;
            n5 += n3;
        }
        if (this.mType == 1) {
            for (n2 = 1; n2 < n3 - 1; ++n2) {
                if (this.mMult[nArray[n2 + 1]][0] == -1) {
                    this.mMult[nArray[n2 + 1]][0] = nArray[n3 - n2];
                    continue;
                }
                this.mMult[nArray[n2 + 1]][1] = nArray[n3 - n2];
            }
            n2 = this.mDegree >> 1;
            for (n = 1; n <= n2; ++n) {
                if (this.mMult[n - 1][0] == -1) {
                    this.mMult[n - 1][0] = n2 + n - 1;
                } else {
                    this.mMult[n - 1][1] = n2 + n - 1;
                }
                if (this.mMult[n2 + n - 1][0] == -1) {
                    this.mMult[n2 + n - 1][0] = n - 1;
                    continue;
                }
                this.mMult[n2 + n - 1][1] = n - 1;
            }
            return;
        } else {
            if (this.mType != 2) throw new RuntimeException("only type 1 or type 2 implemented");
            for (n2 = 1; n2 < n3 - 1; ++n2) {
                if (this.mMult[nArray[n2 + 1]][0] == -1) {
                    this.mMult[nArray[n2 + 1]][0] = nArray[n3 - n2];
                    continue;
                }
                this.mMult[nArray[n2 + 1]][1] = nArray[n3 - n2];
            }
        }
    }

    private int elementOfOrder(int n, int n2) {
        Random random = new Random();
        int n3 = 0;
        while (n3 == 0) {
            n3 = random.nextInt();
            if ((n3 %= n2 - 1) >= 0) continue;
            n3 += n2 - 1;
        }
        int n4 = IntegerFunctions.order(n3, n2);
        while (n4 % n != 0 || n4 == 0) {
            while (n3 == 0) {
                n3 = random.nextInt();
                if ((n3 %= n2 - 1) >= 0) continue;
                n3 += n2 - 1;
            }
            n4 = IntegerFunctions.order(n3, n2);
        }
        int n5 = n3;
        n4 = n / n4;
        for (int i = 2; i <= n4; ++i) {
            n5 *= n3;
        }
        return n5;
    }
}

