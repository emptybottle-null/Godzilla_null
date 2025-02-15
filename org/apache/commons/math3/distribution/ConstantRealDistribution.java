/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.exception.OutOfRangeException;

public class ConstantRealDistribution
extends AbstractRealDistribution {
    private static final long serialVersionUID = -4157745166772046273L;
    private final double value;

    public ConstantRealDistribution(double value) {
        super(null);
        this.value = value;
    }

    public double density(double x) {
        return x == this.value ? 1.0 : 0.0;
    }

    public double cumulativeProbability(double x) {
        return x < this.value ? 0.0 : 1.0;
    }

    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, (Number)0, 1);
        }
        return this.value;
    }

    public double getNumericalMean() {
        return this.value;
    }

    public double getNumericalVariance() {
        return 0.0;
    }

    public double getSupportLowerBound() {
        return this.value;
    }

    public double getSupportUpperBound() {
        return this.value;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public double sample() {
        return this.value;
    }

    public void reseedRandomGenerator(long seed) {
    }
}

