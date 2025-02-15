/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import java.util.Random;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.random.RandomGenerator;

public class RandomGeneratorFactory {
    private RandomGeneratorFactory() {
    }

    public static RandomGenerator createRandomGenerator(final Random rng) {
        return new RandomGenerator(){

            public void setSeed(int seed) {
                rng.setSeed(seed);
            }

            public void setSeed(int[] seed) {
                rng.setSeed(RandomGeneratorFactory.convertToLong(seed));
            }

            public void setSeed(long seed) {
                rng.setSeed(seed);
            }

            public void nextBytes(byte[] bytes) {
                rng.nextBytes(bytes);
            }

            public int nextInt() {
                return rng.nextInt();
            }

            public int nextInt(int n) {
                if (n <= 0) {
                    throw new NotStrictlyPositiveException(n);
                }
                return rng.nextInt(n);
            }

            public long nextLong() {
                return rng.nextLong();
            }

            public boolean nextBoolean() {
                return rng.nextBoolean();
            }

            public float nextFloat() {
                return rng.nextFloat();
            }

            public double nextDouble() {
                return rng.nextDouble();
            }

            public double nextGaussian() {
                return rng.nextGaussian();
            }
        };
    }

    public static long convertToLong(int[] seed) {
        long prime = 0xFFFFFFFBL;
        long combined = 0L;
        for (int s : seed) {
            combined = combined * 0xFFFFFFFBL + (long)s;
        }
        return combined;
    }
}

