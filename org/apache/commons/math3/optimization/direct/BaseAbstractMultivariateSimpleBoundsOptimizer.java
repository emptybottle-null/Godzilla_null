/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optimization.BaseMultivariateOptimizer;
import org.apache.commons.math3.optimization.BaseMultivariateSimpleBoundsOptimizer;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleBounds;
import org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public abstract class BaseAbstractMultivariateSimpleBoundsOptimizer<FUNC extends MultivariateFunction>
extends BaseAbstractMultivariateOptimizer<FUNC>
implements BaseMultivariateOptimizer<FUNC>,
BaseMultivariateSimpleBoundsOptimizer<FUNC> {
    @Deprecated
    protected BaseAbstractMultivariateSimpleBoundsOptimizer() {
    }

    protected BaseAbstractMultivariateSimpleBoundsOptimizer(ConvergenceChecker<PointValuePair> checker) {
        super(checker);
    }

    @Override
    public PointValuePair optimize(int maxEval, FUNC f, GoalType goalType, double[] startPoint) {
        return super.optimizeInternal(maxEval, f, goalType, new InitialGuess(startPoint));
    }

    @Override
    public PointValuePair optimize(int maxEval, FUNC f, GoalType goalType, double[] startPoint, double[] lower, double[] upper) {
        return super.optimizeInternal(maxEval, f, goalType, new InitialGuess(startPoint), new SimpleBounds(lower, upper));
    }
}

