/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import java.util.Random;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.RandomGeneratorFactory;

public class JDKRandomGenerator
extends Random
implements RandomGenerator {
    private static final long serialVersionUID = -7745277476784028798L;

    public JDKRandomGenerator() {
    }

    public JDKRandomGenerator(int seed) {
        this.setSeed(seed);
    }

    public void setSeed(int seed) {
        this.setSeed((long)seed);
    }

    public void setSeed(int[] seed) {
        this.setSeed(RandomGeneratorFactory.convertToLong(seed));
    }
}

