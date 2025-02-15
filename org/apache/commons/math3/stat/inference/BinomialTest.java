/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.inference.AlternativeHypothesis;

public class BinomialTest {
    public boolean binomialTest(int numberOfTrials, int numberOfSuccesses, double probability, AlternativeHypothesis alternativeHypothesis, double alpha) {
        double pValue = this.binomialTest(numberOfTrials, numberOfSuccesses, probability, alternativeHypothesis);
        return pValue < alpha;
    }

    public double binomialTest(int numberOfTrials, int numberOfSuccesses, double probability, AlternativeHypothesis alternativeHypothesis) {
        if (numberOfTrials < 0) {
            throw new NotPositiveException(numberOfTrials);
        }
        if (numberOfSuccesses < 0) {
            throw new NotPositiveException(numberOfSuccesses);
        }
        if (probability < 0.0 || probability > 1.0) {
            throw new OutOfRangeException(probability, (Number)0, 1);
        }
        if (numberOfTrials < numberOfSuccesses) {
            throw new MathIllegalArgumentException(LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER, numberOfTrials, numberOfSuccesses);
        }
        if (alternativeHypothesis == null) {
            throw new NullArgumentException();
        }
        BinomialDistribution distribution = new BinomialDistribution(null, numberOfTrials, probability);
        switch (alternativeHypothesis) {
            case GREATER_THAN: {
                return 1.0 - distribution.cumulativeProbability(numberOfSuccesses - 1);
            }
            case LESS_THAN: {
                return distribution.cumulativeProbability(numberOfSuccesses);
            }
            case TWO_SIDED: {
                int criticalValueLow = 0;
                int criticalValueHigh = numberOfTrials;
                double pTotal = 0.0;
                do {
                    double pHigh;
                    double pLow;
                    if ((pLow = distribution.probability(criticalValueLow)) == (pHigh = distribution.probability(criticalValueHigh))) {
                        pTotal += 2.0 * pLow;
                        ++criticalValueLow;
                        --criticalValueHigh;
                        continue;
                    }
                    if (pLow < pHigh) {
                        pTotal += pLow;
                        ++criticalValueLow;
                        continue;
                    }
                    pTotal += pHigh;
                    --criticalValueHigh;
                } while (criticalValueLow <= numberOfSuccesses && criticalValueHigh >= numberOfSuccesses);
                return pTotal;
            }
        }
        throw new MathInternalError(LocalizedFormats.OUT_OF_RANGE_SIMPLE, new Object[]{alternativeHypothesis, AlternativeHypothesis.TWO_SIDED, AlternativeHypothesis.LESS_THAN});
    }
}

