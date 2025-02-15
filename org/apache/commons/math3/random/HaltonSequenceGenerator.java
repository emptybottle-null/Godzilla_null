/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomVectorGenerator;
import org.apache.commons.math3.util.MathUtils;

public class HaltonSequenceGenerator
implements RandomVectorGenerator {
    private static final int[] PRIMES = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173};
    private static final int[] WEIGHTS = new int[]{1, 2, 3, 3, 8, 11, 12, 14, 7, 18, 12, 13, 17, 18, 29, 14, 18, 43, 41, 44, 40, 30, 47, 65, 71, 28, 40, 60, 79, 89, 56, 50, 52, 61, 108, 56, 66, 63, 60, 66};
    private final int dimension;
    private int count = 0;
    private final int[] base;
    private final int[] weight;

    public HaltonSequenceGenerator(int dimension) throws OutOfRangeException {
        this(dimension, PRIMES, WEIGHTS);
    }

    public HaltonSequenceGenerator(int dimension, int[] bases, int[] weights) throws NullArgumentException, OutOfRangeException, DimensionMismatchException {
        MathUtils.checkNotNull(bases);
        if (dimension < 1 || dimension > bases.length) {
            throw new OutOfRangeException(dimension, (Number)1, PRIMES.length);
        }
        if (weights != null && weights.length != bases.length) {
            throw new DimensionMismatchException(weights.length, bases.length);
        }
        this.dimension = dimension;
        this.base = (int[])bases.clone();
        this.weight = weights == null ? null : (int[])weights.clone();
        this.count = 0;
    }

    public double[] nextVector() {
        double[] v = new double[this.dimension];
        for (int i = 0; i < this.dimension; ++i) {
            int index = this.count;
            double f = 1.0 / (double)this.base[i];
            int j = 0;
            while (index > 0) {
                int digit = this.scramble(i, j, this.base[i], index % this.base[i]);
                int n = i;
                v[n] = v[n] + f * (double)digit;
                index /= this.base[i];
                f /= (double)this.base[i];
            }
        }
        ++this.count;
        return v;
    }

    protected int scramble(int i, int j, int b, int digit) {
        return this.weight != null ? this.weight[i] * digit % b : digit;
    }

    public double[] skipTo(int index) throws NotPositiveException {
        this.count = index;
        return this.nextVector();
    }

    public int getNextIndex() {
        return this.count;
    }
}

