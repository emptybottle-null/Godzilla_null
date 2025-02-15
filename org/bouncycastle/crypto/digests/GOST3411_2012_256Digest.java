/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.digests.GOST3411_2012Digest;
import org.bouncycastle.util.Memoable;

public final class GOST3411_2012_256Digest
extends GOST3411_2012Digest {
    private static final byte[] IV = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    public GOST3411_2012_256Digest() {
        super(IV);
    }

    public GOST3411_2012_256Digest(GOST3411_2012_256Digest gOST3411_2012_256Digest) {
        super(IV);
        this.reset(gOST3411_2012_256Digest);
    }

    public String getAlgorithmName() {
        return "GOST3411-2012-256";
    }

    public int getDigestSize() {
        return 32;
    }

    public int doFinal(byte[] byArray, int n) {
        byte[] byArray2 = new byte[64];
        super.doFinal(byArray2, 0);
        System.arraycopy(byArray2, 32, byArray, n, 32);
        return 32;
    }

    public Memoable copy() {
        return new GOST3411_2012_256Digest(this);
    }
}

