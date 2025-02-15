/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.interval.BinomialConfidenceInterval;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;
import org.apache.commons.math3.stat.interval.IntervalUtils;
import org.apache.commons.math3.util.FastMath;

public class NormalApproximationInterval
implements BinomialConfidenceInterval {
    public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
        double mean = (double)numberOfSuccesses / (double)numberOfTrials;
        double alpha = (1.0 - confidenceLevel) / 2.0;
        NormalDistribution normalDistribution = new NormalDistribution();
        double difference = normalDistribution.inverseCumulativeProbability(1.0 - alpha) * FastMath.sqrt(1.0 / (double)numberOfTrials * mean * (1.0 - mean));
        return new ConfidenceInterval(mean - difference, mean + difference, confidenceLevel);
    }
}

