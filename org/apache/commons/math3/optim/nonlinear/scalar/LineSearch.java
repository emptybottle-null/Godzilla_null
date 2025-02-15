/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.optim.univariate.BracketFinder;
import org.apache.commons.math3.optim.univariate.BrentOptimizer;
import org.apache.commons.math3.optim.univariate.SearchInterval;
import org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker;
import org.apache.commons.math3.optim.univariate.UnivariateObjectiveFunction;
import org.apache.commons.math3.optim.univariate.UnivariateOptimizer;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;

public class LineSearch {
    private static final double REL_TOL_UNUSED = 1.0E-15;
    private static final double ABS_TOL_UNUSED = Double.MIN_VALUE;
    private final UnivariateOptimizer lineOptimizer;
    private final BracketFinder bracket = new BracketFinder();
    private final double initialBracketingRange;
    private final MultivariateOptimizer mainOptimizer;

    public LineSearch(MultivariateOptimizer optimizer, double relativeTolerance, double absoluteTolerance, double initialBracketingRange) {
        this.mainOptimizer = optimizer;
        this.lineOptimizer = new BrentOptimizer(1.0E-15, Double.MIN_VALUE, new SimpleUnivariateValueChecker(relativeTolerance, absoluteTolerance));
        this.initialBracketingRange = initialBracketingRange;
    }

    public UnivariatePointValuePair search(final double[] startPoint, final double[] direction) {
        final int n = startPoint.length;
        UnivariateFunction f = new UnivariateFunction(){

            public double value(double alpha) {
                double[] x = new double[n];
                for (int i = 0; i < n; ++i) {
                    x[i] = startPoint[i] + alpha * direction[i];
                }
                double obj = LineSearch.this.mainOptimizer.computeObjectiveValue(x);
                return obj;
            }
        };
        GoalType goal = this.mainOptimizer.getGoalType();
        this.bracket.search(f, goal, 0.0, this.initialBracketingRange);
        return this.lineOptimizer.optimize(new MaxEval(Integer.MAX_VALUE), new UnivariateObjectiveFunction(f), goal, new SearchInterval(this.bracket.getLo(), this.bracket.getHi(), this.bracket.getMid()));
    }
}

