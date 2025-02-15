/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng.drbg;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.drbg.SP80090DRBG;
import org.bouncycastle.crypto.prng.drbg.Utils;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

public class CTRSP800DRBG
implements SP80090DRBG {
    private static final long TDEA_RESEED_MAX = 0x80000000L;
    private static final long AES_RESEED_MAX = 0x800000000000L;
    private static final int TDEA_MAX_BITS_REQUEST = 4096;
    private static final int AES_MAX_BITS_REQUEST = 262144;
    private EntropySource _entropySource;
    private BlockCipher _engine;
    private int _keySizeInBits;
    private int _seedLength;
    private int _securityStrength;
    private byte[] _Key;
    private byte[] _V;
    private long _reseedCounter = 0L;
    private boolean _isTDEA = false;
    private static final byte[] K_BITS = Hex.decode("000102030405060708090A0B0C0D0E0F101112131415161718191A1B1C1D1E1F");

    public CTRSP800DRBG(BlockCipher blockCipher, int n, int n2, EntropySource entropySource, byte[] byArray, byte[] byArray2) {
        this._entropySource = entropySource;
        this._engine = blockCipher;
        this._keySizeInBits = n;
        this._securityStrength = n2;
        this._seedLength = n + blockCipher.getBlockSize() * 8;
        this._isTDEA = this.isTDEA(blockCipher);
        if (n2 > 256) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        }
        if (this.getMaxSecurityStrength(blockCipher, n) < n2) {
            throw new IllegalArgumentException("Requested security strength is not supported by block cipher and key size");
        }
        if (entropySource.entropySize() < n2) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        byte[] byArray3 = this.getEntropy();
        this.CTR_DRBG_Instantiate_algorithm(byArray3, byArray2, byArray);
    }

    private void CTR_DRBG_Instantiate_algorithm(byte[] byArray, byte[] byArray2, byte[] byArray3) {
        byte[] byArray4 = Arrays.concatenate(byArray, byArray2, byArray3);
        byte[] byArray5 = this.Block_Cipher_df(byArray4, this._seedLength);
        int n = this._engine.getBlockSize();
        this._Key = new byte[(this._keySizeInBits + 7) / 8];
        this._V = new byte[n];
        this.CTR_DRBG_Update(byArray5, this._Key, this._V);
        this._reseedCounter = 1L;
    }

    private void CTR_DRBG_Update(byte[] byArray, byte[] byArray2, byte[] byArray3) {
        byte[] byArray4 = new byte[byArray.length];
        byte[] byArray5 = new byte[this._engine.getBlockSize()];
        int n = 0;
        int n2 = this._engine.getBlockSize();
        this._engine.init(true, new KeyParameter(this.expandKey(byArray2)));
        while (n * n2 < byArray.length) {
            this.addOneTo(byArray3);
            this._engine.processBlock(byArray3, 0, byArray5, 0);
            int n3 = byArray4.length - n * n2 > n2 ? n2 : byArray4.length - n * n2;
            System.arraycopy(byArray5, 0, byArray4, n * n2, n3);
            ++n;
        }
        this.XOR(byArray4, byArray, byArray4, 0);
        System.arraycopy(byArray4, 0, byArray2, 0, byArray2.length);
        System.arraycopy(byArray4, byArray2.length, byArray3, 0, byArray3.length);
    }

    private void CTR_DRBG_Reseed_algorithm(byte[] byArray) {
        byte[] byArray2 = Arrays.concatenate(this.getEntropy(), byArray);
        byArray2 = this.Block_Cipher_df(byArray2, this._seedLength);
        this.CTR_DRBG_Update(byArray2, this._Key, this._V);
        this._reseedCounter = 1L;
    }

    private void XOR(byte[] byArray, byte[] byArray2, byte[] byArray3, int n) {
        for (int i = 0; i < byArray.length; ++i) {
            byArray[i] = (byte)(byArray2[i] ^ byArray3[i + n]);
        }
    }

    private void addOneTo(byte[] byArray) {
        int n = 1;
        for (int i = 1; i <= byArray.length; ++i) {
            int n2 = (byArray[byArray.length - i] & 0xFF) + n;
            n = n2 > 255 ? 1 : 0;
            byArray[byArray.length - i] = (byte)n2;
        }
    }

    private byte[] getEntropy() {
        byte[] byArray = this._entropySource.getEntropy();
        if (byArray.length < (this._securityStrength + 7) / 8) {
            throw new IllegalStateException("Insufficient entropy provided by entropy source");
        }
        return byArray;
    }

    private byte[] Block_Cipher_df(byte[] byArray, int n) {
        int n2 = this._engine.getBlockSize();
        int n3 = byArray.length;
        int n4 = n / 8;
        int n5 = 8 + n3 + 1;
        int n6 = (n5 + n2 - 1) / n2 * n2;
        byte[] byArray2 = new byte[n6];
        this.copyIntToByteArray(byArray2, n3, 0);
        this.copyIntToByteArray(byArray2, n4, 4);
        System.arraycopy(byArray, 0, byArray2, 8, n3);
        byArray2[8 + n3] = -128;
        byte[] byArray3 = new byte[this._keySizeInBits / 8 + n2];
        byte[] byArray4 = new byte[n2];
        byte[] byArray5 = new byte[n2];
        int n7 = 0;
        byte[] byArray6 = new byte[this._keySizeInBits / 8];
        System.arraycopy(K_BITS, 0, byArray6, 0, byArray6.length);
        while (n7 * n2 * 8 < this._keySizeInBits + n2 * 8) {
            this.copyIntToByteArray(byArray5, n7, 0);
            this.BCC(byArray4, byArray6, byArray5, byArray2);
            int n8 = byArray3.length - n7 * n2 > n2 ? n2 : byArray3.length - n7 * n2;
            System.arraycopy(byArray4, 0, byArray3, n7 * n2, n8);
            ++n7;
        }
        byte[] byArray7 = new byte[n2];
        System.arraycopy(byArray3, 0, byArray6, 0, byArray6.length);
        System.arraycopy(byArray3, byArray6.length, byArray7, 0, byArray7.length);
        byArray3 = new byte[n / 2];
        n7 = 0;
        this._engine.init(true, new KeyParameter(this.expandKey(byArray6)));
        while (n7 * n2 < byArray3.length) {
            this._engine.processBlock(byArray7, 0, byArray7, 0);
            int n9 = byArray3.length - n7 * n2 > n2 ? n2 : byArray3.length - n7 * n2;
            System.arraycopy(byArray7, 0, byArray3, n7 * n2, n9);
            ++n7;
        }
        return byArray3;
    }

    private void BCC(byte[] byArray, byte[] byArray2, byte[] byArray3, byte[] byArray4) {
        int n = this._engine.getBlockSize();
        byte[] byArray5 = new byte[n];
        int n2 = byArray4.length / n;
        byte[] byArray6 = new byte[n];
        this._engine.init(true, new KeyParameter(this.expandKey(byArray2)));
        this._engine.processBlock(byArray3, 0, byArray5, 0);
        for (int i = 0; i < n2; ++i) {
            this.XOR(byArray6, byArray5, byArray4, i * n);
            this._engine.processBlock(byArray6, 0, byArray5, 0);
        }
        System.arraycopy(byArray5, 0, byArray, 0, byArray.length);
    }

    private void copyIntToByteArray(byte[] byArray, int n, int n2) {
        byArray[n2 + 0] = (byte)(n >> 24);
        byArray[n2 + 1] = (byte)(n >> 16);
        byArray[n2 + 2] = (byte)(n >> 8);
        byArray[n2 + 3] = (byte)n;
    }

    public int getBlockSize() {
        return this._V.length * 8;
    }

    public int generate(byte[] byArray, byte[] byArray2, boolean bl) {
        if (this._isTDEA) {
            if (this._reseedCounter > 0x80000000L) {
                return -1;
            }
            if (Utils.isTooLarge(byArray, 512)) {
                throw new IllegalArgumentException("Number of bits per request limited to 4096");
            }
        } else {
            if (this._reseedCounter > 0x800000000000L) {
                return -1;
            }
            if (Utils.isTooLarge(byArray, 32768)) {
                throw new IllegalArgumentException("Number of bits per request limited to 262144");
            }
        }
        if (bl) {
            this.CTR_DRBG_Reseed_algorithm(byArray2);
            byArray2 = null;
        }
        if (byArray2 != null) {
            byArray2 = this.Block_Cipher_df(byArray2, this._seedLength);
            this.CTR_DRBG_Update(byArray2, this._Key, this._V);
        } else {
            byArray2 = new byte[this._seedLength];
        }
        byte[] byArray3 = new byte[this._V.length];
        this._engine.init(true, new KeyParameter(this.expandKey(this._Key)));
        for (int i = 0; i <= byArray.length / byArray3.length; ++i) {
            int n;
            int n2 = n = byArray.length - i * byArray3.length > byArray3.length ? byArray3.length : byArray.length - i * this._V.length;
            if (n == 0) continue;
            this.addOneTo(this._V);
            this._engine.processBlock(this._V, 0, byArray3, 0);
            System.arraycopy(byArray3, 0, byArray, i * byArray3.length, n);
        }
        this.CTR_DRBG_Update(byArray2, this._Key, this._V);
        ++this._reseedCounter;
        return byArray.length * 8;
    }

    public void reseed(byte[] byArray) {
        this.CTR_DRBG_Reseed_algorithm(byArray);
    }

    private boolean isTDEA(BlockCipher blockCipher) {
        return blockCipher.getAlgorithmName().equals("DESede") || blockCipher.getAlgorithmName().equals("TDEA");
    }

    private int getMaxSecurityStrength(BlockCipher blockCipher, int n) {
        if (this.isTDEA(blockCipher) && n == 168) {
            return 112;
        }
        if (blockCipher.getAlgorithmName().equals("AES")) {
            return n;
        }
        return -1;
    }

    byte[] expandKey(byte[] byArray) {
        if (this._isTDEA) {
            byte[] byArray2 = new byte[24];
            this.padKey(byArray, 0, byArray2, 0);
            this.padKey(byArray, 7, byArray2, 8);
            this.padKey(byArray, 14, byArray2, 16);
            return byArray2;
        }
        return byArray;
    }

    private void padKey(byte[] byArray, int n, byte[] byArray2, int n2) {
        byArray2[n2 + 0] = (byte)(byArray[n + 0] & 0xFE);
        byArray2[n2 + 1] = (byte)(byArray[n + 0] << 7 | (byArray[n + 1] & 0xFC) >>> 1);
        byArray2[n2 + 2] = (byte)(byArray[n + 1] << 6 | (byArray[n + 2] & 0xF8) >>> 2);
        byArray2[n2 + 3] = (byte)(byArray[n + 2] << 5 | (byArray[n + 3] & 0xF0) >>> 3);
        byArray2[n2 + 4] = (byte)(byArray[n + 3] << 4 | (byArray[n + 4] & 0xE0) >>> 4);
        byArray2[n2 + 5] = (byte)(byArray[n + 4] << 3 | (byArray[n + 5] & 0xC0) >>> 5);
        byArray2[n2 + 6] = (byte)(byArray[n + 5] << 2 | (byArray[n + 6] & 0x80) >>> 6);
        byArray2[n2 + 7] = (byte)(byArray[n + 6] << 1);
        for (int i = n2; i <= n2 + 7; ++i) {
            byte by = byArray2[i];
            byArray2[i] = (byte)(by & 0xFE | (by >> 1 ^ by >> 2 ^ by >> 3 ^ by >> 4 ^ by >> 5 ^ by >> 6 ^ by >> 7 ^ 1) & 1);
        }
    }
}

