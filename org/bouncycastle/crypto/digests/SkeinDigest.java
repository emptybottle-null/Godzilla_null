/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.digests.SkeinEngine;
import org.bouncycastle.crypto.params.SkeinParameters;
import org.bouncycastle.util.Memoable;

public class SkeinDigest
implements ExtendedDigest,
Memoable {
    public static final int SKEIN_256 = 256;
    public static final int SKEIN_512 = 512;
    public static final int SKEIN_1024 = 1024;
    private SkeinEngine engine;

    public SkeinDigest(int n, int n2) {
        this.engine = new SkeinEngine(n, n2);
        this.init(null);
    }

    public SkeinDigest(SkeinDigest skeinDigest) {
        this.engine = new SkeinEngine(skeinDigest.engine);
    }

    public void reset(Memoable memoable) {
        SkeinDigest skeinDigest = (SkeinDigest)memoable;
        this.engine.reset(skeinDigest.engine);
    }

    public Memoable copy() {
        return new SkeinDigest(this);
    }

    public String getAlgorithmName() {
        return "Skein-" + this.engine.getBlockSize() * 8 + "-" + this.engine.getOutputSize() * 8;
    }

    public int getDigestSize() {
        return this.engine.getOutputSize();
    }

    public int getByteLength() {
        return this.engine.getBlockSize();
    }

    public void init(SkeinParameters skeinParameters) {
        this.engine.init(skeinParameters);
    }

    public void reset() {
        this.engine.reset();
    }

    public void update(byte by) {
        this.engine.update(by);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.engine.update(byArray, n, n2);
    }

    public int doFinal(byte[] byArray, int n) {
        return this.engine.doFinal(byArray, n);
    }
}

