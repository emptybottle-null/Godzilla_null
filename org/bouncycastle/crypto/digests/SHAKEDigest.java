/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.KeccakDigest;

public class SHAKEDigest
extends KeccakDigest
implements Xof {
    private static int checkBitLength(int n) {
        switch (n) {
            case 128: 
            case 256: {
                return n;
            }
        }
        throw new IllegalArgumentException("'bitLength' " + n + " not supported for SHAKE");
    }

    public SHAKEDigest() {
        this(128);
    }

    public SHAKEDigest(int n) {
        super(SHAKEDigest.checkBitLength(n));
    }

    public SHAKEDigest(SHAKEDigest sHAKEDigest) {
        super(sHAKEDigest);
    }

    public String getAlgorithmName() {
        return "SHAKE" + this.fixedOutputLength;
    }

    public int doFinal(byte[] byArray, int n) {
        return this.doFinal(byArray, n, this.getDigestSize());
    }

    public int doFinal(byte[] byArray, int n, int n2) {
        int n3 = this.doOutput(byArray, n, n2);
        this.reset();
        return n3;
    }

    public int doOutput(byte[] byArray, int n, int n2) {
        if (!this.squeezing) {
            this.absorbBits(15, 4);
        }
        this.squeeze(byArray, n, (long)n2 * 8L);
        return n2;
    }

    protected int doFinal(byte[] byArray, int n, byte by, int n2) {
        return this.doFinal(byArray, n, this.getDigestSize(), by, n2);
    }

    protected int doFinal(byte[] byArray, int n, int n2, byte by, int n3) {
        if (n3 < 0 || n3 > 7) {
            throw new IllegalArgumentException("'partialBits' must be in the range [0,7]");
        }
        int n4 = by & (1 << n3) - 1 | 15 << n3;
        int n5 = n3 + 4;
        if (n5 >= 8) {
            this.absorb(new byte[]{(byte)n4}, 0, 1);
            n5 -= 8;
            n4 >>>= 8;
        }
        if (n5 > 0) {
            this.absorbBits(n4, n5);
        }
        this.squeeze(byArray, n, (long)n2 * 8L);
        this.reset();
        return n2;
    }
}

