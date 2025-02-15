/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.BaseMultivariateOptimizer;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.random.RandomVectorGenerator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class BaseMultivariateMultiStartOptimizer<FUNC extends MultivariateFunction>
implements BaseMultivariateOptimizer<FUNC> {
    private final BaseMultivariateOptimizer<FUNC> optimizer;
    private int maxEvaluations;
    private int totalEvaluations;
    private int starts;
    private RandomVectorGenerator generator;
    private PointValuePair[] optima;

    protected BaseMultivariateMultiStartOptimizer(BaseMultivariateOptimizer<FUNC> optimizer, int starts, RandomVectorGenerator generator) {
        if (optimizer == null || generator == null) {
            throw new NullArgumentException();
        }
        if (starts < 1) {
            throw new NotStrictlyPositiveException(starts);
        }
        this.optimizer = optimizer;
        this.starts = starts;
        this.generator = generator;
    }

    public PointValuePair[] getOptima() {
        if (this.optima == null) {
            throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]);
        }
        return (PointValuePair[])this.optima.clone();
    }

    @Override
    public int getMaxEvaluations() {
        return this.maxEvaluations;
    }

    @Override
    public int getEvaluations() {
        return this.totalEvaluations;
    }

    @Override
    public ConvergenceChecker<PointValuePair> getConvergenceChecker() {
        return this.optimizer.getConvergenceChecker();
    }

    @Override
    public PointValuePair optimize(int maxEval, FUNC f, GoalType goal, double[] startPoint) {
        this.maxEvaluations = maxEval;
        RuntimeException lastException = null;
        this.optima = new PointValuePair[this.starts];
        this.totalEvaluations = 0;
        for (int i = 0; i < this.starts; ++i) {
            try {
                this.optima[i] = this.optimizer.optimize(maxEval - this.totalEvaluations, f, goal, i == 0 ? startPoint : this.generator.nextVector());
            } catch (RuntimeException mue) {
                lastException = mue;
                this.optima[i] = null;
            }
            this.totalEvaluations += this.optimizer.getEvaluations();
        }
        this.sortPairs(goal);
        if (this.optima[0] == null) {
            throw lastException;
        }
        return this.optima[0];
    }

    private void sortPairs(final GoalType goal) {
        Arrays.sort(this.optima, new Comparator<PointValuePair>(){

            @Override
            public int compare(PointValuePair o1, PointValuePair o2) {
                if (o1 == null) {
                    return o2 == null ? 0 : 1;
                }
                if (o2 == null) {
                    return -1;
                }
                double v1 = (Double)o1.getValue();
                double v2 = (Double)o2.getValue();
                return goal == GoalType.MINIMIZE ? Double.compare(v1, v2) : Double.compare(v2, v1);
            }
        });
    }
}

