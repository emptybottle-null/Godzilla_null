/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.digests.EncodableDigest;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public abstract class LongDigest
implements ExtendedDigest,
Memoable,
EncodableDigest {
    private static final int BYTE_LENGTH = 128;
    private byte[] xBuf = new byte[8];
    private int xBufOff;
    private long byteCount1;
    private long byteCount2;
    protected long H1;
    protected long H2;
    protected long H3;
    protected long H4;
    protected long H5;
    protected long H6;
    protected long H7;
    protected long H8;
    private long[] W = new long[80];
    private int wOff;
    static final long[] K = new long[]{4794697086780616226L, 8158064640168781261L, -5349999486874862801L, -1606136188198331460L, 4131703408338449720L, 6480981068601479193L, -7908458776815382629L, -6116909921290321640L, -2880145864133508542L, 1334009975649890238L, 2608012711638119052L, 6128411473006802146L, 8268148722764581231L, -9160688886553864527L, -7215885187991268811L, -4495734319001033068L, -1973867731355612462L, -1171420211273849373L, 1135362057144423861L, 2597628984639134821L, 3308224258029322869L, 5365058923640841347L, 6679025012923562964L, 8573033837759648693L, -7476448914759557205L, -6327057829258317296L, -5763719355590565569L, -4658551843659510044L, -4116276920077217854L, -3051310485924567259L, 489312712824947311L, 1452737877330783856L, 2861767655752347644L, 3322285676063803686L, 5560940570517711597L, 5996557281743188959L, 7280758554555802590L, 8532644243296465576L, -9096487096722542874L, -7894198246740708037L, -6719396339535248540L, -6333637450476146687L, -4446306890439682159L, -4076793802049405392L, -3345356375505022440L, -2983346525034927856L, -860691631967231958L, 1182934255886127544L, 1847814050463011016L, 2177327727835720531L, 2830643537854262169L, 3796741975233480872L, 4115178125766777443L, 5681478168544905931L, 6601373596472566643L, 7507060721942968483L, 8399075790359081724L, 8693463985226723168L, -8878714635349349518L, -8302665154208450068L, -8016688836872298968L, -6606660893046293015L, -4685533653050689259L, -4147400797238176981L, -3880063495543823972L, -3348786107499101689L, -1523767162380948706L, -757361751448694408L, 500013540394364858L, 748580250866718886L, 1242879168328830382L, 1977374033974150939L, 2944078676154940804L, 3659926193048069267L, 4368137639120453308L, 4836135668995329356L, 5532061633213252278L, 6448918945643986474L, 6902733635092675308L, 7801388544844847127L};

    protected LongDigest() {
        this.xBufOff = 0;
        this.reset();
    }

    protected LongDigest(LongDigest longDigest) {
        this.copyIn(longDigest);
    }

    protected void copyIn(LongDigest longDigest) {
        System.arraycopy(longDigest.xBuf, 0, this.xBuf, 0, longDigest.xBuf.length);
        this.xBufOff = longDigest.xBufOff;
        this.byteCount1 = longDigest.byteCount1;
        this.byteCount2 = longDigest.byteCount2;
        this.H1 = longDigest.H1;
        this.H2 = longDigest.H2;
        this.H3 = longDigest.H3;
        this.H4 = longDigest.H4;
        this.H5 = longDigest.H5;
        this.H6 = longDigest.H6;
        this.H7 = longDigest.H7;
        this.H8 = longDigest.H8;
        System.arraycopy(longDigest.W, 0, this.W, 0, longDigest.W.length);
        this.wOff = longDigest.wOff;
    }

    protected void populateState(byte[] byArray) {
        System.arraycopy(this.xBuf, 0, byArray, 0, this.xBufOff);
        Pack.intToBigEndian(this.xBufOff, byArray, 8);
        Pack.longToBigEndian(this.byteCount1, byArray, 12);
        Pack.longToBigEndian(this.byteCount2, byArray, 20);
        Pack.longToBigEndian(this.H1, byArray, 28);
        Pack.longToBigEndian(this.H2, byArray, 36);
        Pack.longToBigEndian(this.H3, byArray, 44);
        Pack.longToBigEndian(this.H4, byArray, 52);
        Pack.longToBigEndian(this.H5, byArray, 60);
        Pack.longToBigEndian(this.H6, byArray, 68);
        Pack.longToBigEndian(this.H7, byArray, 76);
        Pack.longToBigEndian(this.H8, byArray, 84);
        Pack.intToBigEndian(this.wOff, byArray, 92);
        for (int i = 0; i < this.wOff; ++i) {
            Pack.longToBigEndian(this.W[i], byArray, 96 + i * 8);
        }
    }

    protected void restoreState(byte[] byArray) {
        this.xBufOff = Pack.bigEndianToInt(byArray, 8);
        System.arraycopy(byArray, 0, this.xBuf, 0, this.xBufOff);
        this.byteCount1 = Pack.bigEndianToLong(byArray, 12);
        this.byteCount2 = Pack.bigEndianToLong(byArray, 20);
        this.H1 = Pack.bigEndianToLong(byArray, 28);
        this.H2 = Pack.bigEndianToLong(byArray, 36);
        this.H3 = Pack.bigEndianToLong(byArray, 44);
        this.H4 = Pack.bigEndianToLong(byArray, 52);
        this.H5 = Pack.bigEndianToLong(byArray, 60);
        this.H6 = Pack.bigEndianToLong(byArray, 68);
        this.H7 = Pack.bigEndianToLong(byArray, 76);
        this.H8 = Pack.bigEndianToLong(byArray, 84);
        this.wOff = Pack.bigEndianToInt(byArray, 92);
        for (int i = 0; i < this.wOff; ++i) {
            this.W[i] = Pack.bigEndianToLong(byArray, 96 + i * 8);
        }
    }

    protected int getEncodedStateSize() {
        return 96 + this.wOff * 8;
    }

    public void update(byte by) {
        this.xBuf[this.xBufOff++] = by;
        if (this.xBufOff == this.xBuf.length) {
            this.processWord(this.xBuf, 0);
            this.xBufOff = 0;
        }
        ++this.byteCount1;
    }

    public void update(byte[] byArray, int n, int n2) {
        while (this.xBufOff != 0 && n2 > 0) {
            this.update(byArray[n]);
            ++n;
            --n2;
        }
        while (n2 > this.xBuf.length) {
            this.processWord(byArray, n);
            n += this.xBuf.length;
            n2 -= this.xBuf.length;
            this.byteCount1 += (long)this.xBuf.length;
        }
        while (n2 > 0) {
            this.update(byArray[n]);
            ++n;
            --n2;
        }
    }

    public void finish() {
        this.adjustByteCounts();
        long l = this.byteCount1 << 3;
        long l2 = this.byteCount2;
        this.update((byte)-128);
        while (this.xBufOff != 0) {
            this.update((byte)0);
        }
        this.processLength(l, l2);
        this.processBlock();
    }

    public void reset() {
        int n;
        this.byteCount1 = 0L;
        this.byteCount2 = 0L;
        this.xBufOff = 0;
        for (n = 0; n < this.xBuf.length; ++n) {
            this.xBuf[n] = 0;
        }
        this.wOff = 0;
        for (n = 0; n != this.W.length; ++n) {
            this.W[n] = 0L;
        }
    }

    public int getByteLength() {
        return 128;
    }

    protected void processWord(byte[] byArray, int n) {
        this.W[this.wOff] = Pack.bigEndianToLong(byArray, n);
        if (++this.wOff == 16) {
            this.processBlock();
        }
    }

    private void adjustByteCounts() {
        if (this.byteCount1 > 0x1FFFFFFFFFFFFFFFL) {
            this.byteCount2 += this.byteCount1 >>> 61;
            this.byteCount1 &= 0x1FFFFFFFFFFFFFFFL;
        }
    }

    protected void processLength(long l, long l2) {
        if (this.wOff > 14) {
            this.processBlock();
        }
        this.W[14] = l2;
        this.W[15] = l;
    }

    protected void processBlock() {
        int n;
        this.adjustByteCounts();
        for (int i = 16; i <= 79; ++i) {
            this.W[i] = this.Sigma1(this.W[i - 2]) + this.W[i - 7] + this.Sigma0(this.W[i - 15]) + this.W[i - 16];
        }
        long l = this.H1;
        long l2 = this.H2;
        long l3 = this.H3;
        long l4 = this.H4;
        long l5 = this.H5;
        long l6 = this.H6;
        long l7 = this.H7;
        long l8 = this.H8;
        int n2 = 0;
        for (n = 0; n < 10; ++n) {
            long l9 = this.Sum1(l5) + this.Ch(l5, l6, l7) + K[n2];
            int n3 = n2++;
            l8 += this.Sum0(l) + this.Maj(l, l2, l3);
            long l10 = this.Sum1(l4 += (l8 += l9 + this.W[n3])) + this.Ch(l4, l5, l6) + K[n2];
            int n4 = n2++;
            l7 += this.Sum0(l8) + this.Maj(l8, l, l2);
            long l11 = this.Sum1(l3 += (l7 += l10 + this.W[n4])) + this.Ch(l3, l4, l5) + K[n2];
            int n5 = n2++;
            l6 += this.Sum0(l7) + this.Maj(l7, l8, l);
            long l12 = this.Sum1(l2 += (l6 += l11 + this.W[n5])) + this.Ch(l2, l3, l4) + K[n2];
            int n6 = n2++;
            l5 += this.Sum0(l6) + this.Maj(l6, l7, l8);
            long l13 = this.Sum1(l += (l5 += l12 + this.W[n6])) + this.Ch(l, l2, l3) + K[n2];
            int n7 = n2++;
            l4 += this.Sum0(l5) + this.Maj(l5, l6, l7);
            long l14 = this.Sum1(l8 += (l4 += l13 + this.W[n7])) + this.Ch(l8, l, l2) + K[n2];
            int n8 = n2++;
            l3 += this.Sum0(l4) + this.Maj(l4, l5, l6);
            long l15 = this.Sum1(l7 += (l3 += l14 + this.W[n8])) + this.Ch(l7, l8, l) + K[n2];
            int n9 = n2++;
            l2 += this.Sum0(l3) + this.Maj(l3, l4, l5);
            l5 += (l += this.Sum1(l6 += (l2 += l15 + this.W[n9])) + this.Ch(l6, l7, l8) + K[n2] + this.W[n2++]);
            l += this.Sum0(l2) + this.Maj(l2, l3, l4);
        }
        this.H1 += l;
        this.H2 += l2;
        this.H3 += l3;
        this.H4 += l4;
        this.H5 += l5;
        this.H6 += l6;
        this.H7 += l7;
        this.H8 += l8;
        this.wOff = 0;
        for (n = 0; n < 16; ++n) {
            this.W[n] = 0L;
        }
    }

    private long Ch(long l, long l2, long l3) {
        return l & l2 ^ (l ^ 0xFFFFFFFFFFFFFFFFL) & l3;
    }

    private long Maj(long l, long l2, long l3) {
        return l & l2 ^ l & l3 ^ l2 & l3;
    }

    private long Sum0(long l) {
        return (l << 36 | l >>> 28) ^ (l << 30 | l >>> 34) ^ (l << 25 | l >>> 39);
    }

    private long Sum1(long l) {
        return (l << 50 | l >>> 14) ^ (l << 46 | l >>> 18) ^ (l << 23 | l >>> 41);
    }

    private long Sigma0(long l) {
        return (l << 63 | l >>> 1) ^ (l << 56 | l >>> 8) ^ l >>> 7;
    }

    private long Sigma1(long l) {
        return (l << 45 | l >>> 19) ^ (l << 3 | l >>> 61) ^ l >>> 6;
    }
}

