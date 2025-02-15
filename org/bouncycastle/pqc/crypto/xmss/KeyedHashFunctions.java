/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;

final class KeyedHashFunctions {
    private final Digest digest;
    private final int digestSize;

    protected KeyedHashFunctions(Digest digest, int n) {
        if (digest == null) {
            throw new NullPointerException("digest == null");
        }
        this.digest = digest;
        this.digestSize = n;
    }

    private byte[] coreDigest(int n, byte[] byArray, byte[] byArray2) {
        byte[] byArray3 = XMSSUtil.toBytesBigEndian(n, this.digestSize);
        this.digest.update(byArray3, 0, byArray3.length);
        this.digest.update(byArray, 0, byArray.length);
        this.digest.update(byArray2, 0, byArray2.length);
        byte[] byArray4 = new byte[this.digestSize];
        if (this.digest instanceof Xof) {
            ((Xof)this.digest).doFinal(byArray4, 0, this.digestSize);
        } else {
            this.digest.doFinal(byArray4, 0);
        }
        return byArray4;
    }

    protected byte[] F(byte[] byArray, byte[] byArray2) {
        if (byArray.length != this.digestSize) {
            throw new IllegalArgumentException("wrong key length");
        }
        if (byArray2.length != this.digestSize) {
            throw new IllegalArgumentException("wrong in length");
        }
        return this.coreDigest(0, byArray, byArray2);
    }

    protected byte[] H(byte[] byArray, byte[] byArray2) {
        if (byArray.length != this.digestSize) {
            throw new IllegalArgumentException("wrong key length");
        }
        if (byArray2.length != 2 * this.digestSize) {
            throw new IllegalArgumentException("wrong in length");
        }
        return this.coreDigest(1, byArray, byArray2);
    }

    protected byte[] HMsg(byte[] byArray, byte[] byArray2) {
        if (byArray.length != 3 * this.digestSize) {
            throw new IllegalArgumentException("wrong key length");
        }
        return this.coreDigest(2, byArray, byArray2);
    }

    protected byte[] PRF(byte[] byArray, byte[] byArray2) {
        if (byArray.length != this.digestSize) {
            throw new IllegalArgumentException("wrong key length");
        }
        if (byArray2.length != 32) {
            throw new IllegalArgumentException("wrong address length");
        }
        return this.coreDigest(3, byArray, byArray2);
    }
}

