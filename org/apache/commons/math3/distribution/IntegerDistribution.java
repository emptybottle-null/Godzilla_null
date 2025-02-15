/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;

public interface IntegerDistribution {
    public double probability(int var1);

    public double cumulativeProbability(int var1);

    public double cumulativeProbability(int var1, int var2) throws NumberIsTooLargeException;

    public int inverseCumulativeProbability(double var1) throws OutOfRangeException;

    public double getNumericalMean();

    public double getNumericalVariance();

    public int getSupportLowerBound();

    public int getSupportUpperBound();

    public boolean isSupportConnected();

    public void reseedRandomGenerator(long var1);

    public int sample();

    public int[] sample(int var1);
}

