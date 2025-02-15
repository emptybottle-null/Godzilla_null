/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.univariate;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.univariate.BaseUnivariateOptimizer;
import org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.random.RandomGenerator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class UnivariateMultiStartOptimizer<FUNC extends UnivariateFunction>
implements BaseUnivariateOptimizer<FUNC> {
    private final BaseUnivariateOptimizer<FUNC> optimizer;
    private int maxEvaluations;
    private int totalEvaluations;
    private int starts;
    private RandomGenerator generator;
    private UnivariatePointValuePair[] optima;

    public UnivariateMultiStartOptimizer(BaseUnivariateOptimizer<FUNC> optimizer, int starts, RandomGenerator generator) {
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

    @Override
    public ConvergenceChecker<UnivariatePointValuePair> getConvergenceChecker() {
        return this.optimizer.getConvergenceChecker();
    }

    @Override
    public int getMaxEvaluations() {
        return this.maxEvaluations;
    }

    @Override
    public int getEvaluations() {
        return this.totalEvaluations;
    }

    public UnivariatePointValuePair[] getOptima() {
        if (this.optima == null) {
            throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]);
        }
        return (UnivariatePointValuePair[])this.optima.clone();
    }

    @Override
    public UnivariatePointValuePair optimize(int maxEval, FUNC f, GoalType goal, double min, double max) {
        return this.optimize(maxEval, f, goal, min, max, min + 0.5 * (max - min));
    }

    @Override
    public UnivariatePointValuePair optimize(int maxEval, FUNC f, GoalType goal, double min, double max, double startValue) {
        RuntimeException lastException = null;
        this.optima = new UnivariatePointValuePair[this.starts];
        this.totalEvaluations = 0;
        for (int i = 0; i < this.starts; ++i) {
            try {
                double s = i == 0 ? startValue : min + this.generator.nextDouble() * (max - min);
                this.optima[i] = this.optimizer.optimize(maxEval - this.totalEvaluations, f, goal, min, max, s);
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

