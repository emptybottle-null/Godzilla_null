/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.RandomVectorGenerator;
import org.apache.commons.math3.util.FastMath;

public class UnitSphereRandomVectorGenerator
implements RandomVectorGenerator {
    private final RandomGenerator rand;
    private final int dimension;

    public UnitSphereRandomVectorGenerator(int dimension, RandomGenerator rand) {
        this.dimension = dimension;
        this.rand = rand;
    }

    public UnitSphereRandomVectorGenerator(int dimension) {
        this(dimension, new MersenneTwister());
    }

    public double[] nextVector() {
        double[] v = new double[this.dimension];
        double normSq = 0.0;
        for (int i = 0; i < this.dimension; ++i) {
            double comp;
            v[i] = comp = this.rand.nextGaussian();
            normSq += comp * comp;
        }
        double f = 1.0 / FastMath.sqrt(normSq);
        int i = 0;
        while (i < this.dimension) {
            int n = i++;
            v[n] = v[n] * f;
        }
        return v;
    }
}

