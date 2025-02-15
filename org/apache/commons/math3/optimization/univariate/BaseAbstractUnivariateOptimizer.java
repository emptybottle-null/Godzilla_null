/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.univariate.UnivariateOptimizer;
import org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.util.Incrementor;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public abstract class BaseAbstractUnivariateOptimizer
implements UnivariateOptimizer {
    private final ConvergenceChecker<UnivariatePointValuePair> checker;
    private final Incrementor evaluations = new Incrementor();
    private GoalType goal;
    private double searchMin;
    private double searchMax;
    private double searchStart;
    private UnivariateFunction function;

    protected BaseAbstractUnivariateOptimizer(ConvergenceChecker<UnivariatePointValuePair> checker) {
        this.checker = checker;
    }

    @Override
    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    @Override
    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public GoalType getGoalType() {
        return this.goal;
    }

    public double getMin() {
        return this.searchMin;
    }

    public double getMax() {
        return this.searchMax;
    }

    public double getStartValue() {
        return this.searchStart;
    }

    protected double computeObjectiveValue(double point) {
        try {
            this.evaluations.incrementCount();
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
        return this.function.value(point);
    }

    @Override
    public UnivariatePointValuePair optimize(int maxEval, UnivariateFunction f, GoalType goalType, double min, double max, double startValue) {
        if (f == null) {
            throw new NullArgumentException();
        }
        if (goalType == null) {
            throw new NullArgumentException();
        }
        this.searchMin = min;
        this.searchMax = max;
        this.searchStart = startValue;
        this.goal = goalType;
        this.function = f;
        this.evaluations.setMaximalCount(maxEval);
        this.evaluations.resetCount();
        return this.doOptimize();
    }

    @Override
    public UnivariatePointValuePair optimize(int maxEval, UnivariateFunction f, GoalType goalType, double min, double max) {
        return this.optimize(maxEval, f, goalType, min, max, min + 0.5 * (max - min));
    }

    @Override
    public ConvergenceChecker<UnivariatePointValuePair> getConvergenceChecker() {
        return this.checker;
    }

    protected abstract UnivariatePointValuePair doOptimize();
}

