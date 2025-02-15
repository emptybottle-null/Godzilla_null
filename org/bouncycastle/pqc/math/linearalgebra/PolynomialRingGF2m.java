/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class PolynomialRingGF2m {
    private GF2mField field;
    private PolynomialGF2mSmallM p;
    protected PolynomialGF2mSmallM[] sqMatrix;
    protected PolynomialGF2mSmallM[] sqRootMatrix;

    public PolynomialRingGF2m(GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM) {
        this.field = gF2mField;
        this.p = polynomialGF2mSmallM;
        this.computeSquaringMatrix();
        this.computeSquareRootMatrix();
    }

    public PolynomialGF2mSmallM[] getSquaringMatrix() {
        return this.sqMatrix;
    }

    public PolynomialGF2mSmallM[] getSquareRootMatrix() {
        return this.sqRootMatrix;
    }

    private void computeSquaringMatrix() {
        int[] nArray;
        int n;
        int n2 = this.p.getDegree();
        this.sqMatrix = new PolynomialGF2mSmallM[n2];
        for (n = 0; n < n2 >> 1; ++n) {
            nArray = new int[(n << 1) + 1];
            nArray[n << 1] = 1;
            this.sqMatrix[n] = new PolynomialGF2mSmallM(this.field, nArray);
        }
        for (n = n2 >> 1; n < n2; ++n) {
            nArray = new int[(n << 1) + 1];
            nArray[n << 1] = 1;
            PolynomialGF2mSmallM polynomialGF2mSmallM = new PolynomialGF2mSmallM(this.field, nArray);
            this.sqMatrix[n] = polynomialGF2mSmallM.mod(this.p);
        }
    }

    private void computeSquareRootMatrix() {
        int n;
        int n2 = this.p.getDegree();
        PolynomialGF2mSmallM[] polynomialGF2mSmallMArray = new PolynomialGF2mSmallM[n2];
        for (n = n2 - 1; n >= 0; --n) {
            polynomialGF2mSmallMArray[n] = new PolynomialGF2mSmallM(this.sqMatrix[n]);
        }
        this.sqRootMatrix = new PolynomialGF2mSmallM[n2];
        for (n = n2 - 1; n >= 0; --n) {
            this.sqRootMatrix[n] = new PolynomialGF2mSmallM(this.field, n);
        }
        for (n = 0; n < n2; ++n) {
            int n3;
            int n4;
            if (polynomialGF2mSmallMArray[n].getCoefficient(n) == 0) {
                n4 = 0;
                for (n3 = n + 1; n3 < n2; ++n3) {
                    if (polynomialGF2mSmallMArray[n3].getCoefficient(n) == 0) continue;
                    n4 = 1;
                    PolynomialRingGF2m.swapColumns(polynomialGF2mSmallMArray, n, n3);
                    PolynomialRingGF2m.swapColumns(this.sqRootMatrix, n, n3);
                    n3 = n2;
                }
                if (n4 == 0) {
                    throw new ArithmeticException("Squaring matrix is not invertible.");
                }
            }
            n4 = polynomialGF2mSmallMArray[n].getCoefficient(n);
            n3 = this.field.inverse(n4);
            polynomialGF2mSmallMArray[n].multThisWithElement(n3);
            this.sqRootMatrix[n].multThisWithElement(n3);
            for (int i = 0; i < n2; ++i) {
                if (i == n || (n4 = polynomialGF2mSmallMArray[i].getCoefficient(n)) == 0) continue;
                PolynomialGF2mSmallM polynomialGF2mSmallM = polynomialGF2mSmallMArray[n].multWithElement(n4);
                PolynomialGF2mSmallM polynomialGF2mSmallM2 = this.sqRootMatrix[n].multWithElement(n4);
                polynomialGF2mSmallMArray[i].addToThis(polynomialGF2mSmallM);
                this.sqRootMatrix[i].addToThis(polynomialGF2mSmallM2);
            }
        }
    }

    private static void swapColumns(PolynomialGF2mSmallM[] polynomialGF2mSmallMArray, int n, int n2) {
        PolynomialGF2mSmallM polynomialGF2mSmallM = polynomialGF2mSmallMArray[n];
        polynomialGF2mSmallMArray[n] = polynomialGF2mSmallMArray[n2];
        polynomialGF2mSmallMArray[n2] = polynomialGF2mSmallM;
    }
}

