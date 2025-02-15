/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.linear;

import java.util.Collection;
import java.util.Collections;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class LinearOptimizer
extends MultivariateOptimizer {
    private LinearObjectiveFunction function;
    private Collection<LinearConstraint> linearConstraints;
    private boolean nonNegative;

    protected LinearOptimizer() {
        super((ConvergenceChecker<PointValuePair>)null);
    }

    protected boolean isRestrictedToNonNegative() {
        return this.nonNegative;
    }

    protected LinearObjectiveFunction getFunction() {
        return this.function;
    }

    protected Collection<LinearConstraint> getConstraints() {
        return Collections.unmodifiableCollection(this.linearConstraints);
    }

    @Override
    public PointValuePair optimize(OptimizationData ... optData) throws TooManyIterationsException {
        return super.optimize(optData);
    }

    @Override
    protected void parseOptimizationData(OptimizationData ... optData) {
        super.parseOptimizationData(optData);
        for (OptimizationData data : optData) {
            if (data instanceof LinearObjectiveFunction) {
                this.function = (LinearObjectiveFunction)data;
                continue;
            }
            if (data instanceof LinearConstraintSet) {
                this.linearConstraints = ((LinearConstraintSet)data).getConstraints();
                continue;
            }
            if (!(data instanceof NonNegativeConstraint)) continue;
            this.nonNegative = ((NonNegativeConstraint)data).isRestrictedToNonNegative();
        }
    }
}

