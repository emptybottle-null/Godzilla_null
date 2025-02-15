/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.stat.interval.BinomialConfidenceInterval;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;
import org.apache.commons.math3.stat.interval.IntervalUtils;

public class ClopperPearsonInterval
implements BinomialConfidenceInterval {
    public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
        double lowerBound = 0.0;
        double upperBound = 0.0;
        double alpha = (1.0 - confidenceLevel) / 2.0;
        FDistribution distributionLowerBound = new FDistribution(2 * (numberOfTrials - numberOfSuccesses + 1), 2 * numberOfSuccesses);
        double fValueLowerBound = distributionLowerBound.inverseCumulativeProbability(1.0 - alpha);
        if (numberOfSuccesses > 0) {
            lowerBound = (double)numberOfSuccesses / ((double)numberOfSuccesses + (double)(numberOfTrials - numberOfSuccesses + 1) * fValueLowerBound);
        }
        FDistribution distributionUpperBound = new FDistribution(2 * (numberOfSuccesses + 1), 2 * (numberOfTrials - numberOfSuccesses));
        double fValueUpperBound = distributionUpperBound.inverseCumulativeProbability(1.0 - alpha);
        if (numberOfSuccesses > 0) {
            upperBound = (double)(numberOfSuccesses + 1) * fValueUpperBound / ((double)(numberOfTrials - numberOfSuccesses) + (double)(numberOfSuccesses + 1) * fValueUpperBound);
        }
        return new ConfidenceInterval(lowerBound, upperBound, confidenceLevel);
    }
}

