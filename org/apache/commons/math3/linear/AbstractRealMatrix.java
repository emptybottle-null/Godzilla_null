/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.util.ArrayList;
import java.util.Locale;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DefaultRealMatrixChangingVisitor;
import org.apache.commons.math3.linear.DefaultRealMatrixPreservingVisitor;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealLinearOperator;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealMatrixChangingVisitor;
import org.apache.commons.math3.linear.RealMatrixFormat;
import org.apache.commons.math3.linear.RealMatrixPreservingVisitor;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public abstract class AbstractRealMatrix
extends RealLinearOperator
implements RealMatrix {
    private static final RealMatrixFormat DEFAULT_FORMAT = RealMatrixFormat.getInstance(Locale.US);

    protected AbstractRealMatrix() {
    }

    protected AbstractRealMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
        if (rowDimension < 1) {
            throw new NotStrictlyPositiveException(rowDimension);
        }
        if (columnDimension < 1) {
            throw new NotStrictlyPositiveException(columnDimension);
        }
    }

    public RealMatrix add(RealMatrix m) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, m);
        int rowCount = this.getRowDimension();
        int columnCount = this.getColumnDimension();
        RealMatrix out = this.createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; ++row) {
            for (int col = 0; col < columnCount; ++col) {
                out.setEntry(row, col, this.getEntry(row, col) + m.getEntry(row, col));
            }
        }
        return out;
    }

    public RealMatrix subtract(RealMatrix m) throws MatrixDimensionMismatchException {
        MatrixUtils.checkSubtractionCompatible(this, m);
        int rowCount = this.getRowDimension();
        int columnCount = this.getColumnDimension();
        RealMatrix out = this.createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; ++row) {
            for (int col = 0; col < columnCount; ++col) {
                out.setEntry(row, col, this.getEntry(row, col) - m.getEntry(row, col));
            }
        }
        return out;
    }

    public RealMatrix scalarAdd(double d) {
        int rowCount = this.getRowDimension();
        int columnCount = this.getColumnDimension();
        RealMatrix out = this.createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; ++row) {
            for (int col = 0; col < columnCount; ++col) {
                out.setEntry(row, col, this.getEntry(row, col) + d);
            }
        }
        return out;
    }

    public RealMatrix scalarMultiply(double d) {
        int rowCount = this.getRowDimension();
        int columnCount = this.getColumnDimension();
        RealMatrix out = this.createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; ++row) {
            for (int col = 0; col < columnCount; ++col) {
                out.setEntry(row, col, this.getEntry(row, col) * d);
            }
        }
        return out;
    }

    public RealMatrix multiply(RealMatrix m) throws DimensionMismatchException {
        MatrixUtils.checkMultiplicationCompatible(this, m);
        int nRows = this.getRowDimension();
        int nCols = m.getColumnDimension();
        int nSum = this.getColumnDimension();
        RealMatrix out = this.createMatrix(nRows, nCols);
        for (int row = 0; row < nRows; ++row) {
            for (int col = 0; col < nCols; ++col) {
                double sum = 0.0;
                for (int i = 0; i < nSum; ++i) {
                    sum += this.getEntry(row, i) * m.getEntry(i, col);
                }
                out.setEntry(row, col, sum);
            }
        }
        return out;
    }

    public RealMatrix preMultiply(RealMatrix m) throws DimensionMismatchException {
        return m.multiply(this);
    }

    public RealMatrix power(int p) throws NotPositiveException, NonSquareMatrixException {
        if (p < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.NOT_POSITIVE_EXPONENT, p);
        }
        if (!this.isSquare()) {
            throw new NonSquareMatrixException(this.getRowDimension(), this.getColumnDimension());
        }
        if (p == 0) {
            return MatrixUtils.createRealIdentityMatrix(this.getRowDimension());
        }
        if (p == 1) {
            return this.copy();
        }
        int power = p - 1;
        char[] binaryRepresentation = Integer.toBinaryString(power).toCharArray();
        ArrayList<Integer> nonZeroPositions = new ArrayList<Integer>();
        int maxI = -1;
        for (int i = 0; i < binaryRepresentation.length; ++i) {
            if (binaryRepresentation[i] != '1') continue;
            int pos = binaryRepresentation.length - i - 1;
            nonZeroPositions.add(pos);
            if (maxI != -1) continue;
            maxI = pos;
        }
        RealMatrix[] results = new RealMatrix[maxI + 1];
        results[0] = this.copy();
        for (int i = 1; i <= maxI; ++i) {
            results[i] = results[i - 1].multiply(results[i - 1]);
        }
        RealMatrix result = this.copy();
        for (Integer i : nonZeroPositions) {
            result = result.multiply(results[i]);
        }
        return result;
    }

    public double[][] getData() {
        double[][] data = new double[this.getRowDimension()][this.getColumnDimension()];
        for (int i = 0; i < data.length; ++i) {
            double[] dataI = data[i];
            for (int j = 0; j < dataI.length; ++j) {
                dataI[j] = this.getEntry(i, j);
            }
        }
        return data;
    }

    public double getNorm() {
        return this.walkInColumnOrder(new RealMatrixPreservingVisitor(){
            private double endRow;
            private double columnSum;
            private double maxColSum;

            public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
                this.endRow = endRow;
                this.columnSum = 0.0;
                this.maxColSum = 0.0;
            }

            public void visit(int row, int column, double value) {
                this.columnSum += FastMath.abs(value);
                if ((double)row == this.endRow) {
                    this.maxColSum = FastMath.max(this.maxColSum, this.columnSum);
                    this.columnSum = 0.0;
                }
            }

            public double end() {
                return this.maxColSum;
            }
        });
    }

    public double getFrobeniusNorm() {
        return this.walkInOptimizedOrder(new RealMatrixPreservingVisitor(){
            private double sum;

            public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
                this.sum = 0.0;
            }

            public void visit(int row, int column, double value) {
                this.sum += value * value;
            }

            public double end() {
                return FastMath.sqrt(this.sum);
            }
        });
    }

    public RealMatrix getSubMatrix(int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        RealMatrix subMatrix = this.createMatrix(endRow - startRow + 1, endColumn - startColumn + 1);
        for (int i = startRow; i <= endRow; ++i) {
            for (int j = startColumn; j <= endColumn; ++j) {
                subMatrix.setEntry(i - startRow, j - startColumn, this.getEntry(i, j));
            }
        }
        return subMatrix;
    }

    public RealMatrix getSubMatrix(final int[] selectedRows, final int[] selectedColumns) throws NullArgumentException, NoDataException, OutOfRangeException {
        MatrixUtils.checkSubMatrixIndex(this, selectedRows, selectedColumns);
        RealMatrix subMatrix = this.createMatrix(selectedRows.length, selectedColumns.length);
        subMatrix.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor(){

            public double visit(int row, int column, double value) {
                return AbstractRealMatrix.this.getEntry(selectedRows[row], selectedColumns[column]);
            }
        });
        return subMatrix;
    }

    public void copySubMatrix(int startRow, int endRow, int startColumn, int endColumn, final double[][] destination) throws OutOfRangeException, NumberIsTooSmallException, MatrixDimensionMismatchException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        int rowsCount = endRow + 1 - startRow;
        int columnsCount = endColumn + 1 - startColumn;
        if (destination.length < rowsCount || destination[0].length < columnsCount) {
            throw new MatrixDimensionMismatchException(destination.length, destination[0].length, rowsCount, columnsCount);
        }
        for (int i = 1; i < rowsCount; ++i) {
            if (destination[i].length >= columnsCount) continue;
            throw new MatrixDimensionMismatchException(destination.length, destination[i].length, rowsCount, columnsCount);
        }
        this.walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor(){
            private int startRow;
            private int startColumn;

            public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
                this.startRow = startRow;
                this.startColumn = startColumn;
            }

            public void visit(int row, int column, double value) {
                destination[row - this.startRow][column - this.startColumn] = value;
            }
        }, startRow, endRow, startColumn, endColumn);
    }

    public void copySubMatrix(int[] selectedRows, int[] selectedColumns, double[][] destination) throws OutOfRangeException, NullArgumentException, NoDataException, MatrixDimensionMismatchException {
        MatrixUtils.checkSubMatrixIndex(this, selectedRows, selectedColumns);
        int nCols = selectedColumns.length;
        if (destination.length < selectedRows.length || destination[0].length < nCols) {
            throw new MatrixDimensionMismatchException(destination.length, destination[0].length, selectedRows.length, selectedColumns.length);
        }
        for (int i = 0; i < selectedRows.length; ++i) {
            double[] destinationI = destination[i];
            if (destinationI.length < nCols) {
                throw new MatrixDimensionMismatchException(destination.length, destinationI.length, selectedRows.length, selectedColumns.length);
            }
            for (int j = 0; j < selectedColumns.length; ++j) {
                destinationI[j] = this.getEntry(selectedRows[i], selectedColumns[j]);
            }
        }
    }

    public void setSubMatrix(double[][] subMatrix, int row, int column) throws NoDataException, OutOfRangeException, DimensionMismatchException, NullArgumentException {
        MathUtils.checkNotNull(subMatrix);
        int nRows = subMatrix.length;
        if (nRows == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        int nCols = subMatrix[0].length;
        if (nCols == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        for (int r = 1; r < nRows; ++r) {
            if (subMatrix[r].length == nCols) continue;
            throw new DimensionMismatchException(nCols, subMatrix[r].length);
        }
        MatrixUtils.checkRowIndex(this, row);
        MatrixUtils.checkColumnIndex(this, column);
        MatrixUtils.checkRowIndex(this, nRows + row - 1);
        MatrixUtils.checkColumnIndex(this, nCols + column - 1);
        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j < nCols; ++j) {
                this.setEntry(row + i, column + j, subMatrix[i][j]);
            }
        }
    }

    public RealMatrix getRowMatrix(int row) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = this.getColumnDimension();
        RealMatrix out = this.createMatrix(1, nCols);
        for (int i = 0; i < nCols; ++i) {
            out.setEntry(0, i, this.getEntry(row, i));
        }
        return out;
    }

    public void setRowMatrix(int row, RealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = this.getColumnDimension();
        if (matrix.getRowDimension() != 1 || matrix.getColumnDimension() != nCols) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols);
        }
        for (int i = 0; i < nCols; ++i) {
            this.setEntry(row, i, matrix.getEntry(0, i));
        }
    }

    public RealMatrix getColumnMatrix(int column) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = this.getRowDimension();
        RealMatrix out = this.createMatrix(nRows, 1);
        for (int i = 0; i < nRows; ++i) {
            out.setEntry(i, 0, this.getEntry(i, column));
        }
        return out;
    }

    public void setColumnMatrix(int column, RealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = this.getRowDimension();
        if (matrix.getRowDimension() != nRows || matrix.getColumnDimension() != 1) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1);
        }
        for (int i = 0; i < nRows; ++i) {
            this.setEntry(i, column, matrix.getEntry(i, 0));
        }
    }

    public RealVector getRowVector(int row) throws OutOfRangeException {
        return new ArrayRealVector(this.getRow(row), false);
    }

    public void setRowVector(int row, RealVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = this.getColumnDimension();
        if (vector.getDimension() != nCols) {
            throw new MatrixDimensionMismatchException(1, vector.getDimension(), 1, nCols);
        }
        for (int i = 0; i < nCols; ++i) {
            this.setEntry(row, i, vector.getEntry(i));
        }
    }

    public RealVector getColumnVector(int column) throws OutOfRangeException {
        return new ArrayRealVector(this.getColumn(column), false);
    }

    public void setColumnVector(int column, RealVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = this.getRowDimension();
        if (vector.getDimension() != nRows) {
            throw new MatrixDimensionMismatchException(vector.getDimension(), 1, nRows, 1);
        }
        for (int i = 0; i < nRows; ++i) {
            this.setEntry(i, column, vector.getEntry(i));
        }
    }

    public double[] getRow(int row) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = this.getColumnDimension();
        double[] out = new double[nCols];
        for (int i = 0; i < nCols; ++i) {
            out[i] = this.getEntry(row, i);
        }
        return out;
    }

    public void setRow(int row, double[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = this.getColumnDimension();
        if (array.length != nCols) {
            throw new MatrixDimensionMismatchException(1, array.length, 1, nCols);
        }
        for (int i = 0; i < nCols; ++i) {
            this.setEntry(row, i, array[i]);
        }
    }

    public double[] getColumn(int column) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = this.getRowDimension();
        double[] out = new double[nRows];
        for (int i = 0; i < nRows; ++i) {
            out[i] = this.getEntry(i, column);
        }
        return out;
    }

    public void setColumn(int column, double[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = this.getRowDimension();
        if (array.length != nRows) {
            throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1);
        }
        for (int i = 0; i < nRows; ++i) {
            this.setEntry(i, column, array[i]);
        }
    }

    public void addToEntry(int row, int column, double increment) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        this.setEntry(row, column, this.getEntry(row, column) + increment);
    }

    public void multiplyEntry(int row, int column, double factor) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        this.setEntry(row, column, this.getEntry(row, column) * factor);
    }

    public RealMatrix transpose() {
        int nRows = this.getRowDimension();
        int nCols = this.getColumnDimension();
        final RealMatrix out = this.createMatrix(nCols, nRows);
        this.walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor(){

            public void visit(int row, int column, double value) {
                out.setEntry(column, row, value);
            }
        });
        return out;
    }

    public boolean isSquare() {
        return this.getColumnDimension() == this.getRowDimension();
    }

    public abstract int getRowDimension();

    public abstract int getColumnDimension();

    public double getTrace() throws NonSquareMatrixException {
        int nCols;
        int nRows = this.getRowDimension();
        if (nRows != (nCols = this.getColumnDimension())) {
            throw new NonSquareMatrixException(nRows, nCols);
        }
        double trace = 0.0;
        for (int i = 0; i < nRows; ++i) {
            trace += this.getEntry(i, i);
        }
        return trace;
    }

    public double[] operate(double[] v) throws DimensionMismatchException {
        int nRows = this.getRowDimension();
        int nCols = this.getColumnDimension();
        if (v.length != nCols) {
            throw new DimensionMismatchException(v.length, nCols);
        }
        double[] out = new double[nRows];
        for (int row = 0; row < nRows; ++row) {
            double sum = 0.0;
            for (int i = 0; i < nCols; ++i) {
                sum += this.getEntry(row, i) * v[i];
            }
            out[row] = sum;
        }
        return out;
    }

    public RealVector operate(RealVector v) throws DimensionMismatchException {
        try {
            return new ArrayRealVector(this.operate(((ArrayRealVector)v).getDataRef()), false);
        } catch (ClassCastException cce) {
            int nRows = this.getRowDimension();
            int nCols = this.getColumnDimension();
            if (v.getDimension() != nCols) {
                throw new DimensionMismatchException(v.getDimension(), nCols);
            }
            double[] out = new double[nRows];
            for (int row = 0; row < nRows; ++row) {
                double sum = 0.0;
                for (int i = 0; i < nCols; ++i) {
                    sum += this.getEntry(row, i) * v.getEntry(i);
                }
                out[row] = sum;
            }
            return new ArrayRealVector(out, false);
        }
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
                sum += this.getEntry(i, col) * v[i];
            }
            out[col] = sum;
        }
        return out;
    }

    public RealVector preMultiply(RealVector v) throws DimensionMismatchException {
        try {
            return new ArrayRealVector(this.preMultiply(((ArrayRealVector)v).getDataRef()), false);
        } catch (ClassCastException cce) {
            int nRows = this.getRowDimension();
            int nCols = this.getColumnDimension();
            if (v.getDimension() != nRows) {
                throw new DimensionMismatchException(v.getDimension(), nRows);
            }
            double[] out = new double[nCols];
            for (int col = 0; col < nCols; ++col) {
                double sum = 0.0;
                for (int i = 0; i < nRows; ++i) {
                    sum += this.getEntry(i, col) * v.getEntry(i);
                }
                out[col] = sum;
            }
            return new ArrayRealVector(out, false);
        }
    }

    public double walkInRowOrder(RealMatrixChangingVisitor visitor) {
        int rows = this.getRowDimension();
        int columns = this.getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                double oldValue = this.getEntry(row, column);
                double newValue = visitor.visit(row, column, oldValue);
                this.setEntry(row, column, newValue);
            }
        }
        return visitor.end();
    }

    public double walkInRowOrder(RealMatrixPreservingVisitor visitor) {
        int rows = this.getRowDimension();
        int columns = this.getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                visitor.visit(row, column, this.getEntry(row, column));
            }
        }
        return visitor.end();
    }

    public double walkInRowOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int row = startRow; row <= endRow; ++row) {
            for (int column = startColumn; column <= endColumn; ++column) {
                double oldValue = this.getEntry(row, column);
                double newValue = visitor.visit(row, column, oldValue);
                this.setEntry(row, column, newValue);
            }
        }
        return visitor.end();
    }

    public double walkInRowOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int row = startRow; row <= endRow; ++row) {
            for (int column = startColumn; column <= endColumn; ++column) {
                visitor.visit(row, column, this.getEntry(row, column));
            }
        }
        return visitor.end();
    }

    public double walkInColumnOrder(RealMatrixChangingVisitor visitor) {
        int rows = this.getRowDimension();
        int columns = this.getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int column = 0; column < columns; ++column) {
            for (int row = 0; row < rows; ++row) {
                double oldValue = this.getEntry(row, column);
                double newValue = visitor.visit(row, column, oldValue);
                this.setEntry(row, column, newValue);
            }
        }
        return visitor.end();
    }

    public double walkInColumnOrder(RealMatrixPreservingVisitor visitor) {
        int rows = this.getRowDimension();
        int columns = this.getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int column = 0; column < columns; ++column) {
            for (int row = 0; row < rows; ++row) {
                visitor.visit(row, column, this.getEntry(row, column));
            }
        }
        return visitor.end();
    }

    public double walkInColumnOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int column = startColumn; column <= endColumn; ++column) {
            for (int row = startRow; row <= endRow; ++row) {
                double oldValue = this.getEntry(row, column);
                double newValue = visitor.visit(row, column, oldValue);
                this.setEntry(row, column, newValue);
            }
        }
        return visitor.end();
    }

    public double walkInColumnOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int column = startColumn; column <= endColumn; ++column) {
            for (int row = startRow; row <= endRow; ++row) {
                visitor.visit(row, column, this.getEntry(row, column));
            }
        }
        return visitor.end();
    }

    public double walkInOptimizedOrder(RealMatrixChangingVisitor visitor) {
        return this.walkInRowOrder(visitor);
    }

    public double walkInOptimizedOrder(RealMatrixPreservingVisitor visitor) {
        return this.walkInRowOrder(visitor);
    }

    public double walkInOptimizedOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        return this.walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
    }

    public double walkInOptimizedOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        return this.walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        String fullClassName = this.getClass().getName();
        String shortClassName = fullClassName.substring(fullClassName.lastIndexOf(46) + 1);
        res.append(shortClassName);
        res.append(DEFAULT_FORMAT.format(this));
        return res.toString();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof RealMatrix)) {
            return false;
        }
        RealMatrix m = (RealMatrix)object;
        int nRows = this.getRowDimension();
        int nCols = this.getColumnDimension();
        if (m.getColumnDimension() != nCols || m.getRowDimension() != nRows) {
            return false;
        }
        for (int row = 0; row < nRows; ++row) {
            for (int col = 0; col < nCols; ++col) {
                if (this.getEntry(row, col) == m.getEntry(row, col)) continue;
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int ret = 7;
        int nRows = this.getRowDimension();
        int nCols = this.getColumnDimension();
        ret = ret * 31 + nRows;
        ret = ret * 31 + nCols;
        for (int row = 0; row < nRows; ++row) {
            for (int col = 0; col < nCols; ++col) {
                ret = ret * 31 + (11 * (row + 1) + 17 * (col + 1)) * MathUtils.hash(this.getEntry(row, col));
            }
        }
        return ret;
    }

    public abstract RealMatrix createMatrix(int var1, int var2) throws NotStrictlyPositiveException;

    public abstract RealMatrix copy();

    public abstract double getEntry(int var1, int var2) throws OutOfRangeException;

    public abstract void setEntry(int var1, int var2, double var3) throws OutOfRangeException;

    static {
        DEFAULT_FORMAT.getFormat().setMinimumFractionDigits(1);
    }
}

