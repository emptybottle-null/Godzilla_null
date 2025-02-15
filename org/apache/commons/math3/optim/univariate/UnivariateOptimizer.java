/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optim.BaseOptimizer;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.univariate.SearchInterval;
import org.apache.commons.math3.optim.univariate.UnivariateObjectiveFunction;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class UnivariateOptimizer
extends BaseOptimizer<UnivariatePointValuePair> {
    private UnivariateFunction function;
    private GoalType goal;
    private double start;
    private double min;
    private double max;

    protected UnivariateOptimizer(ConvergenceChecker<UnivariatePointValuePair> checker) {
        super(checker);
    }

    @Override
    public UnivariatePointValuePair optimize(OptimizationData ... optData) throws TooManyEvaluationsException {
        return (UnivariatePointValuePair)super.optimize(optData);
    }

    public GoalType getGoalType() {
        return this.goal;
    }

    @Override
    protected void parseOptimizationData(OptimizationData ... optData) {
        super.parseOptimizationData(optData);
        for (OptimizationData data : optData) {
            if (data instanceof SearchInterval) {
                SearchInterval interval = (SearchInterval)data;
                this.min = interval.getMin();
                this.max = interval.getMax();
                this.start = interval.getStartValue();
                continue;
            }
            if (data instanceof UnivariateObjectiveFunction) {
                this.function = ((UnivariateObjectiveFunction)data).getObjectiveFunction();
                continue;
            }
            if (!(data instanceof GoalType)) continue;
            this.goal = (GoalType)data;
        }
    }

    public double getStartValue() {
        return this.start;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    protected double computeObjectiveValue(double x) {
        super.incrementEvaluationCount();
        return this.function.value(x);
    }
}

