/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.ISO7816d4Padding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

public class CMac
implements Mac {
    private byte[] poly;
    private byte[] ZEROES;
    private byte[] mac;
    private byte[] buf;
    private int bufOff;
    private BlockCipher cipher;
    private int macSize;
    private byte[] Lu;
    private byte[] Lu2;

    public CMac(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8);
    }

    public CMac(BlockCipher blockCipher, int n) {
        if (n % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        if (n > blockCipher.getBlockSize() * 8) {
            throw new IllegalArgumentException("MAC size must be less or equal to " + blockCipher.getBlockSize() * 8);
        }
        this.cipher = new CBCBlockCipher(blockCipher);
        this.macSize = n / 8;
        this.poly = CMac.lookupPoly(blockCipher.getBlockSize());
        this.mac = new byte[blockCipher.getBlockSize()];
        this.buf = new byte[blockCipher.getBlockSize()];
        this.ZEROES = new byte[blockCipher.getBlockSize()];
        this.bufOff = 0;
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName();
    }

    private static int shiftLeft(byte[] byArray, byte[] byArray2) {
        int n = byArray.length;
        int n2 = 0;
        while (--n >= 0) {
            int n3 = byArray[n] & 0xFF;
            byArray2[n] = (byte)(n3 << 1 | n2);
            n2 = n3 >>> 7 & 1;
        }
        return n2;
    }

    private byte[] doubleLu(byte[] byArray) {
        byte[] byArray2 = new byte[byArray.length];
        int n = CMac.shiftLeft(byArray, byArray2);
        int n2 = -n & 0xFF;
        int n3 = byArray.length - 3;
        byArray2[n3] = (byte)(byArray2[n3] ^ this.poly[1] & n2);
        int n4 = byArray.length - 2;
        byArray2[n4] = (byte)(byArray2[n4] ^ this.poly[2] & n2);
        int n5 = byArray.length - 1;
        byArray2[n5] = (byte)(byArray2[n5] ^ this.poly[3] & n2);
        return byArray2;
    }

    private static byte[] lookupPoly(int n) {
        int n2;
        switch (n * 8) {
            case 64: {
                n2 = 27;
                break;
            }
            case 128: {
                n2 = 135;
                break;
            }
            case 160: {
                n2 = 45;
                break;
            }
            case 192: {
                n2 = 135;
                break;
            }
            case 224: {
                n2 = 777;
                break;
            }
            case 256: {
                n2 = 1061;
                break;
            }
            case 320: {
                n2 = 27;
                break;
            }
            case 384: {
                n2 = 4109;
                break;
            }
            case 448: {
                n2 = 2129;
                break;
            }
            case 512: {
                n2 = 293;
                break;
            }
            case 768: {
                n2 = 655377;
                break;
            }
            case 1024: {
                n2 = 524355;
                break;
            }
            case 2048: {
                n2 = 548865;
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown block size for CMAC: " + n * 8);
            }
        }
        return Pack.intToBigEndian(n2);
    }

    public void init(CipherParameters cipherParameters) {
        this.validate(cipherParameters);
        this.cipher.init(true, cipherParameters);
        byte[] byArray = new byte[this.ZEROES.length];
        this.cipher.processBlock(this.ZEROES, 0, byArray, 0);
        this.Lu = this.doubleLu(byArray);
        this.Lu2 = this.doubleLu(this.Lu);
        this.reset();
    }

    void validate(CipherParameters cipherParameters) {
        if (cipherParameters != null && !(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("CMac mode only permits key to be set.");
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
        byte[] byArray2;
        int n2 = this.cipher.getBlockSize();
        if (this.bufOff == n2) {
            byArray2 = this.Lu;
        } else {
            new ISO7816d4Padding().addPadding(this.buf, this.bufOff);
            byArray2 = this.Lu2;
        }
        for (int i = 0; i < this.mac.length; ++i) {
            int n3 = i;
            this.buf[n3] = (byte)(this.buf[n3] ^ byArray2[i]);
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

