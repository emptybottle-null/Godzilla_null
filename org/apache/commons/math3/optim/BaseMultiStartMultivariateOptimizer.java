/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optim.BaseMultivariateOptimizer;
import org.apache.commons.math3.optim.InitialGuess;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.random.RandomVectorGenerator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class BaseMultiStartMultivariateOptimizer<PAIR>
extends BaseMultivariateOptimizer<PAIR> {
    private final BaseMultivariateOptimizer<PAIR> optimizer;
    private int totalEvaluations;
    private int starts;
    private RandomVectorGenerator generator;
    private OptimizationData[] optimData;
    private int maxEvalIndex = -1;
    private int initialGuessIndex = -1;

    public BaseMultiStartMultivariateOptimizer(BaseMultivariateOptimizer<PAIR> optimizer, int starts, RandomVectorGenerator generator) {
        super(optimizer.getConvergenceChecker());
        if (starts < 1) {
            throw new NotStrictlyPositiveException(starts);
        }
        this.optimizer = optimizer;
        this.starts = starts;
        this.generator = generator;
    }

    @Override
    public int getEvaluations() {
        return this.totalEvaluations;
    }

    public abstract PAIR[] getOptima();

    @Override
    public PAIR optimize(OptimizationData ... optData) {
        this.optimData = optData;
        return super.optimize(optData);
    }

    @Override
    protected PAIR doOptimize() {
        for (int i = 0; i < this.optimData.length; ++i) {
            if (this.optimData[i] instanceof MaxEval) {
                this.optimData[i] = null;
                this.maxEvalIndex = i;
            }
            if (!(this.optimData[i] instanceof InitialGuess)) continue;
            this.optimData[i] = null;
            this.initialGuessIndex = i;
        }
        if (this.maxEvalIndex == -1) {
            throw new MathIllegalStateException();
        }
        if (this.initialGuessIndex == -1) {
            throw new MathIllegalStateException();
        }
        RuntimeException lastException = null;
        this.totalEvaluations = 0;
        this.clear();
        int maxEval = this.getMaxEvaluations();
        double[] min = this.getLowerBound();
        double[] max = this.getUpperBound();
        double[] startPoint = this.getStartPoint();
        for (int i = 0; i < this.starts; ++i) {
            try {
                this.optimData[this.maxEvalIndex] = new MaxEval(maxEval - this.totalEvaluations);
                double[] s = null;
                if (i == 0) {
                    s = startPoint;
                } else {
                    int attempts = 0;
                    while (s == null) {
                        if (attempts++ >= this.getMaxEvaluations()) {
                            throw new TooManyEvaluationsException(this.getMaxEvaluations());
                        }
                        s = this.generator.nextVector();
                        for (int k = 0; s != null && k < s.length; ++k) {
                            if (!(min != null && s[k] < min[k]) && (max == null || !(s[k] > max[k]))) continue;
                            s = null;
                        }
                    }
                }
                this.optimData[this.initialGuessIndex] = new InitialGuess(s);
                PAIR result = this.optimizer.optimize(this.optimData);
                this.store(result);
            } catch (RuntimeException mue) {
                lastException = mue;
            }
            this.totalEvaluations += this.optimizer.getEvaluations();
        }
        PAIR[] optima = this.getOptima();
        if (optima.length == 0) {
            throw lastException;
        }
        return optima[0];
    }

    protected abstract void store(PAIR var1);

    protected abstract void clear();
}

