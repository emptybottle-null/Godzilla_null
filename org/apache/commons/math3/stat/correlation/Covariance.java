/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

public class Covariance {
    private final RealMatrix covarianceMatrix;
    private final int n;

    public Covariance() {
        this.covarianceMatrix = null;
        this.n = 0;
    }

    public Covariance(double[][] data, boolean biasCorrected) throws MathIllegalArgumentException, NotStrictlyPositiveException {
        this(new BlockRealMatrix(data), biasCorrected);
    }

    public Covariance(double[][] data) throws MathIllegalArgumentException, NotStrictlyPositiveException {
        this(data, true);
    }

    public Covariance(RealMatrix matrix, boolean biasCorrected) throws MathIllegalArgumentException {
        this.checkSufficientData(matrix);
        this.n = matrix.getRowDimension();
        this.covarianceMatrix = this.computeCovarianceMatrix(matrix, biasCorrected);
    }

    public Covariance(RealMatrix matrix) throws MathIllegalArgumentException {
        this(matrix, true);
    }

    public RealMatrix getCovarianceMatrix() {
        return this.covarianceMatrix;
    }

    public int getN() {
        return this.n;
    }

    protected RealMatrix computeCovarianceMatrix(RealMatrix matrix, boolean biasCorrected) throws MathIllegalArgumentException {
        int dimension = matrix.getColumnDimension();
        Variance variance = new Variance(biasCorrected);
        BlockRealMatrix outMatrix = new BlockRealMatrix(dimension, dimension);
        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < i; ++j) {
                double cov = this.covariance(matrix.getColumn(i), matrix.getColumn(j), biasCorrected);
                outMatrix.setEntry(i, j, cov);
                outMatrix.setEntry(j, i, cov);
            }
            outMatrix.setEntry(i, i, variance.evaluate(matrix.getColumn(i)));
        }
        return outMatrix;
    }

    protected RealMatrix computeCovarianceMatrix(RealMatrix matrix) throws MathIllegalArgumentException {
        return this.computeCovarianceMatrix(matrix, true);
    }

    protected RealMatrix computeCovarianceMatrix(double[][] data, boolean biasCorrected) throws MathIllegalArgumentException, NotStrictlyPositiveException {
        return this.computeCovarianceMatrix(new BlockRealMatrix(data), biasCorrected);
    }

    protected RealMatrix computeCovarianceMatrix(double[][] data) throws MathIllegalArgumentException, NotStrictlyPositiveException {
        return this.computeCovarianceMatrix(data, true);
    }

    public double covariance(double[] xArray, double[] yArray, boolean biasCorrected) throws MathIllegalArgumentException {
        Mean mean = new Mean();
        double result = 0.0;
        int length = xArray.length;
        if (length != yArray.length) {
            throw new MathIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, length, yArray.length);
        }
        if (length < 2) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, length, 2);
        }
        double xMean = mean.evaluate(xArray);
        double yMean = mean.evaluate(yArray);
        for (int i = 0; i < length; ++i) {
            double xDev = xArray[i] - xMean;
            double yDev = yArray[i] - yMean;
            result += (xDev * yDev - result) / (double)(i + 1);
        }
        return biasCorrected ? result * ((double)length / (double)(length - 1)) : result;
    }

    public double covariance(double[] xArray, double[] yArray) throws MathIllegalArgumentException {
        return this.covariance(xArray, yArray, true);
    }

    private void checkSufficientData(RealMatrix matrix) throws MathIllegalArgumentException {
        int nRows = matrix.getRowDimension();
        int nCols = matrix.getColumnDimension();
        if (nRows < 2 || nCols < 1) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_ROWS_AND_COLUMNS, nRows, nCols);
        }
    }
}

