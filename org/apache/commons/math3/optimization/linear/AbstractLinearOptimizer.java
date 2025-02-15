/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.linear;

import java.util.Collection;
import java.util.Collections;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.linear.LinearConstraint;
import org.apache.commons.math3.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optimization.linear.LinearOptimizer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public abstract class AbstractLinearOptimizer
implements LinearOptimizer {
    public static final int DEFAULT_MAX_ITERATIONS = 100;
    private LinearObjectiveFunction function;
    private Collection<LinearConstraint> linearConstraints;
    private GoalType goal;
    private boolean nonNegative;
    private int maxIterations;
    private int iterations;

    protected AbstractLinearOptimizer() {
        this.setMaxIterations(100);
    }

    protected boolean restrictToNonNegative() {
        return this.nonNegative;
    }

    protected GoalType getGoalType() {
        return this.goal;
    }

    protected LinearObjectiveFunction getFunction() {
        return this.function;
    }

    protected Collection<LinearConstraint> getConstraints() {
        return Collections.unmodifiableCollection(this.linearConstraints);
    }

    @Override
    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    @Override
    public int getMaxIterations() {
        return this.maxIterations;
    }

    @Override
    public int getIterations() {
        return this.iterations;
    }

    protected void incrementIterationsCounter() throws MaxCountExceededException {
        if (++this.iterations > this.maxIterations) {
            throw new MaxCountExceededException(this.maxIterations);
        }
    }

    @Override
    public PointValuePair optimize(LinearObjectiveFunction f, Collection<LinearConstraint> constraints, GoalType goalType, boolean restrictToNonNegative) throws MathIllegalStateException {
        this.function = f;
        this.linearConstraints = constraints;
        this.goal = goalType;
        this.nonNegative = restrictToNonNegative;
        this.iterations = 0;
        return this.doOptimize();
    }

    protected abstract PointValuePair doOptimize() throws MathIllegalStateException;
}

