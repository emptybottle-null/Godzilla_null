/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.univariate;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.univariate.SearchInterval;
import org.apache.commons.math3.optim.univariate.UnivariateOptimizer;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.random.RandomGenerator;

public class MultiStartUnivariateOptimizer
extends UnivariateOptimizer {
    private final UnivariateOptimizer optimizer;
    private int totalEvaluations;
    private int starts;
    private RandomGenerator generator;
    private UnivariatePointValuePair[] optima;
    private OptimizationData[] optimData;
    private int maxEvalIndex = -1;
    private int searchIntervalIndex = -1;

    public MultiStartUnivariateOptimizer(UnivariateOptimizer optimizer, int starts, RandomGenerator generator) {
        super(optimizer.getConvergenceChecker());
        if (starts < 1) {
            throw new NotStrictlyPositiveException(starts);
        }
        this.optimizer = optimizer;
        this.starts = starts;
        this.generator = generator;
    }

    public int getEvaluations() {
        return this.totalEvaluations;
    }

    public UnivariatePointValuePair[] getOptima() {
        if (this.optima == null) {
            throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]);
        }
        return (UnivariatePointValuePair[])this.optima.clone();
    }

    public UnivariatePointValuePair optimize(OptimizationData ... optData) {
        this.optimData = optData;
        return super.optimize(optData);
    }

    protected UnivariatePointValuePair doOptimize() {
        for (int i = 0; i < this.optimData.length; ++i) {
            if (this.optimData[i] instanceof MaxEval) {
                this.optimData[i] = null;
                this.maxEvalIndex = i;
                continue;
            }
            if (!(this.optimData[i] instanceof SearchInterval)) continue;
            this.optimData[i] = null;
            this.searchIntervalIndex = i;
        }
        if (this.maxEvalIndex == -1) {
            throw new MathIllegalStateException();
        }
        if (this.searchIntervalIndex == -1) {
            throw new MathIllegalStateException();
        }
        RuntimeException lastException = null;
        this.optima = new UnivariatePointValuePair[this.starts];
        this.totalEvaluations = 0;
        int maxEval = this.getMaxEvaluations();
        double min = this.getMin();
        double max = this.getMax();
        double startValue = this.getStartValue();
        for (int i = 0; i < this.starts; ++i) {
            try {
                this.optimData[this.maxEvalIndex] = new MaxEval(maxEval - this.totalEvaluations);
                double s = i == 0 ? startValue : min + this.generator.nextDouble() * (max - min);
                this.optimData[this.searchIntervalIndex] = new SearchInterval(min, max, s);
                this.optima[i] = this.optimizer.optimize(this.optimData);
            } catch (RuntimeException mue) {
                lastException = mue;
                this.optima[i] = null;
            }
            this.totalEvaluations += this.optimizer.getEvaluations();
        }
        this.sortPairs(this.getGoalType());
        if (this.optima[0] == null) {
            throw lastException;
        }
        return this.optima[0];
    }

    private void sortPairs(final GoalType goal) {
        Arrays.sort(this.optima, new Comparator<UnivariatePointValuePair>(){

            @Override
            public int compare(UnivariatePointValuePair o1, UnivariatePointValuePair o2) {
                if (o1 == null) {
                    return o2 == null ? 0 : 1;
                }
                if (o2 == null) {
                    return -1;
                }
                double v1 = o1.getValue();
                double v2 = o2.getValue();
                return goal == GoalType.MINIMIZE ? Double.compare(v1, v2) : Double.compare(v2, v1);
            }
        });
    }
}

