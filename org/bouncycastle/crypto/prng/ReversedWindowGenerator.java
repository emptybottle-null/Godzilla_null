/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

import org.bouncycastle.crypto.prng.RandomGenerator;

public class ReversedWindowGenerator
implements RandomGenerator {
    private final RandomGenerator generator;
    private byte[] window;
    private int windowCount;

    public ReversedWindowGenerator(RandomGenerator randomGenerator, int n) {
        if (randomGenerator == null) {
            throw new IllegalArgumentException("generator cannot be null");
        }
        if (n < 2) {
            throw new IllegalArgumentException("windowSize must be at least 2");
        }
        this.generator = randomGenerator;
        this.window = new byte[n];
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addSeedMaterial(byte[] byArray) {
        ReversedWindowGenerator reversedWindowGenerator = this;
        synchronized (reversedWindowGenerator) {
            this.windowCount = 0;
            this.generator.addSeedMaterial(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addSeedMaterial(long l) {
        ReversedWindowGenerator reversedWindowGenerator = this;
        synchronized (reversedWindowGenerator) {
            this.windowCount = 0;
            this.generator.addSeedMaterial(l);
        }
    }

    public void nextBytes(byte[] byArray) {
        this.doNextBytes(byArray, 0, byArray.length);
    }

    public void nextBytes(byte[] byArray, int n, int n2) {
        this.doNextBytes(byArray, n, n2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void doNextBytes(byte[] byArray, int n, int n2) {
        ReversedWindowGenerator reversedWindowGenerator = this;
        synchronized (reversedWindowGenerator) {
            int n3 = 0;
            while (n3 < n2) {
                if (this.windowCount < 1) {
                    this.generator.nextBytes(this.window, 0, this.window.length);
                    this.windowCount = this.window.length;
                }
                byArray[n + n3++] = this.window[--this.windowCount];
            }
        }
    }
}

