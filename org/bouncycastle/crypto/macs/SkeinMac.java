/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.digests.SkeinEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.SkeinParameters;

public class SkeinMac
implements Mac {
    public static final int SKEIN_256 = 256;
    public static final int SKEIN_512 = 512;
    public static final int SKEIN_1024 = 1024;
    private SkeinEngine engine;

    public SkeinMac(int n, int n2) {
        this.engine = new SkeinEngine(n, n2);
    }

    public SkeinMac(SkeinMac skeinMac) {
        this.engine = new SkeinEngine(skeinMac.engine);
    }

    public String getAlgorithmName() {
        return "Skein-MAC-" + this.engine.getBlockSize() * 8 + "-" + this.engine.getOutputSize() * 8;
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        SkeinParameters skeinParameters;
        if (cipherParameters instanceof SkeinParameters) {
            skeinParameters = (SkeinParameters)cipherParameters;
        } else if (cipherParameters instanceof KeyParameter) {
            skeinParameters = new SkeinParameters.Builder().setKey(((KeyParameter)cipherParameters).getKey()).build();
        } else {
            throw new IllegalArgumentException("Invalid parameter passed to Skein MAC init - " + cipherParameters.getClass().getName());
        }
        if (skeinParameters.getKey() == null) {
            throw new IllegalArgumentException("Skein MAC requires a key parameter.");
        }
        this.engine.init(skeinParameters);
    }

    public int getMacSize() {
        return this.engine.getOutputSize();
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

