/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractRandomGenerator
implements RandomGenerator {
    private double cachedNormalDeviate = Double.NaN;

    public void clear() {
        this.cachedNormalDeviate = Double.NaN;
    }

    public void setSeed(int seed) {
        this.setSeed((long)seed);
    }

    public void setSeed(int[] seed) {
        long prime = 0xFFFFFFFBL;
        long combined = 0L;
        for (int s : seed) {
            combined = combined * 0xFFFFFFFBL + (long)s;
        }
        this.setSeed(combined);
    }

    public abstract void setSeed(long var1);

    public void nextBytes(byte[] bytes) {
        int bytesOut = 0;
        while (bytesOut < bytes.length) {
            int randInt = this.nextInt();
            for (int i = 0; i < 3; ++i) {
                if (i > 0) {
                    randInt >>= 8;
                }
                bytes[bytesOut++] = (byte)randInt;
                if (bytesOut != bytes.length) continue;
                return;
            }
        }
    }

    public int nextInt() {
        return (int)((2.0 * this.nextDouble() - 1.0) * 2.147483647E9);
    }

    public int nextInt(int n) {
        if (n <= 0) {
            throw new NotStrictlyPositiveException(n);
        }
        int result = (int)(this.nextDouble() * (double)n);
        return result < n ? result : n - 1;
    }

    public long nextLong() {
        return (long)((2.0 * this.nextDouble() - 1.0) * 9.223372036854776E18);
    }

    public boolean nextBoolean() {
        return this.nextDouble() <= 0.5;
    }

    public float nextFloat() {
        return (float)this.nextDouble();
    }

    public abstract double nextDouble();

    public double nextGaussian() {
        if (!Double.isNaN(this.cachedNormalDeviate)) {
            double dev = this.cachedNormalDeviate;
            this.cachedNormalDeviate = Double.NaN;
            return dev;
        }
        double v1 = 0.0;
        double v2 = 0.0;
        double s = 1.0;
        while (s >= 1.0) {
            v1 = 2.0 * this.nextDouble() - 1.0;
            v2 = 2.0 * this.nextDouble() - 1.0;
            s = v1 * v1 + v2 * v2;
        }
        if (s != 0.0) {
            s = FastMath.sqrt(-2.0 * FastMath.log(s) / s);
        }
        this.cachedNormalDeviate = v2 * s;
        return v1 * s;
    }
}

