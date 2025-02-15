/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.IntUtils;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.Vector;

public class GF2mMatrix
extends Matrix {
    protected GF2mField field;
    protected int[][] matrix;

    public GF2mMatrix(GF2mField gF2mField, byte[] byArray) {
        int n;
        this.field = gF2mField;
        int n2 = 1;
        for (n = 8; gF2mField.getDegree() > n; n += 8) {
            ++n2;
        }
        if (byArray.length < 5) {
            throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
        }
        this.numRows = (byArray[3] & 0xFF) << 24 ^ (byArray[2] & 0xFF) << 16 ^ (byArray[1] & 0xFF) << 8 ^ byArray[0] & 0xFF;
        int n3 = n2 * this.numRows;
        if (this.numRows <= 0 || (byArray.length - 4) % n3 != 0) {
            throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
        }
        this.numColumns = (byArray.length - 4) / n3;
        this.matrix = new int[this.numRows][this.numColumns];
        n2 = 4;
        for (int i = 0; i < this.numRows; ++i) {
            for (int j = 0; j < this.numColumns; ++j) {
                for (int k = 0; k < n; k += 8) {
                    int[] nArray = this.matrix[i];
                    int n4 = j;
                    nArray[n4] = nArray[n4] ^ (byArray[n2++] & 0xFF) << k;
                }
                if (this.field.isElementOfThisField(this.matrix[i][j])) continue;
                throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
            }
        }
    }

    public GF2mMatrix(GF2mMatrix gF2mMatrix) {
        this.numRows = gF2mMatrix.numRows;
        this.numColumns = gF2mMatrix.numColumns;
        this.field = gF2mMatrix.field;
        this.matrix = new int[this.numRows][];
        for (int i = 0; i < this.numRows; ++i) {
            this.matrix[i] = IntUtils.clone(gF2mMatrix.matrix[i]);
        }
    }

    protected GF2mMatrix(GF2mField gF2mField, int[][] nArray) {
        this.field = gF2mField;
        this.matrix = nArray;
        this.numRows = nArray.length;
        this.numColumns = nArray[0].length;
    }

    public byte[] getEncoded() {
        int n;
        int n2 = 1;
        for (n = 8; this.field.getDegree() > n; n += 8) {
            ++n2;
        }
        byte[] byArray = new byte[this.numRows * this.numColumns * n2 + 4];
        byArray[0] = (byte)(this.numRows & 0xFF);
        byArray[1] = (byte)(this.numRows >>> 8 & 0xFF);
        byArray[2] = (byte)(this.numRows >>> 16 & 0xFF);
        byArray[3] = (byte)(this.numRows >>> 24 & 0xFF);
        n2 = 4;
        for (int i = 0; i < this.numRows; ++i) {
            for (int j = 0; j < this.numColumns; ++j) {
                for (int k = 0; k < n; k += 8) {
                    byArray[n2++] = (byte)(this.matrix[i][j] >>> k);
                }
            }
        }
        return byArray;
    }

    public boolean isZero() {
        for (int i = 0; i < this.numRows; ++i) {
            for (int j = 0; j < this.numColumns; ++j) {
                if (this.matrix[i][j] == 0) continue;
                return false;
            }
        }
        return true;
    }

    public Matrix computeInverse() {
        int n;
        if (this.numRows != this.numColumns) {
            throw new ArithmeticException("Matrix is not invertible.");
        }
        int[][] nArray = new int[this.numRows][this.numRows];
        for (int i = this.numRows - 1; i >= 0; --i) {
            nArray[i] = IntUtils.clone(this.matrix[i]);
        }
        int[][] nArray2 = new int[this.numRows][this.numRows];
        for (n = this.numRows - 1; n >= 0; --n) {
            nArray2[n][n] = 1;
        }
        for (n = 0; n < this.numRows; ++n) {
            int n2;
            int n3;
            if (nArray[n][n] == 0) {
                n3 = 0;
                for (n2 = n + 1; n2 < this.numRows; ++n2) {
                    if (nArray[n2][n] == 0) continue;
                    n3 = 1;
                    GF2mMatrix.swapColumns(nArray, n, n2);
                    GF2mMatrix.swapColumns(nArray2, n, n2);
                    n2 = this.numRows;
                }
                if (n3 == 0) {
                    throw new ArithmeticException("Matrix is not invertible.");
                }
            }
            n3 = nArray[n][n];
            n2 = this.field.inverse(n3);
            this.multRowWithElementThis(nArray[n], n2);
            this.multRowWithElementThis(nArray2[n], n2);
            for (int i = 0; i < this.numRows; ++i) {
                if (i == n || (n3 = nArray[i][n]) == 0) continue;
                int[] nArray3 = this.multRowWithElement(nArray[n], n3);
                int[] nArray4 = this.multRowWithElement(nArray2[n], n3);
                this.addToRow(nArray3, nArray[i]);
                this.addToRow(nArray4, nArray2[i]);
            }
        }
        return new GF2mMatrix(this.field, nArray2);
    }

    private static void swapColumns(int[][] nArray, int n, int n2) {
        int[] nArray2 = nArray[n];
        nArray[n] = nArray[n2];
        nArray[n2] = nArray2;
    }

    private void multRowWithElementThis(int[] nArray, int n) {
        for (int i = nArray.length - 1; i >= 0; --i) {
            nArray[i] = this.field.mult(nArray[i], n);
        }
    }

    private int[] multRowWithElement(int[] nArray, int n) {
        int[] nArray2 = new int[nArray.length];
        for (int i = nArray.length - 1; i >= 0; --i) {
            nArray2[i] = this.field.mult(nArray[i], n);
        }
        return nArray2;
    }

    private void addToRow(int[] nArray, int[] nArray2) {
        for (int i = nArray2.length - 1; i >= 0; --i) {
            nArray2[i] = this.field.add(nArray[i], nArray2[i]);
        }
    }

    public Matrix rightMultiply(Matrix matrix) {
        throw new RuntimeException("Not implemented.");
    }

    public Matrix rightMultiply(Permutation permutation) {
        throw new RuntimeException("Not implemented.");
    }

    public Vector leftMultiply(Vector vector) {
        throw new RuntimeException("Not implemented.");
    }

    public Vector rightMultiply(Vector vector) {
        throw new RuntimeException("Not implemented.");
    }

    public boolean equals(Object object) {
        if (object == null || !(object instanceof GF2mMatrix)) {
            return false;
        }
        GF2mMatrix gF2mMatrix = (GF2mMatrix)object;
        if (!this.field.equals(gF2mMatrix.field) || gF2mMatrix.numRows != this.numColumns || gF2mMatrix.numColumns != this.numColumns) {
            return false;
        }
        for (int i = 0; i < this.numRows; ++i) {
            for (int j = 0; j < this.numColumns; ++j) {
                if (this.matrix[i][j] == gF2mMatrix.matrix[i][j]) continue;
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int n = (this.field.hashCode() * 31 + this.numRows) * 31 + this.numColumns;
        for (int i = 0; i < this.numRows; ++i) {
            for (int j = 0; j < this.numColumns; ++j) {
                n = n * 31 + this.matrix[i][j];
            }
        }
        return n;
    }

    public String toString() {
        String string = this.numRows + " x " + this.numColumns + " Matrix over " + this.field.toString() + ": \n";
        for (int i = 0; i < this.numRows; ++i) {
            for (int j = 0; j < this.numColumns; ++j) {
                string = string + this.field.elementToStr(this.matrix[i][j]) + " : ";
            }
            string = string + "\n";
        }
        return string;
    }
}

