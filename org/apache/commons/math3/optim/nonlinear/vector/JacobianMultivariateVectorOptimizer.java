/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.vector;

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.optim.nonlinear.vector.ModelFunctionJacobian;
import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public abstract class JacobianMultivariateVectorOptimizer
extends MultivariateVectorOptimizer {
    private MultivariateMatrixFunction jacobian;

    protected JacobianMultivariateVectorOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        super(checker);
    }

    protected double[][] computeJacobian(double[] params) {
        return this.jacobian.value(params);
    }

    @Override
    public PointVectorValuePair optimize(OptimizationData ... optData) throws TooManyEvaluationsException, DimensionMismatchException {
        return super.optimize(optData);
    }

    @Override
    protected void parseOptimizationData(OptimizationData ... optData) {
        super.parseOptimizationData(optData);
        for (OptimizationData data : optData) {
            if (!(data instanceof ModelFunctionJacobian)) continue;
            this.jacobian = ((ModelFunctionJacobian)data).getModelFunctionJacobian();
            break;
        }
    }
}

