/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.AbstractFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.FieldMatrixChangingVisitor;
import org.apache.commons.math3.linear.FieldMatrixPreservingVisitor;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Array2DRowFieldMatrix<T extends FieldElement<T>>
extends AbstractFieldMatrix<T>
implements Serializable {
    private static final long serialVersionUID = 7260756672015356458L;
    private T[][] data;

    public Array2DRowFieldMatrix(Field<T> field) {
        super(field);
    }

    public Array2DRowFieldMatrix(Field<T> field, int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
        super(field, rowDimension, columnDimension);
        this.data = (FieldElement[][])MathArrays.buildArray(field, rowDimension, columnDimension);
    }

    public Array2DRowFieldMatrix(T[][] d) throws DimensionMismatchException, NullArgumentException, NoDataException {
        this(Array2DRowFieldMatrix.extractField(d), (FieldElement[][])d);
    }

    public Array2DRowFieldMatrix(Field<T> field, T[][] d) throws DimensionMismatchException, NullArgumentException, NoDataException {
        super(field);
        this.copyIn((FieldElement[][])d);
    }

    public Array2DRowFieldMatrix(T[][] d, boolean copyArray) throws DimensionMismatchException, NoDataException, NullArgumentException {
        this(Array2DRowFieldMatrix.extractField(d), (FieldElement[][])d, copyArray);
    }

    public Array2DRowFieldMatrix(Field<T> field, T[][] d, boolean copyArray) throws DimensionMismatchException, NoDataException, NullArgumentException {
        super(field);
        if (copyArray) {
            this.copyIn((FieldElement[][])d);
        } else {
            MathUtils.checkNotNull(d);
            int nRows = d.length;
            if (nRows == 0) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
            }
            int nCols = d[0].length;
            if (nCols == 0) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
            }
            for (int r = 1; r < nRows; ++r) {
                if (d[r].length == nCols) continue;
                throw new DimensionMismatchException(nCols, d[r].length);
            }
            this.data = d;
        }
    }

    public Array2DRowFieldMatrix(T[] v) throws NoDataException {
        this(Array2DRowFieldMatrix.extractField(v), (FieldElement[])v);
    }

    public Array2DRowFieldMatrix(Field<T> field, T[] v) {
        super(field);
        int nRows = v.length;
        this.data = (FieldElement[][])MathArrays.buildArray(this.getField(), nRows, 1);
        for (int row = 0; row < nRows; ++row) {
            this.data[row][0] = v[row];
        }
    }

    @Override
    public FieldMatrix<T> createMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
        return new Array2DRowFieldMatrix(this.getField(), rowDimension, columnDimension);
    }

    @Override
    public FieldMatrix<T> copy() {
        return new Array2DRowFieldMatrix(this.getField(), this.copyOut(), false);
    }

    @Override
    public Array2DRowFieldMatrix<T> add(Array2DRowFieldMatrix<T> m) throws MatrixDimensionMismatchException {
        this.checkAdditionCompatible(m);
        int rowCount = this.getRowDimension();
        int columnCount = this.getColumnDimension();
        FieldElement[][] outData = (FieldElement[][])MathArrays.buildArray(this.getField(), rowCount, columnCount);
        for (int row = 0; row < rowCount; ++row) {
            T[] dataRow = this.data[row];
            T[] mRow = m.data[row];
            FieldElement[] outDataRow = outData[row];
            for (int col = 0; col < columnCount; ++col) {
                outDataRow[col] = (FieldElement)dataRow[col].add(mRow[col]);
            }
        }
        return new Array2DRowFieldMatrix(this.getField(), outData, false);
    }

    @Override
    public Array2DRowFieldMatrix<T> subtract(Array2DRowFieldMatrix<T> m) throws MatrixDimensionMismatchException {
        this.checkSubtractionCompatible(m);
        int rowCount = this.getRowDimension();
        int columnCount = this.getColumnDimension();
        FieldElement[][] outData = (FieldElement[][])MathArrays.buildArray(this.getField(), rowCount, columnCount);
        for (int row = 0; row < rowCount; ++row) {
            T[] dataRow = this.data[row];
            T[] mRow = m.data[row];
            FieldElement[] outDataRow = outData[row];
            for (int col = 0; col < columnCount; ++col) {
                outDataRow[col] = (FieldElement)dataRow[col].subtract(mRow[col]);
            }
        }
        return new Array2DRowFieldMatrix(this.getField(), outData, false);
    }

    @Override
    public Array2DRowFieldMatrix<T> multiply(Array2DRowFieldMatrix<T> m) throws DimensionMismatchException {
        this.checkMultiplicationCompatible(m);
        int nRows = this.getRowDimension();
        int nCols = m.getColumnDimension();
        int nSum = this.getColumnDimension();
        FieldElement[][] outData = (FieldElement[][])MathArrays.buildArray(this.getField(), nRows, nCols);
        for (int row = 0; row < nRows; ++row) {
            T[] dataRow = this.data[row];
            FieldElement[] outDataRow = outData[row];
            for (int col = 0; col < nCols; ++col) {
                FieldElement sum = (FieldElement)this.getField().getZero();
                for (int i = 0; i < nSum; ++i) {
                    sum = (FieldElement)sum.add(dataRow[i].multiply(m.data[i][col]));
                }
                outDataRow[col] = sum;
            }
        }
        return new Array2DRowFieldMatrix(this.getField(), outData, false);
    }

    @Override
    public T[][] getData() {
        return this.copyOut();
    }

    public T[][] getDataRef() {
        return this.data;
    }

    @Override
    public void setSubMatrix(T[][] subMatrix, int row, int column) throws OutOfRangeException, NullArgumentException, NoDataException, DimensionMismatchException {
        if (this.data == null) {
            if (row > 0) {
                throw new MathIllegalStateException(LocalizedFormats.FIRST_ROWS_NOT_INITIALIZED_YET, row);
            }
            if (column > 0) {
                throw new MathIllegalStateException(LocalizedFormats.FIRST_COLUMNS_NOT_INITIALIZED_YET, column);
            }
            int nRows = subMatrix.length;
            if (nRows == 0) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
            }
            int nCols = subMatrix[0].length;
            if (nCols == 0) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
            }
            this.data = (FieldElement[][])MathArrays.buildArray(this.getField(), subMatrix.length, nCols);
            for (int i = 0; i < this.data.length; ++i) {
                if (subMatrix[i].length != nCols) {
                    throw new DimensionMismatchException(nCols, subMatrix[i].length);
                }
                System.arraycopy(subMatrix[i], 0, this.data[i + row], column, nCols);
            }
        } else {
            super.setSubMatrix(subMatrix, row, column);
        }
    }

    @Override
    public T getEntry(int row, int column) throws OutOfRangeException {
        this.checkRowIndex(row);
        this.checkColumnIndex(column);
        return this.data[row][column];
    }

    @Override
    public void setEntry(int row, int column, T value) throws OutOfRangeException {
        this.checkRowIndex(row);
        this.checkColumnIndex(column);
        this.data[row][column] = value;
    }

    @Override
    public void addToEntry(int row, int column, T increment) throws OutOfRangeException {
        this.checkRowIndex(row);
        this.checkColumnIndex(column);
        this.data[row][column] = (FieldElement)this.data[row][column].add(increment);
    }

    @Override
    public void multiplyEntry(int row, int column, T factor) throws OutOfRangeException {
        this.checkRowIndex(row);
        this.checkColumnIndex(column);
        this.data[row][column] = (FieldElement)this.data[row][column].multiply(factor);
    }

    @Override
    public int getRowDimension() {
        return this.data == null ? 0 : this.data.length;
    }

    @Override
    public int getColumnDimension() {
        return this.data == null || this.data[0] == null ? 0 : this.data[0].length;
    }

    @Override
    public T[] operate(T[] v) throws DimensionMismatchException {
        int nRows = this.getRowDimension();
        int nCols = this.getColumnDimension();
        if (v.length != nCols) {
            throw new DimensionMismatchException(v.length, nCols);
        }
        FieldElement[] out = (FieldElement[])MathArrays.buildArray(this.getField(), nRows);
        for (int row = 0; row < nRows; ++row) {
            T[] dataRow = this.data[row];
            FieldElement sum = (FieldElement)this.getField().getZero();
            for (int i = 0; i < nCols; ++i) {
                sum = (FieldElement)sum.add(dataRow[i].multiply(v[i]));
            }
            out[row] = sum;
        }
        return out;
    }

    @Override
    public T[] preMultiply(T[] v) throws DimensionMismatchException {
        int nRows = this.getRowDimension();
        int nCols = this.getColumnDimension();
        if (v.length != nRows) {
            throw new DimensionMismatchException(v.length, nRows);
        }
        FieldElement[] out = (FieldElement[])MathArrays.buildArray(this.getField(), nCols);
        for (int col = 0; col < nCols; ++col) {
            FieldElement sum = (FieldElement)this.getField().getZero();
            for (int i = 0; i < nRows; ++i) {
                sum = (FieldElement)sum.add(this.data[i][col].multiply(v[i]));
            }
            out[col] = sum;
        }
        return out;
    }

    @Override
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> visitor) {
        int rows = this.getRowDimension();
        int columns = this.getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int i = 0; i < rows; ++i) {
            T[] rowI = this.data[i];
            for (int j = 0; j < columns; ++j) {
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    @Override
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> visitor) {
        int rows = this.getRowDimension();
        int columns = this.getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int i = 0; i < rows; ++i) {
            T[] rowI = this.data[i];
            for (int j = 0; j < columns; ++j) {
                visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    @Override
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int i = startRow; i <= endRow; ++i) {
            T[] rowI = this.data[i];
            for (int j = startColumn; j <= endColumn; ++j) {
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    @Override
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int i = startRow; i <= endRow; ++i) {
            T[] rowI = this.data[i];
            for (int j = startColumn; j <= endColumn; ++j) {
                visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    @Override
    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> visitor) {
        int rows = this.getRowDimension();
        int columns = this.getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int j = 0; j < columns; ++j) {
            for (int i = 0; i < rows; ++i) {
                T[] rowI = this.data[i];
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    @Override
    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> visitor) {
        int rows = this.getRowDimension();
        int columns = this.getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int j = 0; j < columns; ++j) {
            for (int i = 0; i < rows; ++i) {
                visitor.visit(i, j, this.data[i][j]);
            }
        }
        return visitor.end();
    }

    @Override
    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int j = startColumn; j <= endColumn; ++j) {
            for (int i = startRow; i <= endRow; ++i) {
                T[] rowI = this.data[i];
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    @Override
    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int j = startColumn; j <= endColumn; ++j) {
            for (int i = startRow; i <= endRow; ++i) {
                visitor.visit(i, j, this.data[i][j]);
            }
        }
        return visitor.end();
    }

    private T[][] copyOut() {
        int nRows = this.getRowDimension();
        FieldElement[][] out = (FieldElement[][])MathArrays.buildArray(this.getField(), nRows, this.getColumnDimension());
        for (int i = 0; i < nRows; ++i) {
            System.arraycopy(this.data[i], 0, out[i], 0, this.data[i].length);
        }
        return out;
    }

    private void copyIn(T[][] in) throws NullArgumentException, NoDataException, DimensionMismatchException {
        this.setSubMatrix((FieldElement[][])in, 0, 0);
    }
}

