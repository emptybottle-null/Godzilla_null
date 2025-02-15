/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.util.Incrementor;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class BaseOptimizer<PAIR> {
    protected final Incrementor evaluations;
    protected final Incrementor iterations;
    private final ConvergenceChecker<PAIR> checker;

    protected BaseOptimizer(ConvergenceChecker<PAIR> checker) {
        this(checker, 0, Integer.MAX_VALUE);
    }

    protected BaseOptimizer(ConvergenceChecker<PAIR> checker, int maxEval, int maxIter) {
        this.checker = checker;
        this.evaluations = new Incrementor(maxEval, new MaxEvalCallback());
        this.iterations = new Incrementor(maxIter, new MaxIterCallback());
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public int getMaxIterations() {
        return this.iterations.getMaximalCount();
    }

    public int getIterations() {
        return this.iterations.getCount();
    }

    public ConvergenceChecker<PAIR> getConvergenceChecker() {
        return this.checker;
    }

    public PAIR optimize(OptimizationData ... optData) throws TooManyEvaluationsException, TooManyIterationsException {
        this.parseOptimizationData(optData);
        this.evaluations.resetCount();
        this.iterations.resetCount();
        return this.doOptimize();
    }

    public PAIR optimize() throws TooManyEvaluationsException, TooManyIterationsException {
        this.evaluations.resetCount();
        this.iterations.resetCount();
        return this.doOptimize();
    }

    protected abstract PAIR doOptimize();

    protected void incrementEvaluationCount() throws TooManyEvaluationsException {
        this.evaluations.incrementCount();
    }

    protected void incrementIterationCount() throws TooManyIterationsException {
        this.iterations.incrementCount();
    }

    protected void parseOptimizationData(OptimizationData ... optData) {
        for (OptimizationData data : optData) {
            if (data instanceof MaxEval) {
                this.evaluations.setMaximalCount(((MaxEval)data).getMaxEval());
                continue;
            }
            if (!(data instanceof MaxIter)) continue;
            this.iterations.setMaximalCount(((MaxIter)data).getMaxIter());
        }
    }

    private static class MaxIterCallback
    implements Incrementor.MaxCountExceededCallback {
        private MaxIterCallback() {
        }

        public void trigger(int max) {
            throw new TooManyIterationsException(max);
        }
    }

    private static class MaxEvalCallback
    implements Incrementor.MaxCountExceededCallback {
        private MaxEvalCallback() {
        }

        public void trigger(int max) {
            throw new TooManyEvaluationsException(max);
        }
    }
}

