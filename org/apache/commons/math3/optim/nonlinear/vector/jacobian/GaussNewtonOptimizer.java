/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.vector.jacobian;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class GaussNewtonOptimizer
extends AbstractLeastSquaresOptimizer {
    private final boolean useLU;

    public GaussNewtonOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        this(true, checker);
    }

    public GaussNewtonOptimizer(boolean useLU, ConvergenceChecker<PointVectorValuePair> checker) {
        super(checker);
        this.useLU = useLU;
    }

    @Override
    public PointVectorValuePair doOptimize() {
        this.checkParameters();
        ConvergenceChecker<PointVectorValuePair> checker = this.getConvergenceChecker();
        if (checker == null) {
            throw new NullArgumentException();
        }
        double[] targetValues = this.getTarget();
        int nR = targetValues.length;
        RealMatrix weightMatrix = this.getWeight();
        double[] residualsWeights = new double[nR];
        for (int i = 0; i < nR; ++i) {
            residualsWeights[i] = weightMatrix.getEntry(i, i);
        }
        double[] currentPoint = this.getStartPoint();
        int nC = currentPoint.length;
        PointVectorValuePair current = null;
        boolean converged = false;
        while (!converged) {
            this.incrementIterationCount();
            PointVectorValuePair previous = current;
            double[] currentObjective = this.computeObjectiveValue(currentPoint);
            double[] currentResiduals = this.computeResiduals(currentObjective);
            RealMatrix weightedJacobian = this.computeWeightedJacobian(currentPoint);
            current = new PointVectorValuePair(currentPoint, currentObjective);
            double[] b = new double[nC];
            double[][] a = new double[nC][nC];
            for (int i = 0; i < nR; ++i) {
                double[] grad = weightedJacobian.getRow(i);
                double weight = residualsWeights[i];
                double residual = currentResiduals[i];
                double wr = weight * residual;
                for (int j = 0; j < nC; ++j) {
                    int n = j;
                    b[n] = b[n] + wr * grad[j];
                }
                for (int k = 0; k < nC; ++k) {
                    double[] ak = a[k];
                    double wgk = weight * grad[k];
                    for (int l = 0; l < nC; ++l) {
                        int n = l;
                        ak[n] = ak[n] + wgk * grad[l];
                    }
                }
            }
            if (previous != null && (converged = checker.converged(this.getIterations(), previous, current))) {
                this.setCost(this.computeCost(currentResiduals));
                return current;
            }
            try {
                BlockRealMatrix mA = new BlockRealMatrix(a);
                DecompositionSolver solver = this.useLU ? new LUDecomposition(mA).getSolver() : new QRDecomposition(mA).getSolver();
                double[] dX = solver.solve(new ArrayRealVector(b, false)).toArray();
                for (int i = 0; i < nC; ++i) {
                    int n = i;
                    currentPoint[n] = currentPoint[n] + dX[i];
                }
            } catch (SingularMatrixException e) {
                throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, new Object[0]);
            }
        }
        throw new MathInternalError();
    }

    private void checkParameters() {
        if (this.getLowerBound() != null || this.getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }
}

