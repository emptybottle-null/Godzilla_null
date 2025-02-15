/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.selector;

import java.io.IOException;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.Pack;

class MSOutlookKeyIdCalculator {
    MSOutlookKeyIdCalculator() {
    }

    static byte[] calculateKeyId(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        SHA1Digest sHA1Digest = new SHA1Digest();
        byte[] byArray = new byte[sHA1Digest.getDigestSize()];
        byte[] byArray2 = new byte[]{};
        try {
            byArray2 = subjectPublicKeyInfo.getEncoded("DER");
        } catch (IOException iOException) {
            return new byte[0];
        }
        sHA1Digest.update(byArray2, 0, byArray2.length);
        sHA1Digest.doFinal(byArray, 0);
        return byArray;
    }

    private static abstract class GeneralDigest {
        private static final int BYTE_LENGTH = 64;
        private byte[] xBuf;
        private int xBufOff;
        private long byteCount;

        protected GeneralDigest() {
            this.xBuf = new byte[4];
            this.xBufOff = 0;
        }

        protected GeneralDigest(GeneralDigest generalDigest) {
            this.xBuf = new byte[generalDigest.xBuf.length];
            this.copyIn(generalDigest);
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
            while (this.xBufOff != 0 && n2 > 0) {
                this.update(byArray[n]);
                ++n;
                --n2;
            }
            while (n2 > this.xBuf.length) {
                this.processWord(byArray, n);
                n += this.xBuf.length;
                n2 -= this.xBuf.length;
                this.byteCount += (long)this.xBuf.length;
            }
            while (n2 > 0) {
                this.update(byArray[n]);
                ++n;
                --n2;
            }
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

        protected abstract void processWord(byte[] var1, int var2);

        protected abstract void processLength(long var1);

        protected abstract void processBlock();
    }

    private static class SHA1Digest
    extends GeneralDigest {
        private static final int DIGEST_LENGTH = 20;
        private int H1;
        private int H2;
        private int H3;
        private int H4;
        private int H5;
        private int[] X = new int[80];
        private int xOff;
        private static final int Y1 = 1518500249;
        private static final int Y2 = 1859775393;
        private static final int Y3 = -1894007588;
        private static final int Y4 = -899497514;

        public SHA1Digest() {
            this.reset();
        }

        public String getAlgorithmName() {
            return "SHA-1";
        }

        public int getDigestSize() {
            return 20;
        }

        protected void processWord(byte[] byArray, int n) {
            int n2 = byArray[n] << 24;
            n2 |= (byArray[++n] & 0xFF) << 16;
            n2 |= (byArray[++n] & 0xFF) << 8;
            this.X[this.xOff] = n2 |= byArray[++n] & 0xFF;
            if (++this.xOff == 16) {
                this.processBlock();
            }
        }

        protected void processLength(long l) {
            if (this.xOff > 14) {
                this.processBlock();
            }
            this.X[14] = (int)(l >>> 32);
            this.X[15] = (int)(l & 0xFFFFFFFFFFFFFFFFL);
        }

        public int doFinal(byte[] byArray, int n) {
            this.finish();
            Pack.intToBigEndian(this.H1, byArray, n);
            Pack.intToBigEndian(this.H2, byArray, n + 4);
            Pack.intToBigEndian(this.H3, byArray, n + 8);
            Pack.intToBigEndian(this.H4, byArray, n + 12);
            Pack.intToBigEndian(this.H5, byArray, n + 16);
            this.reset();
            return 20;
        }

        public void reset() {
            super.reset();
            this.H1 = 1732584193;
            this.H2 = -271733879;
            this.H3 = -1732584194;
            this.H4 = 271733878;
            this.H5 = -1009589776;
            this.xOff = 0;
            for (int i = 0; i != this.X.length; ++i) {
                this.X[i] = 0;
            }
        }

        private int f(int n, int n2, int n3) {
            return n & n2 | ~n & n3;
        }

        private int h(int n, int n2, int n3) {
            return n ^ n2 ^ n3;
        }

        private int g(int n, int n2, int n3) {
            return n & n2 | n & n3 | n2 & n3;
        }

        protected void processBlock() {
            int n;
            int n2;
            int n3;
            for (n3 = 16; n3 < 80; ++n3) {
                n2 = this.X[n3 - 3] ^ this.X[n3 - 8] ^ this.X[n3 - 14] ^ this.X[n3 - 16];
                this.X[n3] = n2 << 1 | n2 >>> 31;
            }
            n3 = this.H1;
            n2 = this.H2;
            int n4 = this.H3;
            int n5 = this.H4;
            int n6 = this.H5;
            int n7 = 0;
            for (n = 0; n < 4; ++n) {
                n6 += (n3 << 5 | n3 >>> 27) + this.f(n2, n4, n5) + this.X[n7++] + 1518500249;
                n2 = n2 << 30 | n2 >>> 2;
                n5 += (n6 << 5 | n6 >>> 27) + this.f(n3, n2, n4) + this.X[n7++] + 1518500249;
                n3 = n3 << 30 | n3 >>> 2;
                n4 += (n5 << 5 | n5 >>> 27) + this.f(n6, n3, n2) + this.X[n7++] + 1518500249;
                n6 = n6 << 30 | n6 >>> 2;
                n2 += (n4 << 5 | n4 >>> 27) + this.f(n5, n6, n3) + this.X[n7++] + 1518500249;
                n5 = n5 << 30 | n5 >>> 2;
                n3 += (n2 << 5 | n2 >>> 27) + this.f(n4, n5, n6) + this.X[n7++] + 1518500249;
                n4 = n4 << 30 | n4 >>> 2;
            }
            for (n = 0; n < 4; ++n) {
                n6 += (n3 << 5 | n3 >>> 27) + this.h(n2, n4, n5) + this.X[n7++] + 1859775393;
                n2 = n2 << 30 | n2 >>> 2;
                n5 += (n6 << 5 | n6 >>> 27) + this.h(n3, n2, n4) + this.X[n7++] + 1859775393;
                n3 = n3 << 30 | n3 >>> 2;
                n4 += (n5 << 5 | n5 >>> 27) + this.h(n6, n3, n2) + this.X[n7++] + 1859775393;
                n6 = n6 << 30 | n6 >>> 2;
                n2 += (n4 << 5 | n4 >>> 27) + this.h(n5, n6, n3) + this.X[n7++] + 1859775393;
                n5 = n5 << 30 | n5 >>> 2;
                n3 += (n2 << 5 | n2 >>> 27) + this.h(n4, n5, n6) + this.X[n7++] + 1859775393;
                n4 = n4 << 30 | n4 >>> 2;
            }
            for (n = 0; n < 4; ++n) {
                n6 += (n3 << 5 | n3 >>> 27) + this.g(n2, n4, n5) + this.X[n7++] + -1894007588;
                n2 = n2 << 30 | n2 >>> 2;
                n5 += (n6 << 5 | n6 >>> 27) + this.g(n3, n2, n4) + this.X[n7++] + -1894007588;
                n3 = n3 << 30 | n3 >>> 2;
                n4 += (n5 << 5 | n5 >>> 27) + this.g(n6, n3, n2) + this.X[n7++] + -1894007588;
                n6 = n6 << 30 | n6 >>> 2;
                n2 += (n4 << 5 | n4 >>> 27) + this.g(n5, n6, n3) + this.X[n7++] + -1894007588;
                n5 = n5 << 30 | n5 >>> 2;
                n3 += (n2 << 5 | n2 >>> 27) + this.g(n4, n5, n6) + this.X[n7++] + -1894007588;
                n4 = n4 << 30 | n4 >>> 2;
            }
            for (n = 0; n <= 3; ++n) {
                n6 += (n3 << 5 | n3 >>> 27) + this.h(n2, n4, n5) + this.X[n7++] + -899497514;
                n2 = n2 << 30 | n2 >>> 2;
                n5 += (n6 << 5 | n6 >>> 27) + this.h(n3, n2, n4) + this.X[n7++] + -899497514;
                n3 = n3 << 30 | n3 >>> 2;
                n4 += (n5 << 5 | n5 >>> 27) + this.h(n6, n3, n2) + this.X[n7++] + -899497514;
                n6 = n6 << 30 | n6 >>> 2;
                n2 += (n4 << 5 | n4 >>> 27) + this.h(n5, n6, n3) + this.X[n7++] + -899497514;
                n5 = n5 << 30 | n5 >>> 2;
                n3 += (n2 << 5 | n2 >>> 27) + this.h(n4, n5, n6) + this.X[n7++] + -899497514;
                n4 = n4 << 30 | n4 >>> 2;
            }
            this.H1 += n3;
            this.H2 += n2;
            this.H3 += n4;
            this.H4 += n5;
            this.H5 += n6;
            this.xOff = 0;
            for (n = 0; n < 16; ++n) {
                this.X[n] = 0;
            }
        }
    }
}

