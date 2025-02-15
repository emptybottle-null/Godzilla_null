/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.interval.BinomialConfidenceInterval;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;
import org.apache.commons.math3.stat.interval.IntervalUtils;
import org.apache.commons.math3.util.FastMath;

public class AgrestiCoullInterval
implements BinomialConfidenceInterval {
    public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
        double alpha = (1.0 - confidenceLevel) / 2.0;
        NormalDistribution normalDistribution = new NormalDistribution();
        double z = normalDistribution.inverseCumulativeProbability(1.0 - alpha);
        double zSquared = FastMath.pow(z, 2);
        double modifiedNumberOfTrials = (double)numberOfTrials + zSquared;
        double modifiedSuccessesRatio = 1.0 / modifiedNumberOfTrials * ((double)numberOfSuccesses + 0.5 * zSquared);
        double difference = z * FastMath.sqrt(1.0 / modifiedNumberOfTrials * modifiedSuccessesRatio * (1.0 - modifiedSuccessesRatio));
        return new ConfidenceInterval(modifiedSuccessesRatio - difference, modifiedSuccessesRatio + difference, confidenceLevel);
    }
}

