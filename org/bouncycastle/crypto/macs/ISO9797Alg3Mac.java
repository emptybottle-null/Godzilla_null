/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class ISO9797Alg3Mac
implements Mac {
    private byte[] mac;
    private byte[] buf;
    private int bufOff;
    private BlockCipher cipher;
    private BlockCipherPadding padding;
    private int macSize;
    private KeyParameter lastKey2;
    private KeyParameter lastKey3;

    public ISO9797Alg3Mac(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8, null);
    }

    public ISO9797Alg3Mac(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
        this(blockCipher, blockCipher.getBlockSize() * 8, blockCipherPadding);
    }

    public ISO9797Alg3Mac(BlockCipher blockCipher, int n) {
        this(blockCipher, n, null);
    }

    public ISO9797Alg3Mac(BlockCipher blockCipher, int n, BlockCipherPadding blockCipherPadding) {
        if (n % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        if (!(blockCipher instanceof DESEngine)) {
            throw new IllegalArgumentException("cipher must be instance of DESEngine");
        }
        this.cipher = new CBCBlockCipher(blockCipher);
        this.padding = blockCipherPadding;
        this.macSize = n / 8;
        this.mac = new byte[blockCipher.getBlockSize()];
        this.buf = new byte[blockCipher.getBlockSize()];
        this.bufOff = 0;
    }

    public String getAlgorithmName() {
        return "ISO9797Alg3";
    }

    public void init(CipherParameters cipherParameters) {
        KeyParameter keyParameter;
        this.reset();
        if (!(cipherParameters instanceof KeyParameter) && !(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("params must be an instance of KeyParameter or ParametersWithIV");
        }
        KeyParameter keyParameter2 = cipherParameters instanceof KeyParameter ? (KeyParameter)cipherParameters : (KeyParameter)((ParametersWithIV)cipherParameters).getParameters();
        byte[] byArray = keyParameter2.getKey();
        if (byArray.length == 16) {
            keyParameter = new KeyParameter(byArray, 0, 8);
            this.lastKey2 = new KeyParameter(byArray, 8, 8);
            this.lastKey3 = keyParameter;
        } else if (byArray.length == 24) {
            keyParameter = new KeyParameter(byArray, 0, 8);
            this.lastKey2 = new KeyParameter(byArray, 8, 8);
            this.lastKey3 = new KeyParameter(byArray, 16, 8);
        } else {
            throw new IllegalArgumentException("Key must be either 112 or 168 bit long");
        }
        if (cipherParameters instanceof ParametersWithIV) {
            this.cipher.init(true, new ParametersWithIV(keyParameter, ((ParametersWithIV)cipherParameters).getIV()));
        } else {
            this.cipher.init(true, keyParameter);
        }
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
            if (this.bufOff == n2) {
                this.cipher.processBlock(this.buf, 0, this.mac, 0);
                this.bufOff = 0;
            }
            this.padding.addPadding(this.buf, this.bufOff);
        }
        this.cipher.processBlock(this.buf, 0, this.mac, 0);
        DESEngine dESEngine = new DESEngine();
        dESEngine.init(false, this.lastKey2);
        dESEngine.processBlock(this.mac, 0, this.mac, 0);
        dESEngine.init(true, this.lastKey3);
        dESEngine.processBlock(this.mac, 0, this.mac, 0);
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

