/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet.sofm;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunction;
import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;
import org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction;

public class LearningFactorFunctionFactory {
    private LearningFactorFunctionFactory() {
    }

    public static LearningFactorFunction exponentialDecay(final double initValue, final double valueAtNumCall, final long numCall) {
        if (initValue <= 0.0 || initValue > 1.0) {
            throw new OutOfRangeException(initValue, (Number)0, 1);
        }
        return new LearningFactorFunction(){
            private final ExponentialDecayFunction decay;
            {
                this.decay = new ExponentialDecayFunction(initValue, valueAtNumCall, numCall);
            }

            public double value(long n) {
                return this.decay.value(n);
            }
        };
    }

    public static LearningFactorFunction quasiSigmoidDecay(final double initValue, final double slope, final long numCall) {
        if (initValue <= 0.0 || initValue > 1.0) {
            throw new OutOfRangeException(initValue, (Number)0, 1);
        }
        return new LearningFactorFunction(){
            private final QuasiSigmoidDecayFunction decay;
            {
                this.decay = new QuasiSigmoidDecayFunction(initValue, slope, numCall);
            }

            public double value(long n) {
                return this.decay.value(n);
            }
        };
    }
}

