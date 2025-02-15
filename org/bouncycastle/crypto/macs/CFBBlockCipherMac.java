/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.macs.MacCFBBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;

public class CFBBlockCipherMac
implements Mac {
    private byte[] mac;
    private byte[] buf;
    private int bufOff;
    private MacCFBBlockCipher cipher;
    private BlockCipherPadding padding = null;
    private int macSize;

    public CFBBlockCipherMac(BlockCipher blockCipher) {
        this(blockCipher, 8, blockCipher.getBlockSize() * 8 / 2, null);
    }

    public CFBBlockCipherMac(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
        this(blockCipher, 8, blockCipher.getBlockSize() * 8 / 2, blockCipherPadding);
    }

    public CFBBlockCipherMac(BlockCipher blockCipher, int n, int n2) {
        this(blockCipher, n, n2, null);
    }

    public CFBBlockCipherMac(BlockCipher blockCipher, int n, int n2, BlockCipherPadding blockCipherPadding) {
        if (n2 % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        this.mac = new byte[blockCipher.getBlockSize()];
        this.cipher = new MacCFBBlockCipher(blockCipher, n);
        this.padding = blockCipherPadding;
        this.macSize = n2 / 8;
        this.buf = new byte[this.cipher.getBlockSize()];
        this.bufOff = 0;
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName();
    }

    public void init(CipherParameters cipherParameters) {
        this.reset();
        this.cipher.init(cipherParameters);
    }

    public int getMacSize() {
        return this.macSize;
    }

    public void update(byte by) {
        if (this.bufOff == this.buf.length) {
            this.cipher.processBlock(this.buf, 0, this.mac, 0);
            this.bufOff = 0;
        }
        this.buf[this.bufOff++] = by;
    }

    public void update(byte[] byArray, int n, int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int n3 = this.cipher.getBlockSize();
        int n4 = 0;
        int n5 = n3 - this.bufOff;
        if (n2 > n5) {
            System.arraycopy(byArray, n, this.buf, this.bufOff, n5);
            n4 += this.cipher.processBlock(this.buf, 0, this.mac, 0);
            this.bufOff = 0;
            n2 -= n5;
            n += n5;
            while (n2 > n3) {
                n4 += this.cipher.processBlock(byArray, n, this.mac, 0);
                n2 -= n3;
                n += n3;
            }
        }
        System.arraycopy(byArray, n, this.buf, this.bufOff, n2);
        this.bufOff += n2;
    }

    public int doFinal(byte[] byArray, int n) {
        int n2 = this.cipher.getBlockSize();
        if (this.padding == null) {
            while (this.bufOff < n2) {
                this.buf[this.bufOff] = 0;
                ++this.bufOff;
            }
        } else {
            this.padding.addPadding(this.buf, this.bufOff);
        }
        this.cipher.processBlock(this.buf, 0, this.mac, 0);
        this.cipher.getMacBlock(this.mac);
        System.arraycopy(this.mac, 0, byArray, n, this.macSize);
        this.reset();
        return this.macSize;
    }

    public void reset() {
        for (int i = 0; i < this.buf.length; ++i) {
            this.buf[i] = 0;
        }
        this.bufOff = 0;
        this.cipher.reset();
    }
}

