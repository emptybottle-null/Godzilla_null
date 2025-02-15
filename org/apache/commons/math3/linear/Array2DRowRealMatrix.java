/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.AbstractRealMatrix;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealMatrixChangingVisitor;
import org.apache.commons.math3.linear.RealMatrixPreservingVisitor;
import org.apache.commons.math3.util.MathUtils;

public class Array2DRowRealMatrix
extends AbstractRealMatrix
implements Serializable {
    private static final long serialVersionUID = -1067294169172445528L;
    private double[][] data;

    public Array2DRowRealMatrix() {
    }

    public Array2DRowRealMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
        super(rowDimension, columnDimension);
        this.data = new double[rowDimension][columnDimension];
    }

    public Array2DRowRealMatrix(double[][] d) throws DimensionMismatchException, NoDataException, NullArgumentException {
        this.copyIn(d);
    }

    public Array2DRowRealMatrix(double[][] d, boolean copyArray) throws DimensionMismatchException, NoDataException, NullArgumentException {
        if (copyArray) {
            this.copyIn(d);
        } else {
            if (d == null) {
                throw new NullArgumentException();
            }
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
                throw new DimensionMismatchException(d[r].length, nCols);
            }
            this.data = d;
        }
    }

    public Array2DRowRealMatrix(double[] v) {
        int nRows = v.length;
        this.data = new double[nRows][1];
        for (int row = 0; row < nRows; ++row) {
            this.data[row][0] = v[row];
        }
    }

    public RealMatrix createMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
        return new Array2DRowRealMatrix(rowDimension, columnDimension);
    }

    public RealMatrix copy() {
        return new Array2DRowRealMatrix(this.copyOut(), false);
    }

    public Array2DRowRealMatrix add(Array2DRowRealMatrix m) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, m);
        int rowCount = this.getRowDimension();
        int columnCount = this.getColumnDimension();
        double[][] outData = new double[rowCount][columnCount];
        for (int row = 0; row < rowCount; ++row) {
            double[] dataRow = this.data[row];
            double[] mRow = m.data[row];
            double[] outDataRow = outData[row];
            for (int col = 0; col < columnCount; ++col) {
                outDataRow[col] = dataRow[col] + mRow[col];
            }
        }
        return new Array2DRowRealMatrix(outData, false);
    }

    public Array2DRowRealMatrix subtract(Array2DRowRealMatrix m) throws MatrixDimensionMismatchException {
        MatrixUtils.checkSubtractionCompatible(this, m);
        int rowCount = this.getRowDimension();
        int columnCount = this.getColumnDimension();
        double[][] outData = new double[rowCount][columnCount];
        for (int row = 0; row < rowCount; ++row) {
            double[] dataRow = this.data[row];
            double[] mRow = m.data[row];
            double[] outDataRow = outData[row];
            for (int col = 0; col < columnCount; ++col) {
                outDataRow[col] = dataRow[col] - mRow[col];
            }
        }
        return new Array2DRowRealMatrix(outData, false);
    }

    public Array2DRowRealMatrix multiply(Array2DRowRealMatrix m) throws DimensionMismatchException {
        MatrixUtils.checkMultiplicationCompatible(this, m);
        int nRows = this.getRowDimension();
        int nCols = m.getColumnDimension();
        int nSum = this.getColumnDimension();
        double[][] outData = new double[nRows][nCols];
        double[] mCol = new double[nSum];
        double[][] mData = m.data;
        for (int col = 0; col < nCols; ++col) {
            for (int mRow = 0; mRow < nSum; ++mRow) {
                mCol[mRow] = mData[mRow][col];
            }
            for (int row = 0; row < nRows; ++row) {
                double[] dataRow = this.data[row];
                double sum = 0.0;
                for (int i = 0; i < nSum; ++i) {
                    sum += dataRow[i] * mCol[i];
                }
                outData[row][col] = sum;
            }
        }
        return new Array2DRowRealMatrix(outData, false);
    }

    public double[][] getData() {
        return this.copyOut();
    }

    public double[][] getDataRef() {
        return this.data;
    }

    public void setSubMatrix(double[][] subMatrix, int row, int column) throws NoDataException, OutOfRangeException, DimensionMismatchException, NullArgumentException {
        if (this.data == null) {
            if (row > 0) {
                throw new MathIllegalStateException(LocalizedFormats.FIRST_ROWS_NOT_INITIALIZED_YET, row);
            }
            if (column > 0) {
                throw new MathIllegalStateException(LocalizedFormats.FIRST_COLUMNS_NOT_INITIALIZED_YET, column);
            }
            MathUtils.checkNotNull(subMatrix);
            int nRows = subMatrix.length;
            if (nRows == 0) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
            }
            int nCols = subMatrix[0].length;
            if (nCols == 0) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
            }
            this.data = new double[subMatrix.length][nCols];
            for (int i = 0; i < this.data.length; ++i) {
                if (subMatrix[i].length != nCols) {
                    throw new DimensionMismatchException(subMatrix[i].length, nCols);
                }
                System.arraycopy(subMatrix[i], 0, this.data[i + row], column, nCols);
            }
        } else {
            super.setSubMatrix(subMatrix, row, column);
        }
    }

    public double getEntry(int row, int column) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        return this.data[row][column];
    }

    public void setEntry(int row, int column, double value) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        this.data[row][column] = value;
    }

    public void addToEntry(int row, int column, double increment) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        double[] dArray = this.data[row];
        int n = column;
        dArray[n] = dArray[n] + increment;
    }

    public void multiplyEntry(int row, int column, double factor) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        double[] dArray = this.data[row];
        int n = column;
        dArray[n] = dArray[n] * factor;
    }

    public int getRowDimension() {
        return this.data == null ? 0 : this.data.length;
    }

    public int getColumnDimension() {
        return this.data == null || this.data[0] == null ? 0 : this.data[0].length;
    }

    public double[] operate(double[] v) throws DimensionMismatchException {
        int nRows = this.getRowDimension();
        int nCols = this.getColumnDimension();
        if (v.length != nCols) {
            throw new DimensionMismatchException(v.length, nCols);
        }
        double[] out = new double[nRows];
        for (int row = 0; row < nRows; ++row) {
            double[] dataRow = this.data[row];
            double sum = 0.0;
            for (int i = 0; i < nCols; ++i) {
                sum += dataRow[i] * v[i];
            }
            out[row] = sum;
        }
        return out;
    }

    public double[] preMultiply(double[] v) throws DimensionMismatchException {
        int nRows = this.getRowDimension();
        int nCols = this.getColumnDimension();
        if (v.length != nRows) {
            throw new DimensionMismatchException(v.length, nRows);
        }
        double[] out = new double[nCols];
        for (int col = 0; col < nCols; ++col) {
            double sum = 0.0;
            for (int i = 0; i < nRows; ++i) {
                sum += this.data[i][col] * v[i];
            }
            out[col] = sum;
        }
        return out;
    }

    public double walkInRowOrder(RealMatrixChangingVisitor visitor) {
        int rows = this.getRowDimension();
        int columns = this.getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int i = 0; i < rows; ++i) {
            double[] rowI = this.data[i];
            for (int j = 0; j < columns; ++j) {
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    public double walkInRowOrder(RealMatrixPreservingVisitor visitor) {
        int rows = this.getRowDimension();
        int columns = this.getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int i = 0; i < rows; ++i) {
            double[] rowI = this.data[i];
            for (int j = 0; j < columns; ++j) {
                visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    public double walkInRowOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int i = startRow; i <= endRow; ++i) {
            double[] rowI = this.data[i];
            for (int j = startColumn; j <= endColumn; ++j) {
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    public double walkInRowOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int i = startRow; i <= endRow; ++i) {
            double[] rowI = this.data[i];
            for (int j = startColumn; j <= endColumn; ++j) {
                visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    public double walkInColumnOrder(RealMatrixChangingVisitor visitor) {
        int rows = this.getRowDimension();
        int columns = this.getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int j = 0; j < columns; ++j) {
            for (int i = 0; i < rows; ++i) {
                double[] rowI = this.data[i];
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    public double walkInColumnOrder(RealMatrixPreservingVisitor visitor) {
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

    public double walkInColumnOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int j = startColumn; j <= endColumn; ++j) {
            for (int i = startRow; i <= endRow; ++i) {
                double[] rowI = this.data[i];
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    public double walkInColumnOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int j = startColumn; j <= endColumn; ++j) {
            for (int i = startRow; i <= endRow; ++i) {
                visitor.visit(i, j, this.data[i][j]);
            }
        }
        return visitor.end();
    }

    private double[][] copyOut() {
        int nRows = this.getRowDimension();
        double[][] out = new double[nRows][this.getColumnDimension()];
        for (int i = 0; i < nRows; ++i) {
            System.arraycopy(this.data[i], 0, out[i], 0, this.data[i].length);
        }
        return out;
    }

    private void copyIn(double[][] in) throws DimensionMismatchException, NoDataException, NullArgumentException {
        this.setSubMatrix(in, 0, 0);
    }
}

