/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optimization.BaseMultivariateOptimizer;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleBounds;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.util.Incrementor;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public abstract class BaseAbstractMultivariateOptimizer<FUNC extends MultivariateFunction>
implements BaseMultivariateOptimizer<FUNC> {
    protected final Incrementor evaluations = new Incrementor();
    private ConvergenceChecker<PointValuePair> checker;
    private GoalType goal;
    private double[] start;
    private double[] lowerBound;
    private double[] upperBound;
    private MultivariateFunction function;

    @Deprecated
    protected BaseAbstractMultivariateOptimizer() {
        this(new SimpleValueChecker());
    }

    protected BaseAbstractMultivariateOptimizer(ConvergenceChecker<PointValuePair> checker) {
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

    @Override
    public ConvergenceChecker<PointValuePair> getConvergenceChecker() {
        return this.checker;
    }

    protected double computeObjectiveValue(double[] point) {
        try {
            this.evaluations.incrementCount();
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
        return this.function.value(point);
    }

    @Override
    @Deprecated
    public PointValuePair optimize(int maxEval, FUNC f, GoalType goalType, double[] startPoint) {
        return this.optimizeInternal(maxEval, f, goalType, new InitialGuess(startPoint));
    }

    public PointValuePair optimize(int maxEval, FUNC f, GoalType goalType, OptimizationData ... optData) {
        return this.optimizeInternal(maxEval, f, goalType, optData);
    }

    @Deprecated
    protected PointValuePair optimizeInternal(int maxEval, FUNC f, GoalType goalType, double[] startPoint) {
        return this.optimizeInternal(maxEval, f, goalType, new InitialGuess(startPoint));
    }

    protected PointValuePair optimizeInternal(int maxEval, FUNC f, GoalType goalType, OptimizationData ... optData) throws TooManyEvaluationsException {
        this.evaluations.setMaximalCount(maxEval);
        this.evaluations.resetCount();
        this.function = f;
        this.goal = goalType;
        this.parseOptimizationData(optData);
        this.checkParameters();
        return this.doOptimize();
    }

    private void parseOptimizationData(OptimizationData ... optData) {
        for (OptimizationData data : optData) {
            if (data instanceof InitialGuess) {
                this.start = ((InitialGuess)data).getInitialGuess();
                continue;
            }
            if (!(data instanceof SimpleBounds)) continue;
            SimpleBounds bounds = (SimpleBounds)data;
            this.lowerBound = bounds.getLower();
            this.upperBound = bounds.getUpper();
        }
    }

    public GoalType getGoalType() {
        return this.goal;
    }

    public double[] getStartPoint() {
        return this.start == null ? null : (double[])this.start.clone();
    }

    public double[] getLowerBound() {
        return this.lowerBound == null ? null : (double[])this.lowerBound.clone();
    }

    public double[] getUpperBound() {
        return this.upperBound == null ? null : (double[])this.upperBound.clone();
    }

    protected abstract PointValuePair doOptimize();

    private void checkParameters() {
        if (this.start != null) {
            double v;
            int i;
            int dim = this.start.length;
            if (this.lowerBound != null) {
                if (this.lowerBound.length != dim) {
                    throw new DimensionMismatchException(this.lowerBound.length, dim);
                }
                for (i = 0; i < dim; ++i) {
                    v = this.start[i];
                    double lo = this.lowerBound[i];
                    if (!(v < lo)) continue;
                    throw new NumberIsTooSmallException(v, (Number)lo, true);
                }
            }
            if (this.upperBound != null) {
                if (this.upperBound.length != dim) {
                    throw new DimensionMismatchException(this.upperBound.length, dim);
                }
                for (i = 0; i < dim; ++i) {
                    v = this.start[i];
                    double hi = this.upperBound[i];
                    if (!(v > hi)) continue;
                    throw new NumberIsTooLargeException(v, (Number)hi, true);
                }
            }
            if (this.lowerBound == null) {
                this.lowerBound = new double[dim];
                for (i = 0; i < dim; ++i) {
                    this.lowerBound[i] = Double.NEGATIVE_INFINITY;
                }
            }
            if (this.upperBound == null) {
                this.upperBound = new double[dim];
                for (i = 0; i < dim; ++i) {
                    this.upperBound[i] = Double.POSITIVE_INFINITY;
                }
            }
        }
    }
}

