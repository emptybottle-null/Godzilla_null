/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.Incrementor;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class LeastSquaresAdapter
implements LeastSquaresProblem {
    private final LeastSquaresProblem problem;

    public LeastSquaresAdapter(LeastSquaresProblem problem) {
        this.problem = problem;
    }

    @Override
    public RealVector getStart() {
        return this.problem.getStart();
    }

    @Override
    public int getObservationSize() {
        return this.problem.getObservationSize();
    }

    @Override
    public int getParameterSize() {
        return this.problem.getParameterSize();
    }

    @Override
    public LeastSquaresProblem.Evaluation evaluate(RealVector point) {
        return this.problem.evaluate(point);
    }

    @Override
    public Incrementor getEvaluationCounter() {
        return this.problem.getEvaluationCounter();
    }

    @Override
    public Incrementor getIterationCounter() {
        return this.problem.getIterationCounter();
    }

    @Override
    public ConvergenceChecker<LeastSquaresProblem.Evaluation> getConvergenceChecker() {
        return this.problem.getConvergenceChecker();
    }
}

