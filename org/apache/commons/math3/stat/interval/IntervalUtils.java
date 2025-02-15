/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.interval.AgrestiCoullInterval;
import org.apache.commons.math3.stat.interval.BinomialConfidenceInterval;
import org.apache.commons.math3.stat.interval.ClopperPearsonInterval;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;
import org.apache.commons.math3.stat.interval.NormalApproximationInterval;
import org.apache.commons.math3.stat.interval.WilsonScoreInterval;

public final class IntervalUtils {
    private static final BinomialConfidenceInterval AGRESTI_COULL = new AgrestiCoullInterval();
    private static final BinomialConfidenceInterval CLOPPER_PEARSON = new ClopperPearsonInterval();
    private static final BinomialConfidenceInterval NORMAL_APPROXIMATION = new NormalApproximationInterval();
    private static final BinomialConfidenceInterval WILSON_SCORE = new WilsonScoreInterval();

    private IntervalUtils() {
    }

    public static ConfidenceInterval getAgrestiCoullInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        return AGRESTI_COULL.createInterval(numberOfTrials, numberOfSuccesses, confidenceLevel);
    }

    public static ConfidenceInterval getClopperPearsonInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        return CLOPPER_PEARSON.createInterval(numberOfTrials, numberOfSuccesses, confidenceLevel);
    }

    public static ConfidenceInterval getNormalApproximationInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        return NORMAL_APPROXIMATION.createInterval(numberOfTrials, numberOfSuccesses, confidenceLevel);
    }

    public static ConfidenceInterval getWilsonScoreInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        return WILSON_SCORE.createInterval(numberOfTrials, numberOfSuccesses, confidenceLevel);
    }

    static void checkParameters(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        if (numberOfTrials <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.NUMBER_OF_TRIALS, numberOfTrials);
        }
        if (numberOfSuccesses < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.NEGATIVE_NUMBER_OF_SUCCESSES, numberOfSuccesses);
        }
        if (numberOfSuccesses > numberOfTrials) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.NUMBER_OF_SUCCESS_LARGER_THAN_POPULATION_SIZE, (Number)numberOfSuccesses, numberOfTrials, true);
        }
        if (confidenceLevel <= 0.0 || confidenceLevel >= 1.0) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.OUT_OF_BOUNDS_CONFIDENCE_LEVEL, (Number)confidenceLevel, 0, 1);
        }
    }
}

