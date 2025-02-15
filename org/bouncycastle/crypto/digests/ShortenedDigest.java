/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;

public class ShortenedDigest
implements ExtendedDigest {
    private ExtendedDigest baseDigest;
    private int length;

    public ShortenedDigest(ExtendedDigest extendedDigest, int n) {
        if (extendedDigest == null) {
            throw new IllegalArgumentException("baseDigest must not be null");
        }
        if (n > extendedDigest.getDigestSize()) {
            throw new IllegalArgumentException("baseDigest output not large enough to support length");
        }
        this.baseDigest = extendedDigest;
        this.length = n;
    }

    public String getAlgorithmName() {
        return this.baseDigest.getAlgorithmName() + "(" + this.length * 8 + ")";
    }

    public int getDigestSize() {
        return this.length;
    }

    public void update(byte by) {
        this.baseDigest.update(by);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.baseDigest.update(byArray, n, n2);
    }

    public int doFinal(byte[] byArray, int n) {
        byte[] byArray2 = new byte[this.baseDigest.getDigestSize()];
        this.baseDigest.doFinal(byArray2, 0);
        System.arraycopy(byArray2, 0, byArray, n, this.length);
        return this.length;
    }

    public void reset() {
        this.baseDigest.reset();
    }

    public int getByteLength() {
        return this.baseDigest.getByteLength();
    }
}

