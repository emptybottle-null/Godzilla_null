/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

public class ThreadedSeedGenerator {
    public byte[] generateSeed(int n, boolean bl) {
        SeedGenerator seedGenerator = new SeedGenerator();
        return seedGenerator.generateSeed(n, bl);
    }

    private class SeedGenerator
    implements Runnable {
        private volatile int counter = 0;
        private volatile boolean stop = false;

        private SeedGenerator() {
        }

        public void run() {
            while (!this.stop) {
                ++this.counter;
            }
        }

        public byte[] generateSeed(int n, boolean bl) {
            Thread thread = new Thread(this);
            byte[] byArray = new byte[n];
            this.counter = 0;
            this.stop = false;
            int n2 = 0;
            thread.start();
            int n3 = bl ? n : n * 8;
            for (int i = 0; i < n3; ++i) {
                while (this.counter == n2) {
                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException interruptedException) {}
                }
                n2 = this.counter;
                if (bl) {
                    byArray[i] = (byte)(n2 & 0xFF);
                    continue;
                }
                int n4 = i / 8;
                byArray[n4] = (byte)(byArray[n4] << 1 | n2 & 1);
            }
            this.stop = true;
            return byArray;
        }
    }
}

