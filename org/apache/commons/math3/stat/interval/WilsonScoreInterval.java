/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.interval.BinomialConfidenceInterval;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;
import org.apache.commons.math3.stat.interval.IntervalUtils;
import org.apache.commons.math3.util.FastMath;

public class WilsonScoreInterval
implements BinomialConfidenceInterval {
    public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
        double alpha = (1.0 - confidenceLevel) / 2.0;
        NormalDistribution normalDistribution = new NormalDistribution();
        double z = normalDistribution.inverseCumulativeProbability(1.0 - alpha);
        double zSquared = FastMath.pow(z, 2);
        double mean = (double)numberOfSuccesses / (double)numberOfTrials;
        double factor = 1.0 / (1.0 + 1.0 / (double)numberOfTrials * zSquared);
        double modifiedSuccessRatio = mean + 1.0 / (double)(2 * numberOfTrials) * zSquared;
        double difference = z * FastMath.sqrt(1.0 / (double)numberOfTrials * mean * (1.0 - mean) + 1.0 / (4.0 * FastMath.pow((double)numberOfTrials, 2)) * zSquared);
        double lowerBound = factor * (modifiedSuccessRatio - difference);
        double upperBound = factor * (modifiedSuccessRatio + difference);
        return new ConfidenceInterval(lowerBound, upperBound, confidenceLevel);
    }
}

