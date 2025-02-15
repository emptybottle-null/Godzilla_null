/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.linear.AbstractRealMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class DiagonalMatrix
extends AbstractRealMatrix
implements Serializable {
    private static final long serialVersionUID = 20121229L;
    private final double[] data;

    public DiagonalMatrix(int dimension) throws NotStrictlyPositiveException {
        super(dimension, dimension);
        this.data = new double[dimension];
    }

    public DiagonalMatrix(double[] d) {
        this(d, true);
    }

    public DiagonalMatrix(double[] d, boolean copyArray) throws NullArgumentException {
        MathUtils.checkNotNull(d);
        this.data = copyArray ? (double[])d.clone() : d;
    }

    public RealMatrix createMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException, DimensionMismatchException {
        if (rowDimension != columnDimension) {
            throw new DimensionMismatchException(rowDimension, columnDimension);
        }
        return new DiagonalMatrix(rowDimension);
    }

    public RealMatrix copy() {
        return new DiagonalMatrix(this.data);
    }

    public DiagonalMatrix add(DiagonalMatrix m) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, m);
        int dim = this.getRowDimension();
        double[] outData = new double[dim];
        for (int i = 0; i < dim; ++i) {
            outData[i] = this.data[i] + m.data[i];
        }
        return new DiagonalMatrix(outData, false);
    }

    public DiagonalMatrix subtract(DiagonalMatrix m) throws MatrixDimensionMismatchException {
        MatrixUtils.checkSubtractionCompatible(this, m);
        int dim = this.getRowDimension();
        double[] outData = new double[dim];
        for (int i = 0; i < dim; ++i) {
            outData[i] = this.data[i] - m.data[i];
        }
        return new DiagonalMatrix(outData, false);
    }

    public DiagonalMatrix multiply(DiagonalMatrix m) throws DimensionMismatchException {
        MatrixUtils.checkMultiplicationCompatible(this, m);
        int dim = this.getRowDimension();
        double[] outData = new double[dim];
        for (int i = 0; i < dim; ++i) {
            outData[i] = this.data[i] * m.data[i];
        }
        return new DiagonalMatrix(outData, false);
    }

    public RealMatrix multiply(RealMatrix m) throws DimensionMismatchException {
        if (m instanceof DiagonalMatrix) {
            return this.multiply((DiagonalMatrix)m);
        }
        MatrixUtils.checkMultiplicationCompatible(this, m);
        int nRows = m.getRowDimension();
        int nCols = m.getColumnDimension();
        double[][] product = new double[nRows][nCols];
        for (int r = 0; r < nRows; ++r) {
            for (int c = 0; c < nCols; ++c) {
                product[r][c] = this.data[r] * m.getEntry(r, c);
            }
        }
        return new Array2DRowRealMatrix(product, false);
    }

    public double[][] getData() {
        int dim = this.getRowDimension();
        double[][] out = new double[dim][dim];
        for (int i = 0; i < dim; ++i) {
            out[i][i] = this.data[i];
        }
        return out;
    }

    public double[] getDataRef() {
        return this.data;
    }

    public double getEntry(int row, int column) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        return row == column ? this.data[row] : 0.0;
    }

    public void setEntry(int row, int column, double value) throws OutOfRangeException, NumberIsTooLargeException {
        if (row == column) {
            MatrixUtils.checkRowIndex(this, row);
            this.data[row] = value;
        } else {
            this.ensureZero(value);
        }
    }

    public void addToEntry(int row, int column, double increment) throws OutOfRangeException, NumberIsTooLargeException {
        if (row == column) {
            MatrixUtils.checkRowIndex(this, row);
            int n = row;
            this.data[n] = this.data[n] + increment;
        } else {
            this.ensureZero(increment);
        }
    }

    public void multiplyEntry(int row, int column, double factor) throws OutOfRangeException {
        if (row == column) {
            MatrixUtils.checkRowIndex(this, row);
            int n = row;
            this.data[n] = this.data[n] * factor;
        }
    }

    public int getRowDimension() {
        return this.data.length;
    }

    public int getColumnDimension() {
        return this.data.length;
    }

    public double[] operate(double[] v) throws DimensionMismatchException {
        return this.multiply(new DiagonalMatrix(v, false)).getDataRef();
    }

    public double[] preMultiply(double[] v) throws DimensionMismatchException {
        return this.operate(v);
    }

    public RealVector preMultiply(RealVector v) throws DimensionMismatchException {
        double[] vectorData = v instanceof ArrayRealVector ? ((ArrayRealVector)v).getDataRef() : v.toArray();
        return MatrixUtils.createRealVector(this.preMultiply(vectorData));
    }

    private void ensureZero(double value) throws NumberIsTooLargeException {
        if (!Precision.equals(0.0, value, 1)) {
            throw new NumberIsTooLargeException(FastMath.abs(value), (Number)0, true);
        }
    }

    public DiagonalMatrix inverse() throws SingularMatrixException {
        return this.inverse(0.0);
    }

    public DiagonalMatrix inverse(double threshold) throws SingularMatrixException {
        if (this.isSingular(threshold)) {
            throw new SingularMatrixException();
        }
        double[] result = new double[this.data.length];
        for (int i = 0; i < this.data.length; ++i) {
            result[i] = 1.0 / this.data[i];
        }
        return new DiagonalMatrix(result, false);
    }

    public boolean isSingular(double threshold) {
        for (int i = 0; i < this.data.length; ++i) {
            if (!Precision.equals(this.data[i], 0.0, threshold)) continue;
            return true;
        }
        return false;
    }
}

