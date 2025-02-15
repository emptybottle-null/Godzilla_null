/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class EvaluationRmsChecker
implements ConvergenceChecker<LeastSquaresProblem.Evaluation> {
    private final double relTol;
    private final double absTol;

    public EvaluationRmsChecker(double tol) {
        this(tol, tol);
    }

    public EvaluationRmsChecker(double relTol, double absTol) {
        this.relTol = relTol;
        this.absTol = absTol;
    }

    @Override
    public boolean converged(int iteration, LeastSquaresProblem.Evaluation previous, LeastSquaresProblem.Evaluation current) {
        double currRms;
        double prevRms = previous.getRMS();
        return Precision.equals(prevRms, currRms = current.getRMS(), this.absTol) || Precision.equalsWithRelativeTolerance(prevRms, currRms, this.relTol);
    }
}

