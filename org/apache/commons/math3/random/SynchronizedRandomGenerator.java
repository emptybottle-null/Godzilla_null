/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import org.apache.commons.math3.random.RandomGenerator;

public class SynchronizedRandomGenerator
implements RandomGenerator {
    private final RandomGenerator wrapped;

    public SynchronizedRandomGenerator(RandomGenerator rng) {
        this.wrapped = rng;
    }

    public synchronized void setSeed(int seed) {
        this.wrapped.setSeed(seed);
    }

    public synchronized void setSeed(int[] seed) {
        this.wrapped.setSeed(seed);
    }

    public synchronized void setSeed(long seed) {
        this.wrapped.setSeed(seed);
    }

    public synchronized void nextBytes(byte[] bytes) {
        this.wrapped.nextBytes(bytes);
    }

    public synchronized int nextInt() {
        return this.wrapped.nextInt();
    }

    public synchronized int nextInt(int n) {
        return this.wrapped.nextInt(n);
    }

    public synchronized long nextLong() {
        return this.wrapped.nextLong();
    }

    public synchronized boolean nextBoolean() {
        return this.wrapped.nextBoolean();
    }

    public synchronized float nextFloat() {
        return this.wrapped.nextFloat();
    }

    public synchronized double nextDouble() {
        return this.wrapped.nextDouble();
    }

    public synchronized double nextGaussian() {
        return this.wrapped.nextGaussian();
    }
}

