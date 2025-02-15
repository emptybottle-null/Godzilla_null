/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.digests.LongDigest;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class SHA384Digest
extends LongDigest {
    private static final int DIGEST_LENGTH = 48;

    public SHA384Digest() {
    }

    public SHA384Digest(SHA384Digest sHA384Digest) {
        super(sHA384Digest);
    }

    public SHA384Digest(byte[] byArray) {
        this.restoreState(byArray);
    }

    public String getAlgorithmName() {
        return "SHA-384";
    }

    public int getDigestSize() {
        return 48;
    }

    public int doFinal(byte[] byArray, int n) {
        this.finish();
        Pack.longToBigEndian(this.H1, byArray, n);
        Pack.longToBigEndian(this.H2, byArray, n + 8);
        Pack.longToBigEndian(this.H3, byArray, n + 16);
        Pack.longToBigEndian(this.H4, byArray, n + 24);
        Pack.longToBigEndian(this.H5, byArray, n + 32);
        Pack.longToBigEndian(this.H6, byArray, n + 40);
        this.reset();
        return 48;
    }

    public void reset() {
        super.reset();
        this.H1 = -3766243637369397544L;
        this.H2 = 7105036623409894663L;
        this.H3 = -7973340178411365097L;
        this.H4 = 1526699215303891257L;
        this.H5 = 7436329637833083697L;
        this.H6 = -8163818279084223215L;
        this.H7 = -2662702644619276377L;
        this.H8 = 5167115440072839076L;
    }

    public Memoable copy() {
        return new SHA384Digest(this);
    }

    public void reset(Memoable memoable) {
        SHA384Digest sHA384Digest = (SHA384Digest)memoable;
        super.copyIn(sHA384Digest);
    }

    public byte[] getEncodedState() {
        byte[] byArray = new byte[this.getEncodedStateSize()];
        super.populateState(byArray);
        return byArray;
    }
}

