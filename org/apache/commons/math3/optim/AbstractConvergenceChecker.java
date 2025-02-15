/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim;

import org.apache.commons.math3.optim.ConvergenceChecker;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractConvergenceChecker<PAIR>
implements ConvergenceChecker<PAIR> {
    private final double relativeThreshold;
    private final double absoluteThreshold;

    public AbstractConvergenceChecker(double relativeThreshold, double absoluteThreshold) {
        this.relativeThreshold = relativeThreshold;
        this.absoluteThreshold = absoluteThreshold;
    }

    public double getRelativeThreshold() {
        return this.relativeThreshold;
    }

    public double getAbsoluteThreshold() {
        return this.absoluteThreshold;
    }

    @Override
    public abstract boolean converged(int var1, PAIR var2, PAIR var3);
}

