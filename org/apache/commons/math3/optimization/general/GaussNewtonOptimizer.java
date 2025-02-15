/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.general;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.optimization.SimpleVectorValueChecker;
import org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class GaussNewtonOptimizer
extends AbstractLeastSquaresOptimizer {
    private final boolean useLU;

    @Deprecated
    public GaussNewtonOptimizer() {
        this(true);
    }

    public GaussNewtonOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        this(true, checker);
    }

    @Deprecated
    public GaussNewtonOptimizer(boolean useLU) {
        this(useLU, new SimpleVectorValueChecker());
    }

    public GaussNewtonOptimizer(boolean useLU, ConvergenceChecker<PointVectorValuePair> checker) {
        super(checker);
        this.useLU = useLU;
    }

    @Override
    public PointVectorValuePair doOptimize() {
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
        int iter = 0;
        boolean converged = false;
        while (!converged) {
            ++iter;
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
            if (previous == null || !(converged = checker.converged(iter, previous, current))) continue;
            this.cost = this.computeCost(currentResiduals);
            this.point = current.getPoint();
            return current;
        }
        throw new MathInternalError();
    }
}

