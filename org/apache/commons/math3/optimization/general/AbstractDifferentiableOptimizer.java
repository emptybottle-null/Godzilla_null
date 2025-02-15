/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.general;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.analysis.differentiation.GradientFunction;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public abstract class AbstractDifferentiableOptimizer
extends BaseAbstractMultivariateOptimizer<MultivariateDifferentiableFunction> {
    private MultivariateVectorFunction gradient;

    protected AbstractDifferentiableOptimizer(ConvergenceChecker<PointValuePair> checker) {
        super(checker);
    }

    protected double[] computeObjectiveGradient(double[] evaluationPoint) {
        return this.gradient.value(evaluationPoint);
    }

    @Override
    @Deprecated
    protected PointValuePair optimizeInternal(int maxEval, MultivariateDifferentiableFunction f, GoalType goalType, double[] startPoint) {
        return this.optimizeInternal(maxEval, f, goalType, new OptimizationData[]{new InitialGuess(startPoint)});
    }

    @Override
    protected PointValuePair optimizeInternal(int maxEval, MultivariateDifferentiableFunction f, GoalType goalType, OptimizationData ... optData) {
        this.gradient = new GradientFunction(f);
        return super.optimizeInternal(maxEval, f, goalType, optData);
    }
}

