/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;

public class NonMemoableDigest
implements ExtendedDigest {
    private ExtendedDigest baseDigest;

    public NonMemoableDigest(ExtendedDigest extendedDigest) {
        if (extendedDigest == null) {
            throw new IllegalArgumentException("baseDigest must not be null");
        }
        this.baseDigest = extendedDigest;
    }

    public String getAlgorithmName() {
        return this.baseDigest.getAlgorithmName();
    }

    public int getDigestSize() {
        return this.baseDigest.getDigestSize();
    }

    public void update(byte by) {
        this.baseDigest.update(by);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.baseDigest.update(byArray, n, n2);
    }

    public int doFinal(byte[] byArray, int n) {
        return this.baseDigest.doFinal(byArray, n);
    }

    public void reset() {
        this.baseDigest.reset();
    }

    public int getByteLength() {
        return this.baseDigest.getByteLength();
    }
}

