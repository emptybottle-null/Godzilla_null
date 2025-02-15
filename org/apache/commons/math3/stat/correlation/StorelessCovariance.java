/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance;

public class StorelessCovariance
extends Covariance {
    private StorelessBivariateCovariance[] covMatrix;
    private int dimension;

    public StorelessCovariance(int dim) {
        this(dim, true);
    }

    public StorelessCovariance(int dim, boolean biasCorrected) {
        this.dimension = dim;
        this.covMatrix = new StorelessBivariateCovariance[this.dimension * (this.dimension + 1) / 2];
        this.initializeMatrix(biasCorrected);
    }

    private void initializeMatrix(boolean biasCorrected) {
        for (int i = 0; i < this.dimension; ++i) {
            for (int j = 0; j < this.dimension; ++j) {
                this.setElement(i, j, new StorelessBivariateCovariance(biasCorrected));
            }
        }
    }

    private int indexOf(int i, int j) {
        return j < i ? i * (i + 1) / 2 + j : j * (j + 1) / 2 + i;
    }

    private StorelessBivariateCovariance getElement(int i, int j) {
        return this.covMatrix[this.indexOf(i, j)];
    }

    private void setElement(int i, int j, StorelessBivariateCovariance cov) {
        this.covMatrix[this.indexOf((int)i, (int)j)] = cov;
    }

    public double getCovariance(int xIndex, int yIndex) throws NumberIsTooSmallException {
        return this.getElement(xIndex, yIndex).getResult();
    }

    public void increment(double[] data) throws DimensionMismatchException {
        int length = data.length;
        if (length != this.dimension) {
            throw new DimensionMismatchException(length, this.dimension);
        }
        for (int i = 0; i < length; ++i) {
            for (int j = i; j < length; ++j) {
                this.getElement(i, j).increment(data[i], data[j]);
            }
        }
    }

    public void append(StorelessCovariance sc) throws DimensionMismatchException {
        if (sc.dimension != this.dimension) {
            throw new DimensionMismatchException(sc.dimension, this.dimension);
        }
        for (int i = 0; i < this.dimension; ++i) {
            for (int j = i; j < this.dimension; ++j) {
                this.getElement(i, j).append(sc.getElement(i, j));
            }
        }
    }

    public RealMatrix getCovarianceMatrix() throws NumberIsTooSmallException {
        return MatrixUtils.createRealMatrix(this.getData());
    }

    public double[][] getData() throws NumberIsTooSmallException {
        double[][] data = new double[this.dimension][this.dimension];
        for (int i = 0; i < this.dimension; ++i) {
            for (int j = 0; j < this.dimension; ++j) {
                data[i][j] = this.getElement(i, j).getResult();
            }
        }
        return data;
    }

    public int getN() throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }
}

