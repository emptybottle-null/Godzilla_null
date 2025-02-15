/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import org.bouncycastle.pqc.math.linearalgebra.GF2Vector;
import org.bouncycastle.pqc.math.linearalgebra.IntUtils;
import org.bouncycastle.pqc.math.linearalgebra.LittleEndianConversions;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.Vector;

public class GF2Matrix
extends Matrix {
    private int[][] matrix;
    private int length;

    public GF2Matrix(byte[] byArray) {
        if (byArray.length < 9) {
            throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
        }
        this.numRows = LittleEndianConversions.OS2IP(byArray, 0);
        this.numColumns = LittleEndianConversions.OS2IP(byArray, 4);
        int n = (this.numColumns + 7 >>> 3) * this.numRows;
        if (this.numRows <= 0 || n != byArray.length - 8) {
            throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
        }
        this.length = this.numColumns + 31 >>> 5;
        this.matrix = new int[this.numRows][this.length];
        int n2 = this.numColumns >> 5;
        int n3 = this.numColumns & 0x1F;
        int n4 = 8;
        for (int i = 0; i < this.numRows; ++i) {
            int n5 = 0;
            while (n5 < n2) {
                this.matrix[i][n5] = LittleEndianConversions.OS2IP(byArray, n4);
                ++n5;
                n4 += 4;
            }
            for (n5 = 0; n5 < n3; n5 += 8) {
                int[] nArray = this.matrix[i];
                int n6 = n2;
                nArray[n6] = nArray[n6] ^ (byArray[n4++] & 0xFF) << n5;
            }
        }
    }

    public GF2Matrix(int n, int[][] nArray) {
        if (nArray[0].length != n + 31 >> 5) {
            throw new ArithmeticException("Int array does not match given number of columns.");
        }
        this.numColumns = n;
        this.numRows = nArray.length;
        this.length = nArray[0].length;
        int n2 = n & 0x1F;
        int n3 = n2 == 0 ? -1 : (1 << n2) - 1;
        for (int i = 0; i < this.numRows; ++i) {
            int[] nArray2 = nArray[i];
            int n4 = this.length - 1;
            nArray2[n4] = nArray2[n4] & n3;
        }
        this.matrix = nArray;
    }

    public GF2Matrix(int n, char c) {
        this(n, c, new SecureRandom());
    }

    public GF2Matrix(int n, char c, SecureRandom secureRandom) {
        if (n <= 0) {
            throw new ArithmeticException("Size of matrix is non-positive.");
        }
        switch (c) {
            case 'Z': {
                this.assignZeroMatrix(n, n);
                break;
            }
            case 'I': {
                this.assignUnitMatrix(n);
                break;
            }
            case 'L': {
                this.assignRandomLowerTriangularMatrix(n, secureRandom);
                break;
            }
            case 'U': {
                this.assignRandomUpperTriangularMatrix(n, secureRandom);
                break;
            }
            case 'R': {
                this.assignRandomRegularMatrix(n, secureRandom);
                break;
            }
            default: {
                throw new ArithmeticException("Unknown matrix type.");
            }
        }
    }

    public GF2Matrix(GF2Matrix gF2Matrix) {
        this.numColumns = gF2Matrix.getNumColumns();
        this.numRows = gF2Matrix.getNumRows();
        this.length = gF2Matrix.length;
        this.matrix = new int[gF2Matrix.matrix.length][];
        for (int i = 0; i < this.matrix.length; ++i) {
            this.matrix[i] = IntUtils.clone(gF2Matrix.matrix[i]);
        }
    }

    private GF2Matrix(int n, int n2) {
        if (n2 <= 0 || n <= 0) {
            throw new ArithmeticException("size of matrix is non-positive");
        }
        this.assignZeroMatrix(n, n2);
    }

    private void assignZeroMatrix(int n, int n2) {
        this.numRows = n;
        this.numColumns = n2;
        this.length = n2 + 31 >>> 5;
        this.matrix = new int[this.numRows][this.length];
        for (int i = 0; i < this.numRows; ++i) {
            for (int j = 0; j < this.length; ++j) {
                this.matrix[i][j] = 0;
            }
        }
    }

    private void assignUnitMatrix(int n) {
        int n2;
        int n3;
        this.numRows = n;
        this.numColumns = n;
        this.length = n + 31 >>> 5;
        this.matrix = new int[this.numRows][this.length];
        for (n3 = 0; n3 < this.numRows; ++n3) {
            for (n2 = 0; n2 < this.length; ++n2) {
                this.matrix[n3][n2] = 0;
            }
        }
        for (n3 = 0; n3 < this.numRows; ++n3) {
            n2 = n3 & 0x1F;
            this.matrix[n3][n3 >>> 5] = 1 << n2;
        }
    }

    private void assignRandomLowerTriangularMatrix(int n, SecureRandom secureRandom) {
        this.numRows = n;
        this.numColumns = n;
        this.length = n + 31 >>> 5;
        this.matrix = new int[this.numRows][this.length];
        for (int i = 0; i < this.numRows; ++i) {
            int n2;
            int n3 = i >>> 5;
            int n4 = i & 0x1F;
            int n5 = 31 - n4;
            n4 = 1 << n4;
            for (n2 = 0; n2 < n3; ++n2) {
                this.matrix[i][n2] = secureRandom.nextInt();
            }
            this.matrix[i][n3] = secureRandom.nextInt() >>> n5 | n4;
            for (n2 = n3 + 1; n2 < this.length; ++n2) {
                this.matrix[i][n2] = 0;
            }
        }
    }

    private void assignRandomUpperTriangularMatrix(int n, SecureRandom secureRandom) {
        this.numRows = n;
        this.numColumns = n;
        this.length = n + 31 >>> 5;
        this.matrix = new int[this.numRows][this.length];
        int n2 = n & 0x1F;
        int n3 = n2 == 0 ? -1 : (1 << n2) - 1;
        for (int i = 0; i < this.numRows; ++i) {
            int n4;
            int n5;
            int n6 = i >>> 5;
            int n7 = n5 = i & 0x1F;
            n5 = 1 << n5;
            for (n4 = 0; n4 < n6; ++n4) {
                this.matrix[i][n4] = 0;
            }
            this.matrix[i][n6] = secureRandom.nextInt() << n7 | n5;
            for (n4 = n6 + 1; n4 < this.length; ++n4) {
                this.matrix[i][n4] = secureRandom.nextInt();
            }
            int[] nArray = this.matrix[i];
            int n8 = this.length - 1;
            nArray[n8] = nArray[n8] & n3;
        }
    }

    private void assignRandomRegularMatrix(int n, SecureRandom secureRandom) {
        this.numRows = n;
        this.numColumns = n;
        this.length = n + 31 >>> 5;
        this.matrix = new int[this.numRows][this.length];
        GF2Matrix gF2Matrix = new GF2Matrix(n, 'L', secureRandom);
        GF2Matrix gF2Matrix2 = new GF2Matrix(n, 'U', secureRandom);
        GF2Matrix gF2Matrix3 = (GF2Matrix)gF2Matrix.rightMultiply(gF2Matrix2);
        Permutation permutation = new Permutation(n, secureRandom);
        int[] nArray = permutation.getVector();
        for (int i = 0; i < n; ++i) {
            System.arraycopy(gF2Matrix3.matrix[i], 0, this.matrix[nArray[i]], 0, this.length);
        }
    }

    public static GF2Matrix[] createRandomRegularMatrixAndItsInverse(int n, SecureRandom secureRandom) {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        GF2Matrix[] gF2MatrixArray = new GF2Matrix[2];
        int n8 = n + 31 >> 5;
        GF2Matrix gF2Matrix = new GF2Matrix(n, 'L', secureRandom);
        GF2Matrix gF2Matrix2 = new GF2Matrix(n, 'U', secureRandom);
        GF2Matrix gF2Matrix3 = (GF2Matrix)gF2Matrix.rightMultiply(gF2Matrix2);
        Permutation permutation = new Permutation(n, secureRandom);
        int[] nArray = permutation.getVector();
        int[][] nArray2 = new int[n][n8];
        for (int i = 0; i < n; ++i) {
            System.arraycopy(gF2Matrix3.matrix[nArray[i]], 0, nArray2[i], 0, n8);
        }
        gF2MatrixArray[0] = new GF2Matrix(n, nArray2);
        GF2Matrix gF2Matrix4 = new GF2Matrix(n, 'I');
        for (int i = 0; i < n; ++i) {
            n7 = i & 0x1F;
            n6 = i >>> 5;
            n5 = 1 << n7;
            for (n4 = i + 1; n4 < n; ++n4) {
                n3 = gF2Matrix.matrix[n4][n6] & n5;
                if (n3 == 0) continue;
                for (n2 = 0; n2 <= n6; ++n2) {
                    int[] nArray3 = gF2Matrix4.matrix[n4];
                    int n9 = n2;
                    nArray3[n9] = nArray3[n9] ^ gF2Matrix4.matrix[i][n2];
                }
            }
        }
        GF2Matrix gF2Matrix5 = new GF2Matrix(n, 'I');
        for (n7 = n - 1; n7 >= 0; --n7) {
            n6 = n7 & 0x1F;
            n5 = n7 >>> 5;
            n4 = 1 << n6;
            for (n3 = n7 - 1; n3 >= 0; --n3) {
                n2 = gF2Matrix2.matrix[n3][n5] & n4;
                if (n2 == 0) continue;
                for (int i = n5; i < n8; ++i) {
                    int[] nArray4 = gF2Matrix5.matrix[n3];
                    int n10 = i;
                    nArray4[n10] = nArray4[n10] ^ gF2Matrix5.matrix[n7][i];
                }
            }
        }
        gF2MatrixArray[1] = (GF2Matrix)gF2Matrix5.rightMultiply(gF2Matrix4.rightMultiply(permutation));
        return gF2MatrixArray;
    }

    public int[][] getIntArray() {
        return this.matrix;
    }

    public int getLength() {
        return this.length;
    }

    public int[] getRow(int n) {
        return this.matrix[n];
    }

    public byte[] getEncoded() {
        int n = this.numColumns + 7 >>> 3;
        n *= this.numRows;
        byte[] byArray = new byte[n += 8];
        LittleEndianConversions.I2OSP(this.numRows, byArray, 0);
        LittleEndianConversions.I2OSP(this.numColumns, byArray, 4);
        int n2 = this.numColumns >>> 5;
        int n3 = this.numColumns & 0x1F;
        int n4 = 8;
        for (int i = 0; i < this.numRows; ++i) {
            int n5 = 0;
            while (n5 < n2) {
                LittleEndianConversions.I2OSP(this.matrix[i][n5], byArray, n4);
                ++n5;
                n4 += 4;
            }
            for (n5 = 0; n5 < n3; n5 += 8) {
                byArray[n4++] = (byte)(this.matrix[i][n2] >>> n5 & 0xFF);
            }
        }
        return byArray;
    }

    public double getHammingWeight() {
        double d = 0.0;
        double d2 = 0.0;
        int n = this.numColumns & 0x1F;
        int n2 = n == 0 ? this.length : this.length - 1;
        for (int i = 0; i < this.numRows; ++i) {
            int n3;
            int n4;
            int n5;
            for (n5 = 0; n5 < n2; ++n5) {
                n4 = this.matrix[i][n5];
                for (n3 = 0; n3 < 32; ++n3) {
                    int n6 = n4 >>> n3 & 1;
                    d += (double)n6;
                    d2 += 1.0;
                }
            }
            n5 = this.matrix[i][this.length - 1];
            for (n4 = 0; n4 < n; ++n4) {
                n3 = n5 >>> n4 & 1;
                d += (double)n3;
                d2 += 1.0;
            }
        }
        return d / d2;
    }

    public boolean isZero() {
        for (int i = 0; i < this.numRows; ++i) {
            for (int j = 0; j < this.length; ++j) {
                if (this.matrix[i][j] == 0) continue;
                return false;
            }
        }
        return true;
    }

    public GF2Matrix getLeftSubMatrix() {
        if (this.numColumns <= this.numRows) {
            throw new ArithmeticException("empty submatrix");
        }
        int n = this.numRows + 31 >> 5;
        int[][] nArray = new int[this.numRows][n];
        int n2 = (1 << (this.numRows & 0x1F)) - 1;
        if (n2 == 0) {
            n2 = -1;
        }
        for (int i = this.numRows - 1; i >= 0; --i) {
            System.arraycopy(this.matrix[i], 0, nArray[i], 0, n);
            int[] nArray2 = nArray[i];
            int n3 = n - 1;
            nArray2[n3] = nArray2[n3] & n2;
        }
        return new GF2Matrix(this.numRows, nArray);
    }

    public GF2Matrix extendLeftCompactForm() {
        int n = this.numColumns + this.numRows;
        GF2Matrix gF2Matrix = new GF2Matrix(this.numRows, n);
        int n2 = this.numRows - 1 + this.numColumns;
        int n3 = this.numRows - 1;
        while (n3 >= 0) {
            System.arraycopy(this.matrix[n3], 0, gF2Matrix.matrix[n3], 0, this.length);
            int[] nArray = gF2Matrix.matrix[n3];
            int n4 = n2 >> 5;
            nArray[n4] = nArray[n4] | 1 << (n2 & 0x1F);
            --n3;
            --n2;
        }
        return gF2Matrix;
    }

    public GF2Matrix getRightSubMatrix() {
        if (this.numColumns <= this.numRows) {
            throw new ArithmeticException("empty submatrix");
        }
        int n = this.numRows >> 5;
        int n2 = this.numRows & 0x1F;
        GF2Matrix gF2Matrix = new GF2Matrix(this.numRows, this.numColumns - this.numRows);
        for (int i = this.numRows - 1; i >= 0; --i) {
            if (n2 != 0) {
                int n3 = n;
                for (int j = 0; j < gF2Matrix.length - 1; ++j) {
                    gF2Matrix.matrix[i][j] = this.matrix[i][n3++] >>> n2 | this.matrix[i][n3] << 32 - n2;
                }
                gF2Matrix.matrix[i][gF2Matrix.length - 1] = this.matrix[i][n3++] >>> n2;
                if (n3 >= this.length) continue;
                int[] nArray = gF2Matrix.matrix[i];
                int n4 = gF2Matrix.length - 1;
                nArray[n4] = nArray[n4] | this.matrix[i][n3] << 32 - n2;
                continue;
            }
            System.arraycopy(this.matrix[i], n, gF2Matrix.matrix[i], 0, gF2Matrix.length);
        }
        return gF2Matrix;
    }

    public GF2Matrix extendRightCompactForm() {
        GF2Matrix gF2Matrix = new GF2Matrix(this.numRows, this.numRows + this.numColumns);
        int n = this.numRows >> 5;
        int n2 = this.numRows & 0x1F;
        for (int i = this.numRows - 1; i >= 0; --i) {
            int[] nArray = gF2Matrix.matrix[i];
            int n3 = i >> 5;
            nArray[n3] = nArray[n3] | 1 << (i & 0x1F);
            if (n2 != 0) {
                int n4;
                int n5 = n;
                for (n4 = 0; n4 < this.length - 1; ++n4) {
                    int n6 = this.matrix[i][n4];
                    int[] nArray2 = gF2Matrix.matrix[i];
                    int n7 = n5++;
                    nArray2[n7] = nArray2[n7] | n6 << n2;
                    int[] nArray3 = gF2Matrix.matrix[i];
                    int n8 = n5;
                    nArray3[n8] = nArray3[n8] | n6 >>> 32 - n2;
                }
                n4 = this.matrix[i][this.length - 1];
                int[] nArray4 = gF2Matrix.matrix[i];
                int n9 = n5++;
                nArray4[n9] = nArray4[n9] | n4 << n2;
                if (n5 >= gF2Matrix.length) continue;
                int[] nArray5 = gF2Matrix.matrix[i];
                int n10 = n5;
                nArray5[n10] = nArray5[n10] | n4 >>> 32 - n2;
                continue;
            }
            System.arraycopy(this.matrix[i], 0, gF2Matrix.matrix[i], n, this.length);
        }
        return gF2Matrix;
    }

    public Matrix computeTranspose() {
        int[][] nArray = new int[this.numColumns][this.numRows + 31 >>> 5];
        for (int i = 0; i < this.numRows; ++i) {
            for (int j = 0; j < this.numColumns; ++j) {
                int n = j >>> 5;
                int n2 = j & 0x1F;
                int n3 = this.matrix[i][n] >>> n2 & 1;
                int n4 = i >>> 5;
                int n5 = i & 0x1F;
                if (n3 != 1) continue;
                int[] nArray2 = nArray[j];
                int n6 = n4;
                nArray2[n6] = nArray2[n6] | 1 << n5;
            }
        }
        return new GF2Matrix(this.numRows, nArray);
    }

    public Matrix computeInverse() {
        int n;
        int n2;
        int n3;
        if (this.numRows != this.numColumns) {
            throw new ArithmeticException("Matrix is not invertible.");
        }
        int[][] nArray = new int[this.numRows][this.length];
        for (int i = this.numRows - 1; i >= 0; --i) {
            nArray[i] = IntUtils.clone(this.matrix[i]);
        }
        int[][] nArray2 = new int[this.numRows][this.length];
        for (n3 = this.numRows - 1; n3 >= 0; --n3) {
            n2 = n3 >> 5;
            n = n3 & 0x1F;
            nArray2[n3][n2] = 1 << n;
        }
        for (n3 = 0; n3 < this.numRows; ++n3) {
            int n4;
            n2 = n3 >> 5;
            n = 1 << (n3 & 0x1F);
            if ((nArray[n3][n2] & n) == 0) {
                n4 = 0;
                for (int i = n3 + 1; i < this.numRows; ++i) {
                    if ((nArray[i][n2] & n) == 0) continue;
                    n4 = 1;
                    GF2Matrix.swapRows(nArray, n3, i);
                    GF2Matrix.swapRows(nArray2, n3, i);
                    i = this.numRows;
                }
                if (n4 == 0) {
                    throw new ArithmeticException("Matrix is not invertible.");
                }
            }
            for (n4 = this.numRows - 1; n4 >= 0; --n4) {
                if (n4 == n3 || (nArray[n4][n2] & n) == 0) continue;
                GF2Matrix.addToRow(nArray[n3], nArray[n4], n2);
                GF2Matrix.addToRow(nArray2[n3], nArray2[n4], 0);
            }
        }
        return new GF2Matrix(this.numColumns, nArray2);
    }

    public Matrix leftMultiply(Permutation permutation) {
        int[] nArray = permutation.getVector();
        if (nArray.length != this.numRows) {
            throw new ArithmeticException("length mismatch");
        }
        int[][] nArrayArray = new int[this.numRows][];
        for (int i = this.numRows - 1; i >= 0; --i) {
            nArrayArray[i] = IntUtils.clone(this.matrix[nArray[i]]);
        }
        return new GF2Matrix(this.numRows, nArrayArray);
    }

    public Vector leftMultiply(Vector vector) {
        int n;
        int n2;
        int n3;
        if (!(vector instanceof GF2Vector)) {
            throw new ArithmeticException("vector is not defined over GF(2)");
        }
        if (vector.length != this.numRows) {
            throw new ArithmeticException("length mismatch");
        }
        int[] nArray = ((GF2Vector)vector).getVecArray();
        int[] nArray2 = new int[this.length];
        int n4 = this.numRows >> 5;
        int n5 = 1 << (this.numRows & 0x1F);
        int n6 = 0;
        for (n3 = 0; n3 < n4; ++n3) {
            n2 = 1;
            do {
                if ((n = nArray[n3] & n2) != 0) {
                    for (int i = 0; i < this.length; ++i) {
                        int n7 = i;
                        nArray2[n7] = nArray2[n7] ^ this.matrix[n6][i];
                    }
                }
                ++n6;
            } while ((n2 <<= 1) != 0);
        }
        for (n3 = 1; n3 != n5; n3 <<= 1) {
            n2 = nArray[n4] & n3;
            if (n2 != 0) {
                for (n = 0; n < this.length; ++n) {
                    int n8 = n;
                    nArray2[n8] = nArray2[n8] ^ this.matrix[n6][n];
                }
            }
            ++n6;
        }
        return new GF2Vector(nArray2, this.numColumns);
    }

    public Vector leftMultiplyLeftCompactForm(Vector vector) {
        int n;
        int n2;
        int n3;
        int n4;
        int n5;
        if (!(vector instanceof GF2Vector)) {
            throw new ArithmeticException("vector is not defined over GF(2)");
        }
        if (vector.length != this.numRows) {
            throw new ArithmeticException("length mismatch");
        }
        int[] nArray = ((GF2Vector)vector).getVecArray();
        int[] nArray2 = new int[this.numRows + this.numColumns + 31 >>> 5];
        int n6 = this.numRows >>> 5;
        int n7 = 0;
        for (n5 = 0; n5 < n6; ++n5) {
            n4 = 1;
            do {
                if ((n3 = nArray[n5] & n4) != 0) {
                    for (n2 = 0; n2 < this.length; ++n2) {
                        int n8 = n2;
                        nArray2[n8] = nArray2[n8] ^ this.matrix[n7][n2];
                    }
                    n2 = this.numColumns + n7 >>> 5;
                    n = this.numColumns + n7 & 0x1F;
                    int n9 = n2;
                    nArray2[n9] = nArray2[n9] | 1 << n;
                }
                ++n7;
            } while ((n4 <<= 1) != 0);
        }
        n5 = 1 << (this.numRows & 0x1F);
        for (n4 = 1; n4 != n5; n4 <<= 1) {
            n3 = nArray[n6] & n4;
            if (n3 != 0) {
                for (n2 = 0; n2 < this.length; ++n2) {
                    int n10 = n2;
                    nArray2[n10] = nArray2[n10] ^ this.matrix[n7][n2];
                }
                n2 = this.numColumns + n7 >>> 5;
                n = this.numColumns + n7 & 0x1F;
                int n11 = n2;
                nArray2[n11] = nArray2[n11] | 1 << n;
            }
            ++n7;
        }
        return new GF2Vector(nArray2, this.numRows + this.numColumns);
    }

    public Matrix rightMultiply(Matrix matrix) {
        if (!(matrix instanceof GF2Matrix)) {
            throw new ArithmeticException("matrix is not defined over GF(2)");
        }
        if (matrix.numRows != this.numColumns) {
            throw new ArithmeticException("length mismatch");
        }
        GF2Matrix gF2Matrix = (GF2Matrix)matrix;
        GF2Matrix gF2Matrix2 = new GF2Matrix(this.numRows, matrix.numColumns);
        int n = this.numColumns & 0x1F;
        int n2 = n == 0 ? this.length : this.length - 1;
        for (int i = 0; i < this.numRows; ++i) {
            int n3;
            int n4;
            int n5;
            int n6;
            int n7 = 0;
            for (n6 = 0; n6 < n2; ++n6) {
                n5 = this.matrix[i][n6];
                for (n4 = 0; n4 < 32; ++n4) {
                    n3 = n5 & 1 << n4;
                    if (n3 != 0) {
                        for (int j = 0; j < gF2Matrix.length; ++j) {
                            int[] nArray = gF2Matrix2.matrix[i];
                            int n8 = j;
                            nArray[n8] = nArray[n8] ^ gF2Matrix.matrix[n7][j];
                        }
                    }
                    ++n7;
                }
            }
            n6 = this.matrix[i][this.length - 1];
            for (n5 = 0; n5 < n; ++n5) {
                n4 = n6 & 1 << n5;
                if (n4 != 0) {
                    for (n3 = 0; n3 < gF2Matrix.length; ++n3) {
                        int[] nArray = gF2Matrix2.matrix[i];
                        int n9 = n3;
                        nArray[n9] = nArray[n9] ^ gF2Matrix.matrix[n7][n3];
                    }
                }
                ++n7;
            }
        }
        return gF2Matrix2;
    }

    public Matrix rightMultiply(Permutation permutation) {
        int[] nArray = permutation.getVector();
        if (nArray.length != this.numColumns) {
            throw new ArithmeticException("length mismatch");
        }
        GF2Matrix gF2Matrix = new GF2Matrix(this.numRows, this.numColumns);
        for (int i = this.numColumns - 1; i >= 0; --i) {
            int n = i >>> 5;
            int n2 = i & 0x1F;
            int n3 = nArray[i] >>> 5;
            int n4 = nArray[i] & 0x1F;
            for (int j = this.numRows - 1; j >= 0; --j) {
                int[] nArray2 = gF2Matrix.matrix[j];
                int n5 = n;
                nArray2[n5] = nArray2[n5] | (this.matrix[j][n3] >>> n4 & 1) << n2;
            }
        }
        return gF2Matrix;
    }

    public Vector rightMultiply(Vector vector) {
        if (!(vector instanceof GF2Vector)) {
            throw new ArithmeticException("vector is not defined over GF(2)");
        }
        if (vector.length != this.numColumns) {
            throw new ArithmeticException("length mismatch");
        }
        int[] nArray = ((GF2Vector)vector).getVecArray();
        int[] nArray2 = new int[this.numRows + 31 >>> 5];
        for (int i = 0; i < this.numRows; ++i) {
            int n;
            int n2 = 0;
            for (n = 0; n < this.length; ++n) {
                n2 ^= this.matrix[i][n] & nArray[n];
            }
            n = 0;
            for (int j = 0; j < 32; ++j) {
                n ^= n2 >>> j & 1;
            }
            if (n != 1) continue;
            int n3 = i >>> 5;
            nArray2[n3] = nArray2[n3] | 1 << (i & 0x1F);
        }
        return new GF2Vector(nArray2, this.numRows);
    }

    public Vector rightMultiplyRightCompactForm(Vector vector) {
        if (!(vector instanceof GF2Vector)) {
            throw new ArithmeticException("vector is not defined over GF(2)");
        }
        if (vector.length != this.numColumns + this.numRows) {
            throw new ArithmeticException("length mismatch");
        }
        int[] nArray = ((GF2Vector)vector).getVecArray();
        int[] nArray2 = new int[this.numRows + 31 >>> 5];
        int n = this.numRows >> 5;
        int n2 = this.numRows & 0x1F;
        for (int i = 0; i < this.numRows; ++i) {
            int n3;
            int n4;
            int n5 = nArray[i >> 5] >>> (i & 0x1F) & 1;
            int n6 = n;
            if (n2 != 0) {
                n4 = 0;
                for (n3 = 0; n3 < this.length - 1; ++n3) {
                    n4 = nArray[n6++] >>> n2 | nArray[n6] << 32 - n2;
                    n5 ^= this.matrix[i][n3] & n4;
                }
                n4 = nArray[n6++] >>> n2;
                if (n6 < nArray.length) {
                    n4 |= nArray[n6] << 32 - n2;
                }
                n5 ^= this.matrix[i][this.length - 1] & n4;
            } else {
                for (n4 = 0; n4 < this.length; ++n4) {
                    n5 ^= this.matrix[i][n4] & nArray[n6++];
                }
            }
            n4 = 0;
            for (n3 = 0; n3 < 32; ++n3) {
                n4 ^= n5 & 1;
                n5 >>>= 1;
            }
            if (n4 != 1) continue;
            int n7 = i >> 5;
            nArray2[n7] = nArray2[n7] | 1 << (i & 0x1F);
        }
        return new GF2Vector(nArray2, this.numRows);
    }

    public boolean equals(Object object) {
        if (!(object instanceof GF2Matrix)) {
            return false;
        }
        GF2Matrix gF2Matrix = (GF2Matrix)object;
        if (this.numRows != gF2Matrix.numRows || this.numColumns != gF2Matrix.numColumns || this.length != gF2Matrix.length) {
            return false;
        }
        for (int i = 0; i < this.numRows; ++i) {
            if (IntUtils.equals(this.matrix[i], gF2Matrix.matrix[i])) continue;
            return false;
        }
        return true;
    }

    public int hashCode() {
        int n = (this.numRows * 31 + this.numColumns) * 31 + this.length;
        for (int i = 0; i < this.numRows; ++i) {
            n = n * 31 + this.matrix[i].hashCode();
        }
        return n;
    }

    public String toString() {
        int n = this.numColumns & 0x1F;
        int n2 = n == 0 ? this.length : this.length - 1;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.numRows; ++i) {
            int n3;
            int n4;
            int n5;
            stringBuffer.append(i + ": ");
            for (n5 = 0; n5 < n2; ++n5) {
                n4 = this.matrix[i][n5];
                for (n3 = 0; n3 < 32; ++n3) {
                    int n6 = n4 >>> n3 & 1;
                    if (n6 == 0) {
                        stringBuffer.append('0');
                        continue;
                    }
                    stringBuffer.append('1');
                }
                stringBuffer.append(' ');
            }
            n5 = this.matrix[i][this.length - 1];
            for (n4 = 0; n4 < n; ++n4) {
                n3 = n5 >>> n4 & 1;
                if (n3 == 0) {
                    stringBuffer.append('0');
                    continue;
                }
                stringBuffer.append('1');
            }
            stringBuffer.append('\n');
        }
        return stringBuffer.toString();
    }

    private static void swapRows(int[][] nArray, int n, int n2) {
        int[] nArray2 = nArray[n];
        nArray[n] = nArray[n2];
        nArray[n2] = nArray2;
    }

    private static void addToRow(int[] nArray, int[] nArray2, int n) {
        for (int i = nArray2.length - 1; i >= n; --i) {
            nArray2[i] = nArray[i] ^ nArray2[i];
        }
    }
}

