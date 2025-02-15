/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public abstract class GeneralDigest
implements ExtendedDigest,
Memoable {
    private static final int BYTE_LENGTH = 64;
    private final byte[] xBuf = new byte[4];
    private int xBufOff;
    private long byteCount;

    protected GeneralDigest() {
        this.xBufOff = 0;
    }

    protected GeneralDigest(GeneralDigest generalDigest) {
        this.copyIn(generalDigest);
    }

    protected GeneralDigest(byte[] byArray) {
        System.arraycopy(byArray, 0, this.xBuf, 0, this.xBuf.length);
        this.xBufOff = Pack.bigEndianToInt(byArray, 4);
        this.byteCount = Pack.bigEndianToLong(byArray, 8);
    }

    protected void copyIn(GeneralDigest generalDigest) {
        System.arraycopy(generalDigest.xBuf, 0, this.xBuf, 0, generalDigest.xBuf.length);
        this.xBufOff = generalDigest.xBufOff;
        this.byteCount = generalDigest.byteCount;
    }

    public void update(byte by) {
        this.xBuf[this.xBufOff++] = by;
        if (this.xBufOff == this.xBuf.length) {
            this.processWord(this.xBuf, 0);
            this.xBufOff = 0;
        }
        ++this.byteCount;
    }

    public void update(byte[] byArray, int n, int n2) {
        n2 = Math.max(0, n2);
        int n3 = 0;
        if (this.xBufOff != 0) {
            while (n3 < n2) {
                this.xBuf[this.xBufOff++] = byArray[n + n3++];
                if (this.xBufOff != 4) continue;
                this.processWord(this.xBuf, 0);
                this.xBufOff = 0;
                break;
            }
        }
        int n4 = (n2 - n3 & 0xFFFFFFFC) + n3;
        while (n3 < n4) {
            this.processWord(byArray, n + n3);
            n3 += 4;
        }
        while (n3 < n2) {
            this.xBuf[this.xBufOff++] = byArray[n + n3++];
        }
        this.byteCount += (long)n2;
    }

    public void finish() {
        long l = this.byteCount << 3;
        this.update((byte)-128);
        while (this.xBufOff != 0) {
            this.update((byte)0);
        }
        this.processLength(l);
        this.processBlock();
    }

    public void reset() {
        this.byteCount = 0L;
        this.xBufOff = 0;
        for (int i = 0; i < this.xBuf.length; ++i) {
            this.xBuf[i] = 0;
        }
    }

    protected void populateState(byte[] byArray) {
        System.arraycopy(this.xBuf, 0, byArray, 0, this.xBufOff);
        Pack.intToBigEndian(this.xBufOff, byArray, 4);
        Pack.longToBigEndian(this.byteCount, byArray, 8);
    }

    public int getByteLength() {
        return 64;
    }

    protected abstract void processWord(byte[] var1, int var2);

    protected abstract void processLength(long var1);

    protected abstract void processBlock();
}

