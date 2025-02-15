/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
import org.bouncycastle.pqc.math.linearalgebra.GF2Vector;
import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public final class GoppaCode {
    private GoppaCode() {
    }

    public static GF2Matrix createCanonicalCheckMatrix(GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM) {
        int n;
        int n2;
        int n3;
        int n4 = gF2mField.getDegree();
        int n5 = 1 << n4;
        int n6 = polynomialGF2mSmallM.getDegree();
        int[][] nArray = new int[n6][n5];
        int[][] nArray2 = new int[n6][n5];
        for (n3 = 0; n3 < n5; ++n3) {
            nArray2[0][n3] = gF2mField.inverse(polynomialGF2mSmallM.evaluateAt(n3));
        }
        for (n3 = 1; n3 < n6; ++n3) {
            for (n2 = 0; n2 < n5; ++n2) {
                nArray2[n3][n2] = gF2mField.mult(nArray2[n3 - 1][n2], n2);
            }
        }
        for (n3 = 0; n3 < n6; ++n3) {
            for (n2 = 0; n2 < n5; ++n2) {
                for (n = 0; n <= n3; ++n) {
                    nArray[n3][n2] = gF2mField.add(nArray[n3][n2], gF2mField.mult(nArray2[n][n2], polynomialGF2mSmallM.getCoefficient(n6 + n - n3)));
                }
            }
        }
        int[][] nArray3 = new int[n6 * n4][n5 + 31 >>> 5];
        for (n2 = 0; n2 < n5; ++n2) {
            n = n2 >>> 5;
            int n7 = 1 << (n2 & 0x1F);
            for (int i = 0; i < n6; ++i) {
                int n8 = nArray[i][n2];
                for (int j = 0; j < n4; ++j) {
                    int n9 = n8 >>> j & 1;
                    if (n9 == 0) continue;
                    int n10 = (i + 1) * n4 - j - 1;
                    int[] nArray4 = nArray3[n10];
                    int n11 = n;
                    nArray4[n11] = nArray4[n11] ^ n7;
                }
            }
        }
        return new GF2Matrix(n5, nArray3);
    }

    public static MaMaPe computeSystematicForm(GF2Matrix gF2Matrix, SecureRandom secureRandom) {
        GF2Matrix gF2Matrix2;
        GF2Matrix gF2Matrix3;
        Permutation permutation;
        int n = gF2Matrix.getNumColumns();
        GF2Matrix gF2Matrix4 = null;
        boolean bl = false;
        do {
            permutation = new Permutation(n, secureRandom);
            gF2Matrix3 = (GF2Matrix)gF2Matrix.rightMultiply(permutation);
            gF2Matrix2 = gF2Matrix3.getLeftSubMatrix();
            try {
                bl = true;
                gF2Matrix4 = (GF2Matrix)gF2Matrix2.computeInverse();
            } catch (ArithmeticException arithmeticException) {
                bl = false;
            }
        } while (!bl);
        GF2Matrix gF2Matrix5 = (GF2Matrix)gF2Matrix4.rightMultiply(gF2Matrix3);
        GF2Matrix gF2Matrix6 = gF2Matrix5.getRightSubMatrix();
        return new MaMaPe(gF2Matrix2, gF2Matrix6, permutation);
    }

    public static GF2Vector syndromeDecode(GF2Vector gF2Vector, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, PolynomialGF2mSmallM[] polynomialGF2mSmallMArray) {
        int n = 1 << gF2mField.getDegree();
        GF2Vector gF2Vector2 = new GF2Vector(n);
        if (!gF2Vector.isZero()) {
            PolynomialGF2mSmallM polynomialGF2mSmallM2 = new PolynomialGF2mSmallM(gF2Vector.toExtensionFieldVector(gF2mField));
            PolynomialGF2mSmallM polynomialGF2mSmallM3 = polynomialGF2mSmallM2.modInverse(polynomialGF2mSmallM);
            PolynomialGF2mSmallM polynomialGF2mSmallM4 = polynomialGF2mSmallM3.addMonomial(1);
            polynomialGF2mSmallM4 = polynomialGF2mSmallM4.modSquareRootMatrix(polynomialGF2mSmallMArray);
            PolynomialGF2mSmallM[] polynomialGF2mSmallMArray2 = polynomialGF2mSmallM4.modPolynomialToFracton(polynomialGF2mSmallM);
            PolynomialGF2mSmallM polynomialGF2mSmallM5 = polynomialGF2mSmallMArray2[0].multiply(polynomialGF2mSmallMArray2[0]);
            PolynomialGF2mSmallM polynomialGF2mSmallM6 = polynomialGF2mSmallMArray2[1].multiply(polynomialGF2mSmallMArray2[1]);
            PolynomialGF2mSmallM polynomialGF2mSmallM7 = polynomialGF2mSmallM6.multWithMonomial(1);
            PolynomialGF2mSmallM polynomialGF2mSmallM8 = polynomialGF2mSmallM5.add(polynomialGF2mSmallM7);
            int n2 = polynomialGF2mSmallM8.getHeadCoefficient();
            int n3 = gF2mField.inverse(n2);
            PolynomialGF2mSmallM polynomialGF2mSmallM9 = polynomialGF2mSmallM8.multWithElement(n3);
            for (int i = 0; i < n; ++i) {
                int n4 = polynomialGF2mSmallM9.evaluateAt(i);
                if (n4 != 0) continue;
                gF2Vector2.setBit(i);
            }
        }
        return gF2Vector2;
    }

    public static class MaMaPe {
        private GF2Matrix s;
        private GF2Matrix h;
        private Permutation p;

        public MaMaPe(GF2Matrix gF2Matrix, GF2Matrix gF2Matrix2, Permutation permutation) {
            this.s = gF2Matrix;
            this.h = gF2Matrix2;
            this.p = permutation;
        }

        public GF2Matrix getFirstMatrix() {
            return this.s;
        }

        public GF2Matrix getSecondMatrix() {
            return this.h;
        }

        public Permutation getPermutation() {
            return this.p;
        }
    }

    public static class MatrixSet {
        private GF2Matrix g;
        private int[] setJ;

        public MatrixSet(GF2Matrix gF2Matrix, int[] nArray) {
            this.g = gF2Matrix;
            this.setJ = nArray;
        }

        public GF2Matrix getG() {
            return this.g;
        }

        public int[] getSetJ() {
            return this.setJ;
        }
    }
}

