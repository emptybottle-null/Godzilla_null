/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.general;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.optimization.general.AbstractScalarDifferentiableOptimizer;
import org.apache.commons.math3.optimization.general.ConjugateGradientFormula;
import org.apache.commons.math3.optimization.general.Preconditioner;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class NonLinearConjugateGradientOptimizer
extends AbstractScalarDifferentiableOptimizer {
    private final ConjugateGradientFormula updateFormula;
    private final Preconditioner preconditioner;
    private final UnivariateSolver solver;
    private double initialStep;
    private double[] point;

    @Deprecated
    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula) {
        this(updateFormula, new SimpleValueChecker());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula, ConvergenceChecker<PointValuePair> checker) {
        this(updateFormula, checker, new BrentSolver(), new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula, ConvergenceChecker<PointValuePair> checker, UnivariateSolver lineSearchSolver) {
        this(updateFormula, checker, lineSearchSolver, new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula, ConvergenceChecker<PointValuePair> checker, UnivariateSolver lineSearchSolver, Preconditioner preconditioner) {
        super(checker);
        this.updateFormula = updateFormula;
        this.solver = lineSearchSolver;
        this.preconditioner = preconditioner;
        this.initialStep = 1.0;
    }

    public void setInitialStep(double initialStep) {
        this.initialStep = initialStep <= 0.0 ? 1.0 : initialStep;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    protected PointValuePair doOptimize() {
        checker = this.getConvergenceChecker();
        this.point = this.getStartPoint();
        goal = this.getGoalType();
        n = this.point.length;
        r = this.computeObjectiveGradient(this.point);
        if (goal == GoalType.MINIMIZE) {
            for (i = 0; i < n; ++i) {
                r[i] = -r[i];
            }
        }
        steepestDescent = this.preconditioner.precondition(this.point, r);
        searchDirection = (double[])steepestDescent.clone();
        delta = 0.0;
        for (i = 0; i < n; ++i) {
            delta += r[i] * searchDirection[i];
        }
        current = null;
        iter = 0;
        maxEval = this.getMaxEvaluations();
        block2: while (true) {
            objective = this.computeObjectiveValue(this.point);
            previous = current;
            current = new PointValuePair(this.point, objective);
            if (previous != null && checker.converged(++iter, previous, current)) {
                return current;
            }
            lsf = new LineSearchFunction(searchDirection);
            uB = this.findUpperBound(lsf, 0.0, this.initialStep);
            step = this.solver.solve(maxEval, lsf, 0.0, uB, 1.0E-15);
            maxEval -= this.solver.getEvaluations();
            for (i = 0; i < this.point.length; ++i) {
                v0 = i;
                this.point[v0] = this.point[v0] + step * searchDirection[i];
            }
            r = this.computeObjectiveGradient(this.point);
            if (goal == GoalType.MINIMIZE) {
                for (i = 0; i < n; ++i) {
                    r[i] = -r[i];
                }
            }
            deltaOld = delta;
            newSteepestDescent = this.preconditioner.precondition(this.point, r);
            delta = 0.0;
            for (i = 0; i < n; ++i) {
                delta += r[i] * newSteepestDescent[i];
            }
            if (this.updateFormula == ConjugateGradientFormula.FLETCHER_REEVES) {
                beta = delta / deltaOld;
            } else {
                deltaMid = 0.0;
                for (i = 0; i < r.length; ++i) {
                    deltaMid += r[i] * steepestDescent[i];
                }
                beta = (delta - deltaMid) / deltaOld;
            }
            steepestDescent = newSteepestDescent;
            if (iter % n == 0 || beta < 0.0) {
                searchDirection = (double[])steepestDescent.clone();
                continue;
            }
            i = 0;
            while (true) {
                if (i < n) ** break;
                continue block2;
                searchDirection[i] = steepestDescent[i] + beta * searchDirection[i];
                ++i;
            }
            break;
        }
    }

    private double findUpperBound(UnivariateFunction f, double a, double h) {
        double yA;
        double yB = yA = f.value(a);
        for (double step = h; step < Double.MAX_VALUE; step *= FastMath.max(2.0, yA / yB)) {
            double b = a + step;
            yB = f.value(b);
            if (!(yA * yB <= 0.0)) continue;
            return b;
        }
        throw new MathIllegalStateException(LocalizedFormats.UNABLE_TO_BRACKET_OPTIMUM_IN_LINE_SEARCH, new Object[0]);
    }

    private class LineSearchFunction
    implements UnivariateFunction {
        private final double[] searchDirection;

        LineSearchFunction(double[] searchDirection) {
            this.searchDirection = searchDirection;
        }

        public double value(double x) {
            double[] shiftedPoint = (double[])NonLinearConjugateGradientOptimizer.this.point.clone();
            for (int i = 0; i < shiftedPoint.length; ++i) {
                int n = i;
                shiftedPoint[n] = shiftedPoint[n] + x * this.searchDirection[i];
            }
            double[] gradient = NonLinearConjugateGradientOptimizer.this.computeObjectiveGradient(shiftedPoint);
            double dotProduct = 0.0;
            for (int i = 0; i < gradient.length; ++i) {
                dotProduct += gradient[i] * this.searchDirection[i];
            }
            return dotProduct;
        }
    }

    public static class IdentityPreconditioner
    implements Preconditioner {
        public double[] precondition(double[] variables, double[] r) {
            return (double[])r.clone();
        }
    }
}

