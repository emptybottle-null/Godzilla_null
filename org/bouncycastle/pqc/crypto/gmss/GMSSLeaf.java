/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

public class GMSSLeaf {
    private Digest messDigestOTS;
    private int mdsize;
    private int keysize;
    private GMSSRandom gmssRandom;
    private byte[] leaf;
    private byte[] concHashs;
    private int i;
    private int j;
    private int two_power_w;
    private int w;
    private int steps;
    private byte[] seed;
    byte[] privateKeyOTS;

    public GMSSLeaf(Digest digest, byte[][] byArray, int[] nArray) {
        this.i = nArray[0];
        this.j = nArray[1];
        this.steps = nArray[2];
        this.w = nArray[3];
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        int n = this.mdsize << 3;
        int n2 = (int)Math.ceil((double)n / (double)this.w);
        int n3 = this.getLog((n2 << this.w) + 1);
        this.keysize = n2 + (int)Math.ceil((double)n3 / (double)this.w);
        this.two_power_w = 1 << this.w;
        this.privateKeyOTS = byArray[0];
        this.seed = byArray[1];
        this.concHashs = byArray[2];
        this.leaf = byArray[3];
    }

    GMSSLeaf(Digest digest, int n, int n2) {
        this.w = n;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        int n3 = this.mdsize << 3;
        int n4 = (int)Math.ceil((double)n3 / (double)n);
        int n5 = this.getLog((n4 << n) + 1);
        this.keysize = n4 + (int)Math.ceil((double)n5 / (double)n);
        this.two_power_w = 1 << n;
        this.steps = (int)Math.ceil((double)(((1 << n) - 1) * this.keysize + 1 + this.keysize) / (double)n2);
        this.seed = new byte[this.mdsize];
        this.leaf = new byte[this.mdsize];
        this.privateKeyOTS = new byte[this.mdsize];
        this.concHashs = new byte[this.mdsize * this.keysize];
    }

    public GMSSLeaf(Digest digest, int n, int n2, byte[] byArray) {
        this.w = n;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        int n3 = this.mdsize << 3;
        int n4 = (int)Math.ceil((double)n3 / (double)n);
        int n5 = this.getLog((n4 << n) + 1);
        this.keysize = n4 + (int)Math.ceil((double)n5 / (double)n);
        this.two_power_w = 1 << n;
        this.steps = (int)Math.ceil((double)(((1 << n) - 1) * this.keysize + 1 + this.keysize) / (double)n2);
        this.seed = new byte[this.mdsize];
        this.leaf = new byte[this.mdsize];
        this.privateKeyOTS = new byte[this.mdsize];
        this.concHashs = new byte[this.mdsize * this.keysize];
        this.initLeafCalc(byArray);
    }

    private GMSSLeaf(GMSSLeaf gMSSLeaf) {
        this.messDigestOTS = gMSSLeaf.messDigestOTS;
        this.mdsize = gMSSLeaf.mdsize;
        this.keysize = gMSSLeaf.keysize;
        this.gmssRandom = gMSSLeaf.gmssRandom;
        this.leaf = Arrays.clone(gMSSLeaf.leaf);
        this.concHashs = Arrays.clone(gMSSLeaf.concHashs);
        this.i = gMSSLeaf.i;
        this.j = gMSSLeaf.j;
        this.two_power_w = gMSSLeaf.two_power_w;
        this.w = gMSSLeaf.w;
        this.steps = gMSSLeaf.steps;
        this.seed = Arrays.clone(gMSSLeaf.seed);
        this.privateKeyOTS = Arrays.clone(gMSSLeaf.privateKeyOTS);
    }

    void initLeafCalc(byte[] byArray) {
        this.i = 0;
        this.j = 0;
        byte[] byArray2 = new byte[this.mdsize];
        System.arraycopy(byArray, 0, byArray2, 0, this.seed.length);
        this.seed = this.gmssRandom.nextSeed(byArray2);
    }

    GMSSLeaf nextLeaf() {
        GMSSLeaf gMSSLeaf = new GMSSLeaf(this);
        gMSSLeaf.updateLeafCalc();
        return gMSSLeaf;
    }

    private void updateLeafCalc() {
        byte[] byArray = new byte[this.messDigestOTS.getDigestSize()];
        for (int i = 0; i < this.steps + 10000; ++i) {
            if (this.i == this.keysize && this.j == this.two_power_w - 1) {
                this.messDigestOTS.update(this.concHashs, 0, this.concHashs.length);
                this.leaf = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(this.leaf, 0);
                return;
            }
            if (this.i == 0 || this.j == this.two_power_w - 1) {
                ++this.i;
                this.j = 0;
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
                continue;
            }
            this.messDigestOTS.update(this.privateKeyOTS, 0, this.privateKeyOTS.length);
            this.privateKeyOTS = byArray;
            this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
            ++this.j;
            if (this.j != this.two_power_w - 1) continue;
            System.arraycopy(this.privateKeyOTS, 0, this.concHashs, this.mdsize * (this.i - 1), this.mdsize);
        }
        throw new IllegalStateException("unable to updateLeaf in steps: " + this.steps + " " + this.i + " " + this.j);
    }

    public byte[] getLeaf() {
        return Arrays.clone(this.leaf);
    }

    private int getLog(int n) {
        int n2 = 1;
        int n3 = 2;
        while (n3 < n) {
            n3 <<= 1;
            ++n2;
        }
        return n2;
    }

    public byte[][] getStatByte() {
        byte[][] byArrayArray = new byte[][]{new byte[this.mdsize], new byte[this.mdsize], new byte[this.mdsize * this.keysize], new byte[this.mdsize]};
        byArrayArray[0] = this.privateKeyOTS;
        byArrayArray[1] = this.seed;
        byArrayArray[2] = this.concHashs;
        byArrayArray[3] = this.leaf;
        return byArrayArray;
    }

    public int[] getStatInt() {
        int[] nArray = new int[]{this.i, this.j, this.steps, this.w};
        return nArray;
    }

    public String toString() {
        String string = "";
        for (int i = 0; i < 4; ++i) {
            string = string + this.getStatInt()[i] + " ";
        }
        string = string + " " + this.mdsize + " " + this.keysize + " " + this.two_power_w + " ";
        byte[][] byArray = this.getStatByte();
        for (int i = 0; i < 4; ++i) {
            string = byArray[i] != null ? string + new String(Hex.encode(byArray[i])) + " " : string + "null ";
        }
        return string;
    }
}

