/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.optim.AbstractConvergenceChecker;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SimplePointChecker<PAIR extends Pair<double[], ? extends Object>>
extends AbstractConvergenceChecker<PAIR> {
    private static final int ITERATION_CHECK_DISABLED = -1;
    private final int maxIterationCount;

    public SimplePointChecker(double relativeThreshold, double absoluteThreshold) {
        super(relativeThreshold, absoluteThreshold);
        this.maxIterationCount = -1;
    }

    public SimplePointChecker(double relativeThreshold, double absoluteThreshold, int maxIter) {
        super(relativeThreshold, absoluteThreshold);
        if (maxIter <= 0) {
            throw new NotStrictlyPositiveException(maxIter);
        }
        this.maxIterationCount = maxIter;
    }

    @Override
    public boolean converged(int iteration, PAIR previous, PAIR current) {
        if (this.maxIterationCount != -1 && iteration >= this.maxIterationCount) {
            return true;
        }
        double[] p = (double[])((Pair)previous).getKey();
        double[] c = (double[])((Pair)current).getKey();
        for (int i = 0; i < p.length; ++i) {
            double size;
            double pi = p[i];
            double ci = c[i];
            double difference = FastMath.abs(pi - ci);
            if (!(difference > (size = FastMath.max(FastMath.abs(pi), FastMath.abs(ci))) * this.getRelativeThreshold()) || !(difference > this.getAbsoluteThreshold())) continue;
            return false;
        }
        return true;
    }
}

