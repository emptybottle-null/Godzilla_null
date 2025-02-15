/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;

public interface RealDistribution {
    public double probability(double var1);

    public double density(double var1);

    public double cumulativeProbability(double var1);

    @Deprecated
    public double cumulativeProbability(double var1, double var3) throws NumberIsTooLargeException;

    public double inverseCumulativeProbability(double var1) throws OutOfRangeException;

    public double getNumericalMean();

    public double getNumericalVariance();

    public double getSupportLowerBound();

    public double getSupportUpperBound();

    @Deprecated
    public boolean isSupportLowerBoundInclusive();

    @Deprecated
    public boolean isSupportUpperBoundInclusive();

    public boolean isSupportConnected();

    public void reseedRandomGenerator(long var1);

    public double sample();

    public double[] sample(int var1);
}

