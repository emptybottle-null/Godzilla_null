/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optim.BaseMultivariateOptimizer;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunction;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class MultivariateOptimizer
extends BaseMultivariateOptimizer<PointValuePair> {
    private MultivariateFunction function;
    private GoalType goal;

    protected MultivariateOptimizer(ConvergenceChecker<PointValuePair> checker) {
        super(checker);
    }

    @Override
    public PointValuePair optimize(OptimizationData ... optData) throws TooManyEvaluationsException {
        return (PointValuePair)super.optimize(optData);
    }

    @Override
    protected void parseOptimizationData(OptimizationData ... optData) {
        super.parseOptimizationData(optData);
        for (OptimizationData data : optData) {
            if (data instanceof GoalType) {
                this.goal = (GoalType)data;
                continue;
            }
            if (!(data instanceof ObjectiveFunction)) continue;
            this.function = ((ObjectiveFunction)data).getObjectiveFunction();
        }
    }

    public GoalType getGoalType() {
        return this.goal;
    }

    public double computeObjectiveValue(double[] params) {
        super.incrementEvaluationCount();
        return this.function.value(params);
    }
}

