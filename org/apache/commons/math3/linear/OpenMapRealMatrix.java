/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.linear.AbstractRealMatrix;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SparseRealMatrix;
import org.apache.commons.math3.util.OpenIntToDoubleHashMap;

public class OpenMapRealMatrix
extends AbstractRealMatrix
implements SparseRealMatrix,
Serializable {
    private static final long serialVersionUID = -5962461716457143437L;
    private final int rows;
    private final int columns;
    private final OpenIntToDoubleHashMap entries;

    public OpenMapRealMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException, NumberIsTooLargeException {
        super(rowDimension, columnDimension);
        long lRow = rowDimension;
        long lCol = columnDimension;
        if (lRow * lCol >= Integer.MAX_VALUE) {
            throw new NumberIsTooLargeException(lRow * lCol, (Number)Integer.MAX_VALUE, false);
        }
        this.rows = rowDimension;
        this.columns = columnDimension;
        this.entries = new OpenIntToDoubleHashMap(0.0);
    }

    public OpenMapRealMatrix(OpenMapRealMatrix matrix) {
        this.rows = matrix.rows;
        this.columns = matrix.columns;
        this.entries = new OpenIntToDoubleHashMap(matrix.entries);
    }

    public OpenMapRealMatrix copy() {
        return new OpenMapRealMatrix(this);
    }

    public OpenMapRealMatrix createMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException, NumberIsTooLargeException {
        return new OpenMapRealMatrix(rowDimension, columnDimension);
    }

    public int getColumnDimension() {
        return this.columns;
    }

    public OpenMapRealMatrix add(OpenMapRealMatrix m) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, m);
        OpenMapRealMatrix out = new OpenMapRealMatrix(this);
        OpenIntToDoubleHashMap.Iterator iterator = m.entries.iterator();
        while (iterator.hasNext()) {
            iterator.advance();
            int row = iterator.key() / this.columns;
            int col = iterator.key() - row * this.columns;
            out.setEntry(row, col, this.getEntry(row, col) + iterator.value());
        }
        return out;
    }

    public OpenMapRealMatrix subtract(RealMatrix m) throws MatrixDimensionMismatchException {
        try {
            return this.subtract((OpenMapRealMatrix)m);
        } catch (ClassCastException cce) {
            return (OpenMapRealMatrix)super.subtract(m);
        }
    }

    public OpenMapRealMatrix subtract(OpenMapRealMatrix m) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, m);
        OpenMapRealMatrix out = new OpenMapRealMatrix(this);
        OpenIntToDoubleHashMap.Iterator iterator = m.entries.iterator();
        while (iterator.hasNext()) {
            iterator.advance();
            int row = iterator.key() / this.columns;
            int col = iterator.key() - row * this.columns;
            out.setEntry(row, col, this.getEntry(row, col) - iterator.value());
        }
        return out;
    }

    public RealMatrix multiply(RealMatrix m) throws DimensionMismatchException, NumberIsTooLargeException {
        try {
            return this.multiply((OpenMapRealMatrix)m);
        } catch (ClassCastException cce) {
            MatrixUtils.checkMultiplicationCompatible(this, m);
            int outCols = m.getColumnDimension();
            BlockRealMatrix out = new BlockRealMatrix(this.rows, outCols);
            OpenIntToDoubleHashMap.Iterator iterator = this.entries.iterator();
            while (iterator.hasNext()) {
                iterator.advance();
                double value = iterator.value();
                int key = iterator.key();
                int i = key / this.columns;
                int k = key % this.columns;
                for (int j = 0; j < outCols; ++j) {
                    out.addToEntry(i, j, value * m.getEntry(k, j));
                }
            }
            return out;
        }
    }

    public OpenMapRealMatrix multiply(OpenMapRealMatrix m) throws DimensionMismatchException, NumberIsTooLargeException {
        MatrixUtils.checkMultiplicationCompatible(this, m);
        int outCols = m.getColumnDimension();
        OpenMapRealMatrix out = new OpenMapRealMatrix(this.rows, outCols);
        OpenIntToDoubleHashMap.Iterator iterator = this.entries.iterator();
        while (iterator.hasNext()) {
            iterator.advance();
            double value = iterator.value();
            int key = iterator.key();
            int i = key / this.columns;
            int k = key % this.columns;
            for (int j = 0; j < outCols; ++j) {
                int rightKey = m.computeKey(k, j);
                if (!m.entries.containsKey(rightKey)) continue;
                int outKey = out.computeKey(i, j);
                double outValue = out.entries.get(outKey) + value * m.entries.get(rightKey);
                if (outValue == 0.0) {
                    out.entries.remove(outKey);
                    continue;
                }
                out.entries.put(outKey, outValue);
            }
        }
        return out;
    }

    public double getEntry(int row, int column) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        MatrixUtils.checkColumnIndex(this, column);
        return this.entries.get(this.computeKey(row, column));
    }

    public int getRowDimension() {
        return this.rows;
    }

    public void setEntry(int row, int column, double value) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        MatrixUtils.checkColumnIndex(this, column);
        if (value == 0.0) {
            this.entries.remove(this.computeKey(row, column));
        } else {
            this.entries.put(this.computeKey(row, column), value);
        }
    }

    public void addToEntry(int row, int column, double increment) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        MatrixUtils.checkColumnIndex(this, column);
        int key = this.computeKey(row, column);
        double value = this.entries.get(key) + increment;
        if (value == 0.0) {
            this.entries.remove(key);
        } else {
            this.entries.put(key, value);
        }
    }

    public void multiplyEntry(int row, int column, double factor) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        MatrixUtils.checkColumnIndex(this, column);
        int key = this.computeKey(row, column);
        double value = this.entries.get(key) * factor;
        if (value == 0.0) {
            this.entries.remove(key);
        } else {
            this.entries.put(key, value);
        }
    }

    private int computeKey(int row, int column) {
        return row * this.columns + column;
    }
}

