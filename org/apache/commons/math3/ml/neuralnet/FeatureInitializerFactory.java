/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Constant;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.ml.neuralnet.FeatureInitializer;
import org.apache.commons.math3.random.RandomGenerator;

public class FeatureInitializerFactory {
    private FeatureInitializerFactory() {
    }

    public static FeatureInitializer uniform(RandomGenerator rng, double min, double max) {
        return FeatureInitializerFactory.randomize(new UniformRealDistribution(rng, min, max), FeatureInitializerFactory.function(new Constant(0.0), 0.0, 0.0));
    }

    public static FeatureInitializer uniform(double min, double max) {
        return FeatureInitializerFactory.randomize(new UniformRealDistribution(min, max), FeatureInitializerFactory.function(new Constant(0.0), 0.0, 0.0));
    }

    public static FeatureInitializer function(final UnivariateFunction f, final double init, final double inc) {
        return new FeatureInitializer(){
            private double arg;
            {
                this.arg = init;
            }

            public double value() {
                double result = f.value(this.arg);
                this.arg += inc;
                return result;
            }
        };
    }

    public static FeatureInitializer randomize(final RealDistribution random, final FeatureInitializer orig) {
        return new FeatureInitializer(){

            public double value() {
                return orig.value() + random.sample();
            }
        };
    }
}

