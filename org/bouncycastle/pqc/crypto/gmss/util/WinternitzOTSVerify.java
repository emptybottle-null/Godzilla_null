/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;

public class WinternitzOTSVerify {
    private Digest messDigestOTS;
    private int w;

    public WinternitzOTSVerify(Digest digest, int n) {
        this.w = n;
        this.messDigestOTS = digest;
    }

    public int getSignatureLength() {
        int n = this.messDigestOTS.getDigestSize();
        int n2 = ((n << 3) + (this.w - 1)) / this.w;
        int n3 = this.getLog((n2 << this.w) + 1);
        return n * (n2 += (n3 + this.w - 1) / this.w);
    }

    public byte[] Verify(byte[] byArray, byte[] byArray2) {
        int n;
        int n2 = this.messDigestOTS.getDigestSize();
        byte[] byArray3 = new byte[n2];
        this.messDigestOTS.update(byArray, 0, byArray.length);
        byArray3 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(byArray3, 0);
        int n3 = ((n2 << 3) + (this.w - 1)) / this.w;
        int n4 = this.getLog((n3 << this.w) + 1);
        int n5 = n3 + (n4 + this.w - 1) / this.w;
        int n6 = n2 * n5;
        if (n6 != byArray2.length) {
            return null;
        }
        byte[] byArray4 = new byte[n6];
        int n7 = 0;
        int n8 = 0;
        if (8 % this.w == 0) {
            int n9;
            int n10;
            n = 8 / this.w;
            int n11 = (1 << this.w) - 1;
            byte[] byArray5 = new byte[n2];
            for (n10 = 0; n10 < byArray3.length; ++n10) {
                for (int i = 0; i < n; ++i) {
                    n7 += n9;
                    System.arraycopy(byArray2, n8 * n2, byArray5, 0, n2);
                    for (n9 = byArray3[n10] & n11; n9 < n11; ++n9) {
                        this.messDigestOTS.update(byArray5, 0, byArray5.length);
                        byArray5 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(byArray5, 0);
                    }
                    System.arraycopy(byArray5, 0, byArray4, n8 * n2, n2);
                    byArray3[n10] = (byte)(byArray3[n10] >>> this.w);
                    ++n8;
                }
            }
            n7 = (n3 << this.w) - n7;
            for (n10 = 0; n10 < n4; n10 += this.w) {
                System.arraycopy(byArray2, n8 * n2, byArray5, 0, n2);
                for (n9 = n7 & n11; n9 < n11; ++n9) {
                    this.messDigestOTS.update(byArray5, 0, byArray5.length);
                    byArray5 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(byArray5, 0);
                }
                System.arraycopy(byArray5, 0, byArray4, n8 * n2, n2);
                n7 >>>= this.w;
                ++n8;
            }
        } else if (this.w < 8) {
            int n12;
            long l;
            int n13;
            n = n2 / this.w;
            int n14 = (1 << this.w) - 1;
            byte[] byArray6 = new byte[n2];
            int n15 = 0;
            for (n13 = 0; n13 < n; ++n13) {
                int n16;
                l = 0L;
                for (n16 = 0; n16 < this.w; ++n16) {
                    l ^= (long)((byArray3[n15] & 0xFF) << (n16 << 3));
                    ++n15;
                }
                for (n16 = 0; n16 < 8; ++n16) {
                    n7 += n12;
                    System.arraycopy(byArray2, n8 * n2, byArray6, 0, n2);
                    for (n12 = (int)(l & (long)n14); n12 < n14; ++n12) {
                        this.messDigestOTS.update(byArray6, 0, byArray6.length);
                        byArray6 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(byArray6, 0);
                    }
                    System.arraycopy(byArray6, 0, byArray4, n8 * n2, n2);
                    l >>>= this.w;
                    ++n8;
                }
            }
            n = n2 % this.w;
            l = 0L;
            for (n13 = 0; n13 < n; ++n13) {
                l ^= (long)((byArray3[n15] & 0xFF) << (n13 << 3));
                ++n15;
            }
            n <<= 3;
            for (n13 = 0; n13 < n; n13 += this.w) {
                n7 += n12;
                System.arraycopy(byArray2, n8 * n2, byArray6, 0, n2);
                for (n12 = (int)(l & (long)n14); n12 < n14; ++n12) {
                    this.messDigestOTS.update(byArray6, 0, byArray6.length);
                    byArray6 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(byArray6, 0);
                }
                System.arraycopy(byArray6, 0, byArray4, n8 * n2, n2);
                l >>>= this.w;
                ++n8;
            }
            n7 = (n3 << this.w) - n7;
            for (n13 = 0; n13 < n4; n13 += this.w) {
                System.arraycopy(byArray2, n8 * n2, byArray6, 0, n2);
                for (n12 = n7 & n14; n12 < n14; ++n12) {
                    this.messDigestOTS.update(byArray6, 0, byArray6.length);
                    byArray6 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(byArray6, 0);
                }
                System.arraycopy(byArray6, 0, byArray4, n8 * n2, n2);
                n7 >>>= this.w;
                ++n8;
            }
        } else if (this.w < 57) {
            long l;
            int n17;
            int n18;
            long l2;
            int n19;
            int n20;
            n = (n2 << 3) - this.w;
            int n21 = (1 << this.w) - 1;
            byte[] byArray7 = new byte[n2];
            int n22 = 0;
            while (n22 <= n) {
                n20 = n22 >>> 3;
                n19 = n22 % 8;
                int n23 = (n22 += this.w) + 7 >>> 3;
                l2 = 0L;
                n18 = 0;
                for (n17 = n20; n17 < n23; ++n17) {
                    l2 ^= (long)((byArray3[n17] & 0xFF) << (n18 << 3));
                    ++n18;
                }
                n7 = (int)((long)n7 + l);
                System.arraycopy(byArray2, n8 * n2, byArray7, 0, n2);
                for (l = (l2 >>>= n19) & (long)n21; l < (long)n21; ++l) {
                    this.messDigestOTS.update(byArray7, 0, byArray7.length);
                    byArray7 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(byArray7, 0);
                }
                System.arraycopy(byArray7, 0, byArray4, n8 * n2, n2);
                ++n8;
            }
            n20 = n22 >>> 3;
            if (n20 < n2) {
                n19 = n22 % 8;
                l2 = 0L;
                n18 = 0;
                for (n17 = n20; n17 < n2; ++n17) {
                    l2 ^= (long)((byArray3[n17] & 0xFF) << (n18 << 3));
                    ++n18;
                }
                n7 = (int)((long)n7 + l);
                System.arraycopy(byArray2, n8 * n2, byArray7, 0, n2);
                for (l = (l2 >>>= n19) & (long)n21; l < (long)n21; ++l) {
                    this.messDigestOTS.update(byArray7, 0, byArray7.length);
                    byArray7 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(byArray7, 0);
                }
                System.arraycopy(byArray7, 0, byArray4, n8 * n2, n2);
                ++n8;
            }
            n7 = (n3 << this.w) - n7;
            for (n17 = 0; n17 < n4; n17 += this.w) {
                System.arraycopy(byArray2, n8 * n2, byArray7, 0, n2);
                for (l = (long)(n7 & n21); l < (long)n21; ++l) {
                    this.messDigestOTS.update(byArray7, 0, byArray7.length);
                    byArray7 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(byArray7, 0);
                }
                System.arraycopy(byArray7, 0, byArray4, n8 * n2, n2);
                n7 >>>= this.w;
                ++n8;
            }
        }
        byte[] byArray8 = new byte[n2];
        this.messDigestOTS.update(byArray4, 0, byArray4.length);
        byArray8 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(byArray8, 0);
        return byArray8;
    }

    public int getLog(int n) {
        int n2 = 1;
        int n3 = 2;
        while (n3 < n) {
            n3 <<= 1;
            ++n2;
        }
        return n2;
    }
}

