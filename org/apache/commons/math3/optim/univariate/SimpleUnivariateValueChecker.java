/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.optim.AbstractConvergenceChecker;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SimpleUnivariateValueChecker
extends AbstractConvergenceChecker<UnivariatePointValuePair> {
    private static final int ITERATION_CHECK_DISABLED = -1;
    private final int maxIterationCount;

    public SimpleUnivariateValueChecker(double relativeThreshold, double absoluteThreshold) {
        super(relativeThreshold, absoluteThreshold);
        this.maxIterationCount = -1;
    }

    public SimpleUnivariateValueChecker(double relativeThreshold, double absoluteThreshold, int maxIter) {
        super(relativeThreshold, absoluteThreshold);
        if (maxIter <= 0) {
            throw new NotStrictlyPositiveException(maxIter);
        }
        this.maxIterationCount = maxIter;
    }

    @Override
    public boolean converged(int iteration, UnivariatePointValuePair previous, UnivariatePointValuePair current) {
        double size;
        double c;
        if (this.maxIterationCount != -1 && iteration >= this.maxIterationCount) {
            return true;
        }
        double p = previous.getValue();
        double difference = FastMath.abs(p - (c = current.getValue()));
        return difference <= (size = FastMath.max(FastMath.abs(p), FastMath.abs(c))) * this.getRelativeThreshold() || difference <= this.getAbsoluteThreshold();
    }
}

