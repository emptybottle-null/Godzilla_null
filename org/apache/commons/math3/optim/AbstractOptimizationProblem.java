/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationProblem;
import org.apache.commons.math3.util.Incrementor;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractOptimizationProblem<PAIR>
implements OptimizationProblem<PAIR> {
    private static final MaxEvalCallback MAX_EVAL_CALLBACK = new MaxEvalCallback();
    private static final MaxIterCallback MAX_ITER_CALLBACK = new MaxIterCallback();
    private final int maxEvaluations;
    private final int maxIterations;
    private final ConvergenceChecker<PAIR> checker;

    protected AbstractOptimizationProblem(int maxEvaluations, int maxIterations, ConvergenceChecker<PAIR> checker) {
        this.maxEvaluations = maxEvaluations;
        this.maxIterations = maxIterations;
        this.checker = checker;
    }

    @Override
    public Incrementor getEvaluationCounter() {
        return new Incrementor(this.maxEvaluations, MAX_EVAL_CALLBACK);
    }

    @Override
    public Incrementor getIterationCounter() {
        return new Incrementor(this.maxIterations, MAX_ITER_CALLBACK);
    }

    @Override
    public ConvergenceChecker<PAIR> getConvergenceChecker() {
        return this.checker;
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

