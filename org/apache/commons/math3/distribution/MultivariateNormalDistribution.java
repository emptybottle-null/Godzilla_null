/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.AbstractMultivariateRealDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.NonPositiveDefiniteMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class MultivariateNormalDistribution
extends AbstractMultivariateRealDistribution {
    private final double[] means;
    private final RealMatrix covarianceMatrix;
    private final RealMatrix covarianceMatrixInverse;
    private final double covarianceMatrixDeterminant;
    private final RealMatrix samplingMatrix;

    public MultivariateNormalDistribution(double[] means, double[][] covariances) throws SingularMatrixException, DimensionMismatchException, NonPositiveDefiniteMatrixException {
        this(new Well19937c(), means, covariances);
    }

    public MultivariateNormalDistribution(RandomGenerator rng, double[] means, double[][] covariances) throws SingularMatrixException, DimensionMismatchException, NonPositiveDefiniteMatrixException {
        super(rng, means.length);
        int dim = means.length;
        if (covariances.length != dim) {
            throw new DimensionMismatchException(covariances.length, dim);
        }
        for (int i = 0; i < dim; ++i) {
            if (dim == covariances[i].length) continue;
            throw new DimensionMismatchException(covariances[i].length, dim);
        }
        this.means = MathArrays.copyOf(means);
        this.covarianceMatrix = new Array2DRowRealMatrix(covariances);
        EigenDecomposition covMatDec = new EigenDecomposition(this.covarianceMatrix);
        this.covarianceMatrixInverse = covMatDec.getSolver().getInverse();
        this.covarianceMatrixDeterminant = covMatDec.getDeterminant();
        double[] covMatEigenvalues = covMatDec.getRealEigenvalues();
        for (int i = 0; i < covMatEigenvalues.length; ++i) {
            if (!(covMatEigenvalues[i] < 0.0)) continue;
            throw new NonPositiveDefiniteMatrixException(covMatEigenvalues[i], i, 0.0);
        }
        Array2DRowRealMatrix covMatEigenvectors = new Array2DRowRealMatrix(dim, dim);
        for (int v = 0; v < dim; ++v) {
            double[] evec = covMatDec.getEigenvector(v).toArray();
            covMatEigenvectors.setColumn(v, evec);
        }
        RealMatrix tmpMatrix = covMatEigenvectors.transpose();
        for (int row = 0; row < dim; ++row) {
            double factor = FastMath.sqrt(covMatEigenvalues[row]);
            for (int col = 0; col < dim; ++col) {
                tmpMatrix.multiplyEntry(row, col, factor);
            }
        }
        this.samplingMatrix = covMatEigenvectors.multiply(tmpMatrix);
    }

    public double[] getMeans() {
        return MathArrays.copyOf(this.means);
    }

    public RealMatrix getCovariances() {
        return this.covarianceMatrix.copy();
    }

    public double density(double[] vals) throws DimensionMismatchException {
        int dim = this.getDimension();
        if (vals.length != dim) {
            throw new DimensionMismatchException(vals.length, dim);
        }
        return FastMath.pow(Math.PI * 2, -0.5 * (double)dim) * FastMath.pow(this.covarianceMatrixDeterminant, -0.5) * this.getExponentTerm(vals);
    }

    public double[] getStandardDeviations() {
        int dim = this.getDimension();
        double[] std = new double[dim];
        double[][] s = this.covarianceMatrix.getData();
        for (int i = 0; i < dim; ++i) {
            std[i] = FastMath.sqrt(s[i][i]);
        }
        return std;
    }

    public double[] sample() {
        int dim = this.getDimension();
        double[] normalVals = new double[dim];
        for (int i = 0; i < dim; ++i) {
            normalVals[i] = this.random.nextGaussian();
        }
        double[] vals = this.samplingMatrix.operate(normalVals);
        for (int i = 0; i < dim; ++i) {
            int n = i;
            vals[n] = vals[n] + this.means[i];
        }
        return vals;
    }

    private double getExponentTerm(double[] values) {
        double[] centered = new double[values.length];
        for (int i = 0; i < centered.length; ++i) {
            centered[i] = values[i] - this.getMeans()[i];
        }
        double[] preMultiplied = this.covarianceMatrixInverse.preMultiply(centered);
        double sum = 0.0;
        for (int i = 0; i < preMultiplied.length; ++i) {
            sum += preMultiplied[i] * centered[i];
        }
        return FastMath.exp(-0.5 * sum);
    }
}

