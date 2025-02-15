/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.RandomGenerator;

public class DigestRandomGenerator
implements RandomGenerator {
    private static long CYCLE_COUNT = 10L;
    private long stateCounter;
    private long seedCounter;
    private Digest digest;
    private byte[] state;
    private byte[] seed;

    public DigestRandomGenerator(Digest digest) {
        this.digest = digest;
        this.seed = new byte[digest.getDigestSize()];
        this.seedCounter = 1L;
        this.state = new byte[digest.getDigestSize()];
        this.stateCounter = 1L;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addSeedMaterial(byte[] byArray) {
        DigestRandomGenerator digestRandomGenerator = this;
        synchronized (digestRandomGenerator) {
            this.digestUpdate(byArray);
            this.digestUpdate(this.seed);
            this.digestDoFinal(this.seed);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addSeedMaterial(long l) {
        DigestRandomGenerator digestRandomGenerator = this;
        synchronized (digestRandomGenerator) {
            this.digestAddCounter(l);
            this.digestUpdate(this.seed);
            this.digestDoFinal(this.seed);
        }
    }

    public void nextBytes(byte[] byArray) {
        this.nextBytes(byArray, 0, byArray.length);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void nextBytes(byte[] byArray, int n, int n2) {
        DigestRandomGenerator digestRandomGenerator = this;
        synchronized (digestRandomGenerator) {
            int n3 = 0;
            this.generateState();
            int n4 = n + n2;
            for (int i = n; i != n4; ++i) {
                if (n3 == this.state.length) {
                    this.generateState();
                    n3 = 0;
                }
                byArray[i] = this.state[n3++];
            }
        }
    }

    private void cycleSeed() {
        this.digestUpdate(this.seed);
        this.digestAddCounter(this.seedCounter++);
        this.digestDoFinal(this.seed);
    }

    private void generateState() {
        this.digestAddCounter(this.stateCounter++);
        this.digestUpdate(this.state);
        this.digestUpdate(this.seed);
        this.digestDoFinal(this.state);
        if (this.stateCounter % CYCLE_COUNT == 0L) {
            this.cycleSeed();
        }
    }

    private void digestAddCounter(long l) {
        for (int i = 0; i != 8; ++i) {
            this.digest.update((byte)l);
            l >>>= 8;
        }
    }

    private void digestUpdate(byte[] byArray) {
        this.digest.update(byArray, 0, byArray.length);
    }

    private void digestDoFinal(byte[] byArray) {
        this.digest.doFinal(byArray, 0);
    }
}

