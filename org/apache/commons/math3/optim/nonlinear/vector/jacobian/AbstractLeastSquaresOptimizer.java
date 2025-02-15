/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.vector.jacobian;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.optim.nonlinear.vector.JacobianMultivariateVectorOptimizer;
import org.apache.commons.math3.optim.nonlinear.vector.Weight;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public abstract class AbstractLeastSquaresOptimizer
extends JacobianMultivariateVectorOptimizer {
    private RealMatrix weightMatrixSqrt;
    private double cost;

    protected AbstractLeastSquaresOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        super(checker);
    }

    protected RealMatrix computeWeightedJacobian(double[] params) {
        return this.weightMatrixSqrt.multiply(MatrixUtils.createRealMatrix(this.computeJacobian(params)));
    }

    protected double computeCost(double[] residuals) {
        ArrayRealVector r = new ArrayRealVector(residuals);
        return FastMath.sqrt(r.dotProduct(this.getWeight().operate(r)));
    }

    public double getRMS() {
        return FastMath.sqrt(this.getChiSquare() / (double)this.getTargetSize());
    }

    public double getChiSquare() {
        return this.cost * this.cost;
    }

    public RealMatrix getWeightSquareRoot() {
        return this.weightMatrixSqrt.copy();
    }

    protected void setCost(double cost) {
        this.cost = cost;
    }

    public double[][] computeCovariances(double[] params, double threshold) {
        RealMatrix j = this.computeWeightedJacobian(params);
        RealMatrix jTj = j.transpose().multiply(j);
        DecompositionSolver solver = new QRDecomposition(jTj, threshold).getSolver();
        return solver.getInverse().getData();
    }

    public double[] computeSigma(double[] params, double covarianceSingularityThreshold) {
        int nC = params.length;
        double[] sig = new double[nC];
        double[][] cov = this.computeCovariances(params, covarianceSingularityThreshold);
        for (int i = 0; i < nC; ++i) {
            sig[i] = FastMath.sqrt(cov[i][i]);
        }
        return sig;
    }

    @Override
    public PointVectorValuePair optimize(OptimizationData ... optData) throws TooManyEvaluationsException {
        return super.optimize(optData);
    }

    protected double[] computeResiduals(double[] objectiveValue) {
        double[] target = this.getTarget();
        if (objectiveValue.length != target.length) {
            throw new DimensionMismatchException(target.length, objectiveValue.length);
        }
        double[] residuals = new double[target.length];
        for (int i = 0; i < target.length; ++i) {
            residuals[i] = target[i] - objectiveValue[i];
        }
        return residuals;
    }

    @Override
    protected void parseOptimizationData(OptimizationData ... optData) {
        super.parseOptimizationData(optData);
        for (OptimizationData data : optData) {
            if (!(data instanceof Weight)) continue;
            this.weightMatrixSqrt = this.squareRoot(((Weight)data).getWeight());
            break;
        }
    }

    private RealMatrix squareRoot(RealMatrix m) {
        if (m instanceof DiagonalMatrix) {
            int dim = m.getRowDimension();
            DiagonalMatrix sqrtM = new DiagonalMatrix(dim);
            for (int i = 0; i < dim; ++i) {
                sqrtM.setEntry(i, i, FastMath.sqrt(m.getEntry(i, i)));
            }
            return sqrtM;
        }
        EigenDecomposition dec = new EigenDecomposition(m);
        return dec.getSquareRoot();
    }
}

