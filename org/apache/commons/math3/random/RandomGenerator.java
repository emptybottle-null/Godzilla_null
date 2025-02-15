/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

public interface RandomGenerator {
    public void setSeed(int var1);

    public void setSeed(int[] var1);

    public void setSeed(long var1);

    public void nextBytes(byte[] var1);

    public int nextInt();

    public int nextInt(int var1);

    public long nextLong();

    public boolean nextBoolean();

    public float nextFloat();

    public double nextDouble();

    public double nextGaussian();
}

