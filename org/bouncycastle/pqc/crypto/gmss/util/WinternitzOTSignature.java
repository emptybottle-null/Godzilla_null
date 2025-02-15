/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;

public class WinternitzOTSignature {
    private Digest messDigestOTS;
    private int mdsize;
    private int keysize;
    private byte[][] privateKeyOTS;
    private int w;
    private GMSSRandom gmssRandom;
    private int messagesize;
    private int checksumsize;

    public WinternitzOTSignature(byte[] byArray, Digest digest, int n) {
        this.w = n;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        int n2 = this.mdsize << 3;
        this.messagesize = (int)Math.ceil((double)n2 / (double)n);
        this.checksumsize = this.getLog((this.messagesize << n) + 1);
        this.keysize = this.messagesize + (int)Math.ceil((double)this.checksumsize / (double)n);
        this.privateKeyOTS = new byte[this.keysize][this.mdsize];
        byte[] byArray2 = new byte[this.mdsize];
        System.arraycopy(byArray, 0, byArray2, 0, byArray2.length);
        for (int i = 0; i < this.keysize; ++i) {
            this.privateKeyOTS[i] = this.gmssRandom.nextSeed(byArray2);
        }
    }

    public byte[][] getPrivateKey() {
        return this.privateKeyOTS;
    }

    public byte[] getPublicKey() {
        byte[] byArray = new byte[this.keysize * this.mdsize];
        byte[] byArray2 = new byte[this.mdsize];
        int n = 1 << this.w;
        for (int i = 0; i < this.keysize; ++i) {
            this.messDigestOTS.update(this.privateKeyOTS[i], 0, this.privateKeyOTS[i].length);
            byArray2 = new byte[this.messDigestOTS.getDigestSize()];
            this.messDigestOTS.doFinal(byArray2, 0);
            for (int j = 2; j < n; ++j) {
                this.messDigestOTS.update(byArray2, 0, byArray2.length);
                byArray2 = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(byArray2, 0);
            }
            System.arraycopy(byArray2, 0, byArray, this.mdsize * i, this.mdsize);
        }
        this.messDigestOTS.update(byArray, 0, byArray.length);
        byte[] byArray3 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(byArray3, 0);
        return byArray3;
    }

    public byte[] getSignature(byte[] byArray) {
        byte[] byArray2;
        block23: {
            long l;
            int n;
            int n2;
            long l2;
            int n3;
            int n4;
            int n5;
            int n6;
            byte[] byArray3;
            block24: {
                long l3;
                int n7;
                int n8;
                block22: {
                    int n9;
                    byArray2 = new byte[this.keysize * this.mdsize];
                    byArray3 = new byte[this.mdsize];
                    n6 = 0;
                    n5 = 0;
                    n8 = 0;
                    this.messDigestOTS.update(byArray, 0, byArray.length);
                    byArray3 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(byArray3, 0);
                    if (8 % this.w != 0) break block22;
                    int n10 = 8 / this.w;
                    int n11 = (1 << this.w) - 1;
                    byte[] byArray4 = new byte[this.mdsize];
                    for (n9 = 0; n9 < byArray3.length; ++n9) {
                        for (int i = 0; i < n10; ++i) {
                            n5 += n8;
                            System.arraycopy(this.privateKeyOTS[n6], 0, byArray4, 0, this.mdsize);
                            for (n8 = byArray3[n9] & n11; n8 > 0; --n8) {
                                this.messDigestOTS.update(byArray4, 0, byArray4.length);
                                byArray4 = new byte[this.messDigestOTS.getDigestSize()];
                                this.messDigestOTS.doFinal(byArray4, 0);
                            }
                            System.arraycopy(byArray4, 0, byArray2, n6 * this.mdsize, this.mdsize);
                            byArray3[n9] = (byte)(byArray3[n9] >>> this.w);
                            ++n6;
                        }
                    }
                    n5 = (this.messagesize << this.w) - n5;
                    for (n9 = 0; n9 < this.checksumsize; n9 += this.w) {
                        System.arraycopy(this.privateKeyOTS[n6], 0, byArray4, 0, this.mdsize);
                        for (n8 = n5 & n11; n8 > 0; --n8) {
                            this.messDigestOTS.update(byArray4, 0, byArray4.length);
                            byArray4 = new byte[this.messDigestOTS.getDigestSize()];
                            this.messDigestOTS.doFinal(byArray4, 0);
                        }
                        System.arraycopy(byArray4, 0, byArray2, n6 * this.mdsize, this.mdsize);
                        n5 >>>= this.w;
                        ++n6;
                    }
                    break block23;
                }
                if (this.w >= 8) break block24;
                int n12 = this.mdsize / this.w;
                int n13 = (1 << this.w) - 1;
                byte[] byArray5 = new byte[this.mdsize];
                int n14 = 0;
                for (n7 = 0; n7 < n12; ++n7) {
                    int n15;
                    l3 = 0L;
                    for (n15 = 0; n15 < this.w; ++n15) {
                        l3 ^= (long)((byArray3[n14] & 0xFF) << (n15 << 3));
                        ++n14;
                    }
                    for (n15 = 0; n15 < 8; ++n15) {
                        n5 += n8;
                        System.arraycopy(this.privateKeyOTS[n6], 0, byArray5, 0, this.mdsize);
                        for (n8 = (int)(l3 & (long)n13); n8 > 0; --n8) {
                            this.messDigestOTS.update(byArray5, 0, byArray5.length);
                            byArray5 = new byte[this.messDigestOTS.getDigestSize()];
                            this.messDigestOTS.doFinal(byArray5, 0);
                        }
                        System.arraycopy(byArray5, 0, byArray2, n6 * this.mdsize, this.mdsize);
                        l3 >>>= this.w;
                        ++n6;
                    }
                }
                n12 = this.mdsize % this.w;
                l3 = 0L;
                for (n7 = 0; n7 < n12; ++n7) {
                    l3 ^= (long)((byArray3[n14] & 0xFF) << (n7 << 3));
                    ++n14;
                }
                n12 <<= 3;
                for (n7 = 0; n7 < n12; n7 += this.w) {
                    n5 += n8;
                    System.arraycopy(this.privateKeyOTS[n6], 0, byArray5, 0, this.mdsize);
                    for (n8 = (int)(l3 & (long)n13); n8 > 0; --n8) {
                        this.messDigestOTS.update(byArray5, 0, byArray5.length);
                        byArray5 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(byArray5, 0);
                    }
                    System.arraycopy(byArray5, 0, byArray2, n6 * this.mdsize, this.mdsize);
                    l3 >>>= this.w;
                    ++n6;
                }
                n5 = (this.messagesize << this.w) - n5;
                for (n7 = 0; n7 < this.checksumsize; n7 += this.w) {
                    System.arraycopy(this.privateKeyOTS[n6], 0, byArray5, 0, this.mdsize);
                    for (n8 = n5 & n13; n8 > 0; --n8) {
                        this.messDigestOTS.update(byArray5, 0, byArray5.length);
                        byArray5 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(byArray5, 0);
                    }
                    System.arraycopy(byArray5, 0, byArray2, n6 * this.mdsize, this.mdsize);
                    n5 >>>= this.w;
                    ++n6;
                }
                break block23;
            }
            if (this.w >= 57) break block23;
            int n16 = (this.mdsize << 3) - this.w;
            int n17 = (1 << this.w) - 1;
            byte[] byArray6 = new byte[this.mdsize];
            int n18 = 0;
            while (n18 <= n16) {
                n4 = n18 >>> 3;
                n3 = n18 % 8;
                int n19 = (n18 += this.w) + 7 >>> 3;
                l2 = 0L;
                n2 = 0;
                for (n = n4; n < n19; ++n) {
                    l2 ^= (long)((byArray3[n] & 0xFF) << (n2 << 3));
                    ++n2;
                }
                n5 = (int)((long)n5 + l);
                System.arraycopy(this.privateKeyOTS[n6], 0, byArray6, 0, this.mdsize);
                for (l = (l2 >>>= n3) & (long)n17; l > 0L; --l) {
                    this.messDigestOTS.update(byArray6, 0, byArray6.length);
                    byArray6 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(byArray6, 0);
                }
                System.arraycopy(byArray6, 0, byArray2, n6 * this.mdsize, this.mdsize);
                ++n6;
            }
            n4 = n18 >>> 3;
            if (n4 < this.mdsize) {
                n3 = n18 % 8;
                l2 = 0L;
                n2 = 0;
                for (n = n4; n < this.mdsize; ++n) {
                    l2 ^= (long)((byArray3[n] & 0xFF) << (n2 << 3));
                    ++n2;
                }
                n5 = (int)((long)n5 + l);
                System.arraycopy(this.privateKeyOTS[n6], 0, byArray6, 0, this.mdsize);
                for (l = (l2 >>>= n3) & (long)n17; l > 0L; --l) {
                    this.messDigestOTS.update(byArray6, 0, byArray6.length);
                    byArray6 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(byArray6, 0);
                }
                System.arraycopy(byArray6, 0, byArray2, n6 * this.mdsize, this.mdsize);
                ++n6;
            }
            n5 = (this.messagesize << this.w) - n5;
            for (n = 0; n < this.checksumsize; n += this.w) {
                System.arraycopy(this.privateKeyOTS[n6], 0, byArray6, 0, this.mdsize);
                for (l = (long)(n5 & n17); l > 0L; --l) {
                    this.messDigestOTS.update(byArray6, 0, byArray6.length);
                    byArray6 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(byArray6, 0);
                }
                System.arraycopy(byArray6, 0, byArray2, n6 * this.mdsize, this.mdsize);
                n5 >>>= this.w;
                ++n6;
            }
        }
        return byArray2;
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

