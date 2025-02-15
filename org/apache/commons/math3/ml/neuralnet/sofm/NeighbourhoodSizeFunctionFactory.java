/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet.sofm;

import org.apache.commons.math3.ml.neuralnet.sofm.NeighbourhoodSizeFunction;
import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;
import org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction;
import org.apache.commons.math3.util.FastMath;

public class NeighbourhoodSizeFunctionFactory {
    private NeighbourhoodSizeFunctionFactory() {
    }

    public static NeighbourhoodSizeFunction exponentialDecay(final double initValue, final double valueAtNumCall, final long numCall) {
        return new NeighbourhoodSizeFunction(){
            private final ExponentialDecayFunction decay;
            {
                this.decay = new ExponentialDecayFunction(initValue, valueAtNumCall, numCall);
            }

            public int value(long n) {
                return (int)FastMath.rint(this.decay.value(n));
            }
        };
    }

    public static NeighbourhoodSizeFunction quasiSigmoidDecay(final double initValue, final double slope, final long numCall) {
        return new NeighbourhoodSizeFunction(){
            private final QuasiSigmoidDecayFunction decay;
            {
                this.decay = new QuasiSigmoidDecayFunction(initValue, slope, numCall);
            }

            public int value(long n) {
                return (int)FastMath.rint(this.decay.value(n));
            }
        };
    }
}

