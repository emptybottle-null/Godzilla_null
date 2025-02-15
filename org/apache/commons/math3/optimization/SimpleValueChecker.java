/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.optimization.AbstractConvergenceChecker;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class SimpleValueChecker
extends AbstractConvergenceChecker<PointValuePair> {
    private static final int ITERATION_CHECK_DISABLED = -1;
    private final int maxIterationCount;

    @Deprecated
    public SimpleValueChecker() {
        this.maxIterationCount = -1;
    }

    public SimpleValueChecker(double relativeThreshold, double absoluteThreshold) {
        super(relativeThreshold, absoluteThreshold);
        this.maxIterationCount = -1;
    }

    public SimpleValueChecker(double relativeThreshold, double absoluteThreshold, int maxIter) {
        super(relativeThreshold, absoluteThreshold);
        if (maxIter <= 0) {
            throw new NotStrictlyPositiveException(maxIter);
        }
        this.maxIterationCount = maxIter;
    }

    @Override
    public boolean converged(int iteration, PointValuePair previous, PointValuePair current) {
        double size;
        double c;
        if (this.maxIterationCount != -1 && iteration >= this.maxIterationCount) {
            return true;
        }
        double p = (Double)previous.getValue();
        double difference = FastMath.abs(p - (c = ((Double)current.getValue()).doubleValue()));
        return difference <= (size = FastMath.max(FastMath.abs(p), FastMath.abs(c))) * this.getRelativeThreshold() || difference <= this.getAbsoluteThreshold();
    }
}

