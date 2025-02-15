/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.encoders.Hex;

public class GMSSRootSig {
    private Digest messDigestOTS;
    private int mdsize;
    private int keysize;
    private byte[] privateKeyOTS;
    private byte[] hash;
    private byte[] sign;
    private int w;
    private GMSSRandom gmssRandom;
    private int messagesize;
    private int k;
    private int r;
    private int test;
    private int counter;
    private int ii;
    private long test8;
    private long big8;
    private int steps;
    private int checksum;
    private int height;
    private byte[] seed;

    public GMSSRootSig(Digest digest, byte[][] byArray, int[] nArray) {
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.counter = nArray[0];
        this.test = nArray[1];
        this.ii = nArray[2];
        this.r = nArray[3];
        this.steps = nArray[4];
        this.keysize = nArray[5];
        this.height = nArray[6];
        this.w = nArray[7];
        this.checksum = nArray[8];
        this.mdsize = this.messDigestOTS.getDigestSize();
        this.k = (1 << this.w) - 1;
        int n = this.mdsize << 3;
        this.messagesize = (int)Math.ceil((double)n / (double)this.w);
        this.privateKeyOTS = byArray[0];
        this.seed = byArray[1];
        this.hash = byArray[2];
        this.sign = byArray[3];
        this.test8 = (long)(byArray[4][0] & 0xFF) | (long)(byArray[4][1] & 0xFF) << 8 | (long)(byArray[4][2] & 0xFF) << 16 | (long)(byArray[4][3] & 0xFF) << 24 | (long)(byArray[4][4] & 0xFF) << 32 | (long)(byArray[4][5] & 0xFF) << 40 | (long)(byArray[4][6] & 0xFF) << 48 | (long)(byArray[4][7] & 0xFF) << 56;
        this.big8 = (long)(byArray[4][8] & 0xFF) | (long)(byArray[4][9] & 0xFF) << 8 | (long)(byArray[4][10] & 0xFF) << 16 | (long)(byArray[4][11] & 0xFF) << 24 | (long)(byArray[4][12] & 0xFF) << 32 | (long)(byArray[4][13] & 0xFF) << 40 | (long)(byArray[4][14] & 0xFF) << 48 | (long)(byArray[4][15] & 0xFF) << 56;
    }

    public GMSSRootSig(Digest digest, int n, int n2) {
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        this.w = n;
        this.height = n2;
        this.k = (1 << n) - 1;
        int n3 = this.mdsize << 3;
        this.messagesize = (int)Math.ceil((double)n3 / (double)n);
    }

    public void initSign(byte[] byArray, byte[] byArray2) {
        this.hash = new byte[this.mdsize];
        this.messDigestOTS.update(byArray2, 0, byArray2.length);
        this.hash = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(this.hash, 0);
        byte[] byArray3 = new byte[this.mdsize];
        System.arraycopy(this.hash, 0, byArray3, 0, this.mdsize);
        int n = 0;
        int n2 = 0;
        int n3 = this.getLog((this.messagesize << this.w) + 1);
        if (8 % this.w == 0) {
            int n4;
            int n5 = 8 / this.w;
            for (n4 = 0; n4 < this.mdsize; ++n4) {
                for (int i = 0; i < n5; ++i) {
                    n2 += byArray3[n4] & this.k;
                    byArray3[n4] = (byte)(byArray3[n4] >>> this.w);
                }
            }
            n = this.checksum = (this.messagesize << this.w) - n2;
            for (n4 = 0; n4 < n3; n4 += this.w) {
                n2 += n & this.k;
                n >>>= this.w;
            }
        } else if (this.w < 8) {
            long l;
            int n6;
            int n7 = 0;
            int n8 = this.mdsize / this.w;
            for (n6 = 0; n6 < n8; ++n6) {
                int n9;
                l = 0L;
                for (n9 = 0; n9 < this.w; ++n9) {
                    l ^= (long)((byArray3[n7] & 0xFF) << (n9 << 3));
                    ++n7;
                }
                for (n9 = 0; n9 < 8; ++n9) {
                    n2 += (int)(l & (long)this.k);
                    l >>>= this.w;
                }
            }
            n8 = this.mdsize % this.w;
            l = 0L;
            for (n6 = 0; n6 < n8; ++n6) {
                l ^= (long)((byArray3[n7] & 0xFF) << (n6 << 3));
                ++n7;
            }
            n8 <<= 3;
            for (n6 = 0; n6 < n8; n6 += this.w) {
                n2 += (int)(l & (long)this.k);
                l >>>= this.w;
            }
            n = this.checksum = (this.messagesize << this.w) - n2;
            for (n6 = 0; n6 < n3; n6 += this.w) {
                n2 += n & this.k;
                n >>>= this.w;
            }
        } else if (this.w < 57) {
            int n10;
            int n11;
            long l;
            int n12;
            int n13;
            int n14 = 0;
            while (n14 <= (this.mdsize << 3) - this.w) {
                n13 = n14 >>> 3;
                n12 = n14 % 8;
                int n15 = (n14 += this.w) + 7 >>> 3;
                l = 0L;
                n11 = 0;
                for (n10 = n13; n10 < n15; ++n10) {
                    l ^= (long)((byArray3[n10] & 0xFF) << (n11 << 3));
                    ++n11;
                }
                n2 = (int)((long)n2 + ((l >>>= n12) & (long)this.k));
            }
            n13 = n14 >>> 3;
            if (n13 < this.mdsize) {
                n12 = n14 % 8;
                l = 0L;
                n11 = 0;
                for (n10 = n13; n10 < this.mdsize; ++n10) {
                    l ^= (long)((byArray3[n10] & 0xFF) << (n11 << 3));
                    ++n11;
                }
                n2 = (int)((long)n2 + ((l >>>= n12) & (long)this.k));
            }
            n = this.checksum = (this.messagesize << this.w) - n2;
            for (n10 = 0; n10 < n3; n10 += this.w) {
                n2 += n & this.k;
                n >>>= this.w;
            }
        }
        this.keysize = this.messagesize + (int)Math.ceil((double)n3 / (double)this.w);
        this.steps = (int)Math.ceil((double)(this.keysize + n2) / (double)(1 << this.height));
        this.sign = new byte[this.keysize * this.mdsize];
        this.counter = 0;
        this.test = 0;
        this.ii = 0;
        this.test8 = 0L;
        this.r = 0;
        this.privateKeyOTS = new byte[this.mdsize];
        this.seed = new byte[this.mdsize];
        System.arraycopy(byArray, 0, this.seed, 0, this.mdsize);
    }

    public boolean updateSign() {
        for (int i = 0; i < this.steps; ++i) {
            if (this.counter < this.keysize) {
                this.oneStep();
            }
            if (this.counter != this.keysize) continue;
            return true;
        }
        return false;
    }

    public byte[] getSig() {
        return this.sign;
    }

    private void oneStep() {
        if (8 % this.w == 0) {
            if (this.test == 0) {
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
                if (this.ii < this.mdsize) {
                    this.test = this.hash[this.ii] & this.k;
                    this.hash[this.ii] = (byte)(this.hash[this.ii] >>> this.w);
                } else {
                    this.test = this.checksum & this.k;
                    this.checksum >>>= this.w;
                }
            } else if (this.test > 0) {
                this.messDigestOTS.update(this.privateKeyOTS, 0, this.privateKeyOTS.length);
                this.privateKeyOTS = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
                --this.test;
            }
            if (this.test == 0) {
                System.arraycopy(this.privateKeyOTS, 0, this.sign, this.counter * this.mdsize, this.mdsize);
                ++this.counter;
                if (this.counter % (8 / this.w) == 0) {
                    ++this.ii;
                }
            }
        } else if (this.w < 8) {
            if (this.test == 0) {
                if (this.counter % 8 == 0 && this.ii < this.mdsize) {
                    this.big8 = 0L;
                    if (this.counter < this.mdsize / this.w << 3) {
                        for (int i = 0; i < this.w; ++i) {
                            this.big8 ^= (long)((this.hash[this.ii] & 0xFF) << (i << 3));
                            ++this.ii;
                        }
                    } else {
                        for (int i = 0; i < this.mdsize % this.w; ++i) {
                            this.big8 ^= (long)((this.hash[this.ii] & 0xFF) << (i << 3));
                            ++this.ii;
                        }
                    }
                }
                if (this.counter == this.messagesize) {
                    this.big8 = this.checksum;
                }
                this.test = (int)(this.big8 & (long)this.k);
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else if (this.test > 0) {
                this.messDigestOTS.update(this.privateKeyOTS, 0, this.privateKeyOTS.length);
                this.privateKeyOTS = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
                --this.test;
            }
            if (this.test == 0) {
                System.arraycopy(this.privateKeyOTS, 0, this.sign, this.counter * this.mdsize, this.mdsize);
                this.big8 >>>= this.w;
                ++this.counter;
            }
        } else if (this.w < 57) {
            if (this.test8 == 0L) {
                this.big8 = 0L;
                this.ii = 0;
                int n = this.r % 8;
                int n2 = this.r >>> 3;
                if (n2 < this.mdsize) {
                    int n3;
                    if (this.r <= (this.mdsize << 3) - this.w) {
                        this.r += this.w;
                        n3 = this.r + 7 >>> 3;
                    } else {
                        n3 = this.mdsize;
                        this.r += this.w;
                    }
                    for (int i = n2; i < n3; ++i) {
                        this.big8 ^= (long)((this.hash[i] & 0xFF) << (this.ii << 3));
                        ++this.ii;
                    }
                    this.big8 >>>= n;
                    this.test8 = this.big8 & (long)this.k;
                } else {
                    this.test8 = this.checksum & this.k;
                    this.checksum >>>= this.w;
                }
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else if (this.test8 > 0L) {
                this.messDigestOTS.update(this.privateKeyOTS, 0, this.privateKeyOTS.length);
                this.privateKeyOTS = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
                --this.test8;
            }
            if (this.test8 == 0L) {
                System.arraycopy(this.privateKeyOTS, 0, this.sign, this.counter * this.mdsize, this.mdsize);
                ++this.counter;
            }
        }
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

    public byte[][] getStatByte() {
        byte[][] byArray = new byte[5][this.mdsize];
        byArray[0] = this.privateKeyOTS;
        byArray[1] = this.seed;
        byArray[2] = this.hash;
        byArray[3] = this.sign;
        byArray[4] = this.getStatLong();
        return byArray;
    }

    public int[] getStatInt() {
        int[] nArray = new int[]{this.counter, this.test, this.ii, this.r, this.steps, this.keysize, this.height, this.w, this.checksum};
        return nArray;
    }

    public byte[] getStatLong() {
        byte[] byArray = new byte[]{(byte)(this.test8 & 0xFFL), (byte)(this.test8 >> 8 & 0xFFL), (byte)(this.test8 >> 16 & 0xFFL), (byte)(this.test8 >> 24 & 0xFFL), (byte)(this.test8 >> 32 & 0xFFL), (byte)(this.test8 >> 40 & 0xFFL), (byte)(this.test8 >> 48 & 0xFFL), (byte)(this.test8 >> 56 & 0xFFL), (byte)(this.big8 & 0xFFL), (byte)(this.big8 >> 8 & 0xFFL), (byte)(this.big8 >> 16 & 0xFFL), (byte)(this.big8 >> 24 & 0xFFL), (byte)(this.big8 >> 32 & 0xFFL), (byte)(this.big8 >> 40 & 0xFFL), (byte)(this.big8 >> 48 & 0xFFL), (byte)(this.big8 >> 56 & 0xFFL)};
        return byArray;
    }

    public String toString() {
        int n;
        String string = "" + this.big8 + "  ";
        int[] nArray = new int[9];
        nArray = this.getStatInt();
        byte[][] byArray = new byte[5][this.mdsize];
        byArray = this.getStatByte();
        for (n = 0; n < 9; ++n) {
            string = string + nArray[n] + " ";
        }
        for (n = 0; n < 5; ++n) {
            string = string + new String(Hex.encode(byArray[n])) + " ";
        }
        return string;
    }
}

