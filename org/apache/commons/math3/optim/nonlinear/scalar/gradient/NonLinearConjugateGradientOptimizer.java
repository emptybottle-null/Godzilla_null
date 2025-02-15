/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.scalar.gradient;

import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.GradientMultivariateOptimizer;
import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;
import org.apache.commons.math3.optim.nonlinear.scalar.gradient.Preconditioner;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class NonLinearConjugateGradientOptimizer
extends GradientMultivariateOptimizer {
    private final Formula updateFormula;
    private final Preconditioner preconditioner;
    private final LineSearch line;

    public NonLinearConjugateGradientOptimizer(Formula updateFormula, ConvergenceChecker<PointValuePair> checker) {
        this(updateFormula, checker, 1.0E-8, 1.0E-8, 1.0E-8, new IdentityPreconditioner());
    }

    @Deprecated
    public NonLinearConjugateGradientOptimizer(Formula updateFormula, ConvergenceChecker<PointValuePair> checker, UnivariateSolver lineSearchSolver) {
        this(updateFormula, checker, lineSearchSolver, new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(Formula updateFormula, ConvergenceChecker<PointValuePair> checker, double relativeTolerance, double absoluteTolerance, double initialBracketingRange) {
        this(updateFormula, checker, relativeTolerance, absoluteTolerance, initialBracketingRange, new IdentityPreconditioner());
    }

    @Deprecated
    public NonLinearConjugateGradientOptimizer(Formula updateFormula, ConvergenceChecker<PointValuePair> checker, UnivariateSolver lineSearchSolver, Preconditioner preconditioner) {
        this(updateFormula, checker, lineSearchSolver.getRelativeAccuracy(), lineSearchSolver.getAbsoluteAccuracy(), lineSearchSolver.getAbsoluteAccuracy(), preconditioner);
    }

    public NonLinearConjugateGradientOptimizer(Formula updateFormula, ConvergenceChecker<PointValuePair> checker, double relativeTolerance, double absoluteTolerance, double initialBracketingRange, Preconditioner preconditioner) {
        super(checker);
        this.updateFormula = updateFormula;
        this.preconditioner = preconditioner;
        this.line = new LineSearch(this, relativeTolerance, absoluteTolerance, initialBracketingRange);
    }

    @Override
    public PointValuePair optimize(OptimizationData ... optData) throws TooManyEvaluationsException {
        return super.optimize(optData);
    }

    /*
     * Unable to fully structure code
     */
    @Override
    protected PointValuePair doOptimize() {
        checker = this.getConvergenceChecker();
        point = this.getStartPoint();
        goal = this.getGoalType();
        n = point.length;
        r = this.computeObjectiveGradient(point);
        if (goal == GoalType.MINIMIZE) {
            for (i = 0; i < n; ++i) {
                r[i] = -r[i];
            }
        }
        steepestDescent = this.preconditioner.precondition(point, r);
        searchDirection = (double[])steepestDescent.clone();
        delta = 0.0;
        for (i = 0; i < n; ++i) {
            delta += r[i] * searchDirection[i];
        }
        current = null;
        block6: while (true) {
            this.incrementIterationCount();
            objective = this.computeObjectiveValue(point);
            previous = current;
            current = new PointValuePair(point, objective);
            if (previous != null && checker.converged(this.getIterations(), previous, current)) {
                return current;
            }
            step = this.line.search(point, searchDirection).getPoint();
            for (i = 0; i < point.length; ++i) {
                v0 = i;
                point[v0] = point[v0] + step * searchDirection[i];
            }
            r = this.computeObjectiveGradient(point);
            if (goal == GoalType.MINIMIZE) {
                for (i = 0; i < n; ++i) {
                    r[i] = -r[i];
                }
            }
            deltaOld = delta;
            newSteepestDescent = this.preconditioner.precondition(point, r);
            delta = 0.0;
            for (i = 0; i < n; ++i) {
                delta += r[i] * newSteepestDescent[i];
            }
            switch (1.$SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula[this.updateFormula.ordinal()]) {
                case 1: {
                    beta = delta / deltaOld;
                    break;
                }
                case 2: {
                    deltaMid = 0.0;
                    for (i = 0; i < r.length; ++i) {
                        deltaMid += r[i] * steepestDescent[i];
                    }
                    beta = (delta - deltaMid) / deltaOld;
                    break;
                }
                default: {
                    throw new MathInternalError();
                }
            }
            steepestDescent = newSteepestDescent;
            if (this.getIterations() % n == 0 || beta < 0.0) {
                searchDirection = (double[])steepestDescent.clone();
                continue;
            }
            i = 0;
            while (true) {
                if (i < n) ** break;
                continue block6;
                searchDirection[i] = steepestDescent[i] + beta * searchDirection[i];
                ++i;
            }
            break;
        }
    }

    @Override
    protected void parseOptimizationData(OptimizationData ... optData) {
        super.parseOptimizationData(optData);
        this.checkParameters();
    }

    private void checkParameters() {
        if (this.getLowerBound() != null || this.getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }

    static class 1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula;

        static {
            $SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula = new int[Formula.values().length];
            try {
                1.$SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula[Formula.FLETCHER_REEVES.ordinal()] = 1;
            } catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                1.$SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula[Formula.POLAK_RIBIERE.ordinal()] = 2;
            } catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
        }
    }

    public static class IdentityPreconditioner
    implements Preconditioner {
        public double[] precondition(double[] variables, double[] r) {
            return (double[])r.clone();
        }
    }

    @Deprecated
    public static class BracketingStep
    implements OptimizationData {
        private final double initialStep;

        public BracketingStep(double step) {
            this.initialStep = step;
        }

        public double getBracketingStep() {
            return this.initialStep;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum Formula {
        FLETCHER_REEVES,
        POLAK_RIBIERE;

    }
}

