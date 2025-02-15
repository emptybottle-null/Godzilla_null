/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;

public class CBCBlockCipherMac
implements Mac {
    private byte[] mac;
    private byte[] buf;
    private int bufOff;
    private BlockCipher cipher;
    private BlockCipherPadding padding;
    private int macSize;

    public CBCBlockCipherMac(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8 / 2, null);
    }

    public CBCBlockCipherMac(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
        this(blockCipher, blockCipher.getBlockSize() * 8 / 2, blockCipherPadding);
    }

    public CBCBlockCipherMac(BlockCipher blockCipher, int n) {
        this(blockCipher, n, null);
    }

    public CBCBlockCipherMac(BlockCipher blockCipher, int n, BlockCipherPadding blockCipherPadding) {
        if (n % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        this.cipher = new CBCBlockCipher(blockCipher);
        this.padding = blockCipherPadding;
        this.macSize = n / 8;
        this.mac = new byte[blockCipher.getBlockSize()];
        this.buf = new byte[blockCipher.getBlockSize()];
        this.bufOff = 0;
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName();
    }

    public void init(CipherParameters cipherParameters) {
        this.reset();
        this.cipher.init(true, cipherParameters);
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
        int n4 = n3 - this.bufOff;
        if (n2 > n4) {
            System.arraycopy(byArray, n, this.buf, this.bufOff, n4);
            this.cipher.processBlock(this.buf, 0, this.mac, 0);
            this.bufOff = 0;
            n2 -= n4;
            n += n4;
            while (n2 > n3) {
                this.cipher.processBlock(byArray, n, this.mac, 0);
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
            if (this.bufOff == n2) {
                this.cipher.processBlock(this.buf, 0, this.mac, 0);
                this.bufOff = 0;
            }
            this.padding.addPadding(this.buf, this.bufOff);
        }
        this.cipher.processBlock(this.buf, 0, this.mac, 0);
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

