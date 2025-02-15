/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class VectorialCovariance
implements Serializable {
    private static final long serialVersionUID = 4118372414238930270L;
    private final double[] sums;
    private final double[] productsSums;
    private final boolean isBiasCorrected;
    private long n;

    public VectorialCovariance(int dimension, boolean isBiasCorrected) {
        this.sums = new double[dimension];
        this.productsSums = new double[dimension * (dimension + 1) / 2];
        this.n = 0L;
        this.isBiasCorrected = isBiasCorrected;
    }

    public void increment(double[] v) throws DimensionMismatchException {
        if (v.length != this.sums.length) {
            throw new DimensionMismatchException(v.length, this.sums.length);
        }
        int k = 0;
        for (int i = 0; i < v.length; ++i) {
            int n = i;
            this.sums[n] = this.sums[n] + v[i];
            for (int j = 0; j <= i; ++j) {
                int n2 = k++;
                this.productsSums[n2] = this.productsSums[n2] + v[i] * v[j];
            }
        }
        ++this.n;
    }

    public RealMatrix getResult() {
        int dimension = this.sums.length;
        RealMatrix result = MatrixUtils.createRealMatrix(dimension, dimension);
        if (this.n > 1L) {
            double c = 1.0 / (double)(this.n * (this.isBiasCorrected ? this.n - 1L : this.n));
            int k = 0;
            for (int i = 0; i < dimension; ++i) {
                for (int j = 0; j <= i; ++j) {
                    double e = c * ((double)this.n * this.productsSums[k++] - this.sums[i] * this.sums[j]);
                    result.setEntry(i, j, e);
                    result.setEntry(j, i, e);
                }
            }
        }
        return result;
    }

    public long getN() {
        return this.n;
    }

    public void clear() {
        this.n = 0L;
        Arrays.fill(this.sums, 0.0);
        Arrays.fill(this.productsSums, 0.0);
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.isBiasCorrected ? 1231 : 1237);
        result = 31 * result + (int)(this.n ^ this.n >>> 32);
        result = 31 * result + Arrays.hashCode(this.productsSums);
        result = 31 * result + Arrays.hashCode(this.sums);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VectorialCovariance)) {
            return false;
        }
        VectorialCovariance other = (VectorialCovariance)obj;
        if (this.isBiasCorrected != other.isBiasCorrected) {
            return false;
        }
        if (this.n != other.n) {
            return false;
        }
        if (!Arrays.equals(this.productsSums, other.productsSums)) {
            return false;
        }
        return Arrays.equals(this.sums, other.sums);
    }
}

