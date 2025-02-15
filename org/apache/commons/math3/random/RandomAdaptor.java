/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import java.util.Random;
import org.apache.commons.math3.random.RandomGenerator;

public class RandomAdaptor
extends Random
implements RandomGenerator {
    private static final long serialVersionUID = 2306581345647615033L;
    private final RandomGenerator randomGenerator;

    private RandomAdaptor() {
        this.randomGenerator = null;
    }

    public RandomAdaptor(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public static Random createAdaptor(RandomGenerator randomGenerator) {
        return new RandomAdaptor(randomGenerator);
    }

    public boolean nextBoolean() {
        return this.randomGenerator.nextBoolean();
    }

    public void nextBytes(byte[] bytes) {
        this.randomGenerator.nextBytes(bytes);
    }

    public double nextDouble() {
        return this.randomGenerator.nextDouble();
    }

    public float nextFloat() {
        return this.randomGenerator.nextFloat();
    }

    public double nextGaussian() {
        return this.randomGenerator.nextGaussian();
    }

    public int nextInt() {
        return this.randomGenerator.nextInt();
    }

    public int nextInt(int n) {
        return this.randomGenerator.nextInt(n);
    }

    public long nextLong() {
        return this.randomGenerator.nextLong();
    }

    public void setSeed(int seed) {
        if (this.randomGenerator != null) {
            this.randomGenerator.setSeed(seed);
        }
    }

    public void setSeed(int[] seed) {
        if (this.randomGenerator != null) {
            this.randomGenerator.setSeed(seed);
        }
    }

    public void setSeed(long seed) {
        if (this.randomGenerator != null) {
            this.randomGenerator.setSeed(seed);
        }
    }
}

