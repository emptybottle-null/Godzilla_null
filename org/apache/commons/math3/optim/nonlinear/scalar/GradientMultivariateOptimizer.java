/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunctionGradient;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class GradientMultivariateOptimizer
extends MultivariateOptimizer {
    private MultivariateVectorFunction gradient;

    protected GradientMultivariateOptimizer(ConvergenceChecker<PointValuePair> checker) {
        super(checker);
    }

    protected double[] computeObjectiveGradient(double[] params) {
        return this.gradient.value(params);
    }

    @Override
    public PointValuePair optimize(OptimizationData ... optData) throws TooManyEvaluationsException {
        return super.optimize(optData);
    }

    @Override
    protected void parseOptimizationData(OptimizationData ... optData) {
        super.parseOptimizationData(optData);
        for (OptimizationData data : optData) {
            if (!(data instanceof ObjectiveFunctionGradient)) continue;
            this.gradient = ((ObjectiveFunctionGradient)data).getObjectiveFunctionGradient();
            break;
        }
    }
}

