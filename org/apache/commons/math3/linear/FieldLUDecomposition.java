/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.FieldDecompositionSolver;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.FieldVector;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldLUDecomposition<T extends FieldElement<T>> {
    private final Field<T> field;
    private T[][] lu;
    private int[] pivot;
    private boolean even;
    private boolean singular;
    private FieldMatrix<T> cachedL;
    private FieldMatrix<T> cachedU;
    private FieldMatrix<T> cachedP;

    public FieldLUDecomposition(FieldMatrix<T> matrix) {
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        int m = matrix.getColumnDimension();
        this.field = matrix.getField();
        this.lu = matrix.getData();
        this.pivot = new int[m];
        this.cachedL = null;
        this.cachedU = null;
        this.cachedP = null;
        for (int row = 0; row < m; ++row) {
            this.pivot[row] = row;
        }
        this.even = true;
        this.singular = false;
        for (int col = 0; col < m; ++col) {
            FieldElement<T> sum = (FieldElement)this.field.getZero();
            for (int row = 0; row < col; ++row) {
                T[] luRow = this.lu[row];
                sum = luRow[col];
                for (int i = 0; i < row; ++i) {
                    sum = (FieldElement)sum.subtract(luRow[i].multiply(this.lu[i][col]));
                }
                luRow[col] = sum;
            }
            int nonZero = col;
            for (int row = col; row < m; ++row) {
                T[] luRow = this.lu[row];
                sum = luRow[col];
                for (int i = 0; i < col; ++i) {
                    sum = (FieldElement)sum.subtract(luRow[i].multiply(this.lu[i][col]));
                }
                luRow[col] = sum;
                if (!this.lu[nonZero][col].equals(this.field.getZero())) continue;
                ++nonZero;
            }
            if (nonZero >= m) {
                this.singular = true;
                return;
            }
            if (nonZero != col) {
                FieldElement tmp = (FieldElement)this.field.getZero();
                for (int i = 0; i < m; ++i) {
                    tmp = this.lu[nonZero][i];
                    this.lu[nonZero][i] = this.lu[col][i];
                    this.lu[col][i] = tmp;
                }
                int temp = this.pivot[nonZero];
                this.pivot[nonZero] = this.pivot[col];
                this.pivot[col] = temp;
                this.even = !this.even;
            }
            T luDiag = this.lu[col][col];
            for (int row = col + 1; row < m; ++row) {
                T[] luRow = this.lu[row];
                luRow[col] = (FieldElement)luRow[col].divide(luDiag);
            }
        }
    }

    public FieldMatrix<T> getL() {
        if (this.cachedL == null && !this.singular) {
            int m = this.pivot.length;
            this.cachedL = new Array2DRowFieldMatrix<T>(this.field, m, m);
            for (int i = 0; i < m; ++i) {
                T[] luI = this.lu[i];
                for (int j = 0; j < i; ++j) {
                    this.cachedL.setEntry(i, j, luI[j]);
                }
                this.cachedL.setEntry(i, i, (FieldElement)this.field.getOne());
            }
        }
        return this.cachedL;
    }

    public FieldMatrix<T> getU() {
        if (this.cachedU == null && !this.singular) {
            int m = this.pivot.length;
            this.cachedU = new Array2DRowFieldMatrix<T>(this.field, m, m);
            for (int i = 0; i < m; ++i) {
                T[] luI = this.lu[i];
                for (int j = i; j < m; ++j) {
                    this.cachedU.setEntry(i, j, luI[j]);
                }
            }
        }
        return this.cachedU;
    }

    public FieldMatrix<T> getP() {
        if (this.cachedP == null && !this.singular) {
            int m = this.pivot.length;
            this.cachedP = new Array2DRowFieldMatrix<T>(this.field, m, m);
            for (int i = 0; i < m; ++i) {
                this.cachedP.setEntry(i, this.pivot[i], (FieldElement)this.field.getOne());
            }
        }
        return this.cachedP;
    }

    public int[] getPivot() {
        return (int[])this.pivot.clone();
    }

    public T getDeterminant() {
        if (this.singular) {
            return (T)((FieldElement)this.field.getZero());
        }
        int m = this.pivot.length;
        FieldElement determinant = this.even ? (FieldElement)this.field.getOne() : (FieldElement)((FieldElement)this.field.getZero()).subtract(this.field.getOne());
        for (int i = 0; i < m; ++i) {
            determinant = (FieldElement)determinant.multiply(this.lu[i][i]);
        }
        return (T)determinant;
    }

    public FieldDecompositionSolver<T> getSolver() {
        return new Solver(this.field, (FieldElement[][])this.lu, this.pivot, this.singular);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class Solver<T extends FieldElement<T>>
    implements FieldDecompositionSolver<T> {
        private final Field<T> field;
        private final T[][] lu;
        private final int[] pivot;
        private final boolean singular;

        private Solver(Field<T> field, T[][] lu, int[] pivot, boolean singular) {
            this.field = field;
            this.lu = lu;
            this.pivot = pivot;
            this.singular = singular;
        }

        @Override
        public boolean isNonSingular() {
            return !this.singular;
        }

        @Override
        public FieldVector<T> solve(FieldVector<T> b) {
            try {
                return this.solve((ArrayFieldVector)b);
            } catch (ClassCastException cce) {
                int i;
                FieldElement bpCol;
                int col;
                int m = this.pivot.length;
                if (b.getDimension() != m) {
                    throw new DimensionMismatchException(b.getDimension(), m);
                }
                if (this.singular) {
                    throw new SingularMatrixException();
                }
                FieldElement[] bp = (FieldElement[])MathArrays.buildArray(this.field, m);
                for (int row = 0; row < m; ++row) {
                    bp[row] = b.getEntry(this.pivot[row]);
                }
                for (col = 0; col < m; ++col) {
                    bpCol = bp[col];
                    for (i = col + 1; i < m; ++i) {
                        bp[i] = (FieldElement)bp[i].subtract(bpCol.multiply(this.lu[i][col]));
                    }
                }
                for (col = m - 1; col >= 0; --col) {
                    bp[col] = (FieldElement)bp[col].divide(this.lu[col][col]);
                    bpCol = bp[col];
                    for (i = 0; i < col; ++i) {
                        bp[i] = (FieldElement)bp[i].subtract(bpCol.multiply(this.lu[i][col]));
                    }
                }
                return new ArrayFieldVector(this.field, bp, false);
            }
        }

        @Override
        public ArrayFieldVector<T> solve(ArrayFieldVector<T> b) {
            int i;
            FieldElement bpCol;
            int col;
            int m = this.pivot.length;
            int length = b.getDimension();
            if (length != m) {
                throw new DimensionMismatchException(length, m);
            }
            if (this.singular) {
                throw new SingularMatrixException();
            }
            FieldElement[] bp = (FieldElement[])MathArrays.buildArray(this.field, m);
            for (int row = 0; row < m; ++row) {
                bp[row] = b.getEntry(this.pivot[row]);
            }
            for (col = 0; col < m; ++col) {
                bpCol = bp[col];
                for (i = col + 1; i < m; ++i) {
                    bp[i] = (FieldElement)bp[i].subtract(bpCol.multiply(this.lu[i][col]));
                }
            }
            for (col = m - 1; col >= 0; --col) {
                bp[col] = (FieldElement)bp[col].divide(this.lu[col][col]);
                bpCol = bp[col];
                for (i = 0; i < col; ++i) {
                    bp[i] = (FieldElement)bp[i].subtract(bpCol.multiply(this.lu[i][col]));
                }
            }
            return new ArrayFieldVector(bp, false);
        }

        @Override
        public FieldMatrix<T> solve(FieldMatrix<T> b) {
            FieldElement[] bpCol;
            int col;
            int m = this.pivot.length;
            if (b.getRowDimension() != m) {
                throw new DimensionMismatchException(b.getRowDimension(), m);
            }
            if (this.singular) {
                throw new SingularMatrixException();
            }
            int nColB = b.getColumnDimension();
            FieldElement[][] bp = (FieldElement[][])MathArrays.buildArray(this.field, m, nColB);
            for (int row = 0; row < m; ++row) {
                FieldElement[] bpRow = bp[row];
                int pRow = this.pivot[row];
                for (int col2 = 0; col2 < nColB; ++col2) {
                    bpRow[col2] = b.getEntry(pRow, col2);
                }
            }
            for (col = 0; col < m; ++col) {
                bpCol = bp[col];
                for (int i = col + 1; i < m; ++i) {
                    FieldElement[] bpI = bp[i];
                    T luICol = this.lu[i][col];
                    for (int j = 0; j < nColB; ++j) {
                        bpI[j] = (FieldElement)bpI[j].subtract(bpCol[j].multiply(luICol));
                    }
                }
            }
            for (col = m - 1; col >= 0; --col) {
                bpCol = bp[col];
                T luDiag = this.lu[col][col];
                for (int j = 0; j < nColB; ++j) {
                    bpCol[j] = (FieldElement)bpCol[j].divide(luDiag);
                }
                for (int i = 0; i < col; ++i) {
                    FieldElement[] bpI = bp[i];
                    T luICol = this.lu[i][col];
                    for (int j = 0; j < nColB; ++j) {
                        bpI[j] = (FieldElement)bpI[j].subtract(bpCol[j].multiply(luICol));
                    }
                }
            }
            return new Array2DRowFieldMatrix(this.field, bp, false);
        }

        @Override
        public FieldMatrix<T> getInverse() {
            int m = this.pivot.length;
            FieldElement one = (FieldElement)this.field.getOne();
            Array2DRowFieldMatrix<FieldElement> identity = new Array2DRowFieldMatrix<FieldElement>(this.field, m, m);
            for (int i = 0; i < m; ++i) {
                identity.setEntry(i, i, one);
            }
            return this.solve(identity);
        }
    }
}

