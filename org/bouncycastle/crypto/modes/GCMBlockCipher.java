/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.gcm.GCMExponentiator;
import org.bouncycastle.crypto.modes.gcm.GCMMultiplier;
import org.bouncycastle.crypto.modes.gcm.GCMUtil;
import org.bouncycastle.crypto.modes.gcm.Tables1kGCMExponentiator;
import org.bouncycastle.crypto.modes.gcm.Tables8kGCMMultiplier;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class GCMBlockCipher
implements AEADBlockCipher {
    private static final int BLOCK_SIZE = 16;
    private BlockCipher cipher;
    private GCMMultiplier multiplier;
    private GCMExponentiator exp;
    private boolean forEncryption;
    private boolean initialised;
    private int macSize;
    private byte[] lastKey;
    private byte[] nonce;
    private byte[] initialAssociatedText;
    private byte[] H;
    private byte[] J0;
    private byte[] bufBlock;
    private byte[] macBlock;
    private byte[] S;
    private byte[] S_at;
    private byte[] S_atPre;
    private byte[] counter;
    private int blocksRemaining;
    private int bufOff;
    private long totalLength;
    private byte[] atBlock;
    private int atBlockPos;
    private long atLength;
    private long atLengthPre;

    public GCMBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, null);
    }

    public GCMBlockCipher(BlockCipher blockCipher, GCMMultiplier gCMMultiplier) {
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
        if (gCMMultiplier == null) {
            gCMMultiplier = new Tables8kGCMMultiplier();
        }
        this.cipher = blockCipher;
        this.multiplier = gCMMultiplier;
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/GCM";
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        KeyParameter keyParameter;
        CipherParameters cipherParameters2;
        this.forEncryption = bl;
        this.macBlock = null;
        this.initialised = true;
        byte[] byArray = null;
        if (cipherParameters instanceof AEADParameters) {
            cipherParameters2 = (AEADParameters)cipherParameters;
            byArray = ((AEADParameters)cipherParameters2).getNonce();
            this.initialAssociatedText = ((AEADParameters)cipherParameters2).getAssociatedText();
            int n = ((AEADParameters)cipherParameters2).getMacSize();
            if (n < 32 || n > 128 || n % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + n);
            }
            this.macSize = n / 8;
            keyParameter = ((AEADParameters)cipherParameters2).getKey();
        } else if (cipherParameters instanceof ParametersWithIV) {
            cipherParameters2 = (ParametersWithIV)cipherParameters;
            byArray = ((ParametersWithIV)cipherParameters2).getIV();
            this.initialAssociatedText = null;
            this.macSize = 16;
            keyParameter = (KeyParameter)((ParametersWithIV)cipherParameters2).getParameters();
        } else {
            throw new IllegalArgumentException("invalid parameters passed to GCM");
        }
        int n = bl ? 16 : 16 + this.macSize;
        this.bufBlock = new byte[n];
        if (byArray == null || byArray.length < 1) {
            throw new IllegalArgumentException("IV must be at least 1 byte");
        }
        if (bl && this.nonce != null && Arrays.areEqual(this.nonce, byArray)) {
            if (keyParameter == null) {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
            if (this.lastKey != null && Arrays.areEqual(this.lastKey, keyParameter.getKey())) {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
        }
        this.nonce = byArray;
        if (keyParameter != null) {
            this.lastKey = keyParameter.getKey();
        }
        if (keyParameter != null) {
            this.cipher.init(true, keyParameter);
            this.H = new byte[16];
            this.cipher.processBlock(this.H, 0, this.H, 0);
            this.multiplier.init(this.H);
            this.exp = null;
        } else if (this.H == null) {
            throw new IllegalArgumentException("Key must be specified in initial init");
        }
        this.J0 = new byte[16];
        if (this.nonce.length == 12) {
            System.arraycopy(this.nonce, 0, this.J0, 0, this.nonce.length);
            this.J0[15] = 1;
        } else {
            this.gHASH(this.J0, this.nonce, this.nonce.length);
            byte[] byArray2 = new byte[16];
            Pack.longToBigEndian((long)this.nonce.length * 8L, byArray2, 8);
            this.gHASHBlock(this.J0, byArray2);
        }
        this.S = new byte[16];
        this.S_at = new byte[16];
        this.S_atPre = new byte[16];
        this.atBlock = new byte[16];
        this.atBlockPos = 0;
        this.atLength = 0L;
        this.atLengthPre = 0L;
        this.counter = Arrays.clone(this.J0);
        this.blocksRemaining = -2;
        this.bufOff = 0;
        this.totalLength = 0L;
        if (this.initialAssociatedText != null) {
            this.processAADBytes(this.initialAssociatedText, 0, this.initialAssociatedText.length);
        }
    }

    public byte[] getMac() {
        if (this.macBlock == null) {
            return new byte[this.macSize];
        }
        return Arrays.clone(this.macBlock);
    }

    public int getOutputSize(int n) {
        int n2 = n + this.bufOff;
        if (this.forEncryption) {
            return n2 + this.macSize;
        }
        return n2 < this.macSize ? 0 : n2 - this.macSize;
    }

    public int getUpdateOutputSize(int n) {
        int n2 = n + this.bufOff;
        if (!this.forEncryption) {
            if (n2 < this.macSize) {
                return 0;
            }
            n2 -= this.macSize;
        }
        return n2 - n2 % 16;
    }

    public void processAADByte(byte by) {
        this.checkStatus();
        this.atBlock[this.atBlockPos] = by;
        if (++this.atBlockPos == 16) {
            this.gHASHBlock(this.S_at, this.atBlock);
            this.atBlockPos = 0;
            this.atLength += 16L;
        }
    }

    public void processAADBytes(byte[] byArray, int n, int n2) {
        this.checkStatus();
        for (int i = 0; i < n2; ++i) {
            this.atBlock[this.atBlockPos] = byArray[n + i];
            if (++this.atBlockPos != 16) continue;
            this.gHASHBlock(this.S_at, this.atBlock);
            this.atBlockPos = 0;
            this.atLength += 16L;
        }
    }

    private void initCipher() {
        if (this.atLength > 0L) {
            System.arraycopy(this.S_at, 0, this.S_atPre, 0, 16);
            this.atLengthPre = this.atLength;
        }
        if (this.atBlockPos > 0) {
            this.gHASHPartial(this.S_atPre, this.atBlock, 0, this.atBlockPos);
            this.atLengthPre += (long)this.atBlockPos;
        }
        if (this.atLengthPre > 0L) {
            System.arraycopy(this.S_atPre, 0, this.S, 0, 16);
        }
    }

    public int processByte(byte by, byte[] byArray, int n) throws DataLengthException {
        this.checkStatus();
        this.bufBlock[this.bufOff] = by;
        if (++this.bufOff == this.bufBlock.length) {
            this.outputBlock(byArray, n);
            return 16;
        }
        return 0;
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws DataLengthException {
        this.checkStatus();
        if (byArray.length < n + n2) {
            throw new DataLengthException("Input buffer too short");
        }
        int n4 = 0;
        for (int i = 0; i < n2; ++i) {
            this.bufBlock[this.bufOff] = byArray[n + i];
            if (++this.bufOff != this.bufBlock.length) continue;
            this.outputBlock(byArray2, n3 + n4);
            n4 += 16;
        }
        return n4;
    }

    private void outputBlock(byte[] byArray, int n) {
        if (byArray.length < n + 16) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.totalLength == 0L) {
            this.initCipher();
        }
        this.gCTRBlock(this.bufBlock, byArray, n);
        if (this.forEncryption) {
            this.bufOff = 0;
        } else {
            System.arraycopy(this.bufBlock, 16, this.bufBlock, 0, this.macSize);
            this.bufOff = this.macSize;
        }
    }

    public int doFinal(byte[] byArray, int n) throws IllegalStateException, InvalidCipherTextException {
        this.checkStatus();
        if (this.totalLength == 0L) {
            this.initCipher();
        }
        int n2 = this.bufOff;
        if (this.forEncryption) {
            if (byArray.length < n + n2 + this.macSize) {
                throw new OutputLengthException("Output buffer too short");
            }
        } else {
            if (n2 < this.macSize) {
                throw new InvalidCipherTextException("data too short");
            }
            if (byArray.length < n + (n2 -= this.macSize)) {
                throw new OutputLengthException("Output buffer too short");
            }
        }
        if (n2 > 0) {
            this.gCTRPartial(this.bufBlock, 0, n2, byArray, n);
        }
        this.atLength += (long)this.atBlockPos;
        if (this.atLength > this.atLengthPre) {
            if (this.atBlockPos > 0) {
                this.gHASHPartial(this.S_at, this.atBlock, 0, this.atBlockPos);
            }
            if (this.atLengthPre > 0L) {
                GCMUtil.xor(this.S_at, this.S_atPre);
            }
            long l = this.totalLength * 8L + 127L >>> 7;
            byte[] byArray2 = new byte[16];
            if (this.exp == null) {
                this.exp = new Tables1kGCMExponentiator();
                this.exp.init(this.H);
            }
            this.exp.exponentiateX(l, byArray2);
            GCMUtil.multiply(this.S_at, byArray2);
            GCMUtil.xor(this.S, this.S_at);
        }
        byte[] byArray3 = new byte[16];
        Pack.longToBigEndian(this.atLength * 8L, byArray3, 0);
        Pack.longToBigEndian(this.totalLength * 8L, byArray3, 8);
        this.gHASHBlock(this.S, byArray3);
        byte[] byArray4 = new byte[16];
        this.cipher.processBlock(this.J0, 0, byArray4, 0);
        GCMUtil.xor(byArray4, this.S);
        int n3 = n2;
        this.macBlock = new byte[this.macSize];
        System.arraycopy(byArray4, 0, this.macBlock, 0, this.macSize);
        if (this.forEncryption) {
            System.arraycopy(this.macBlock, 0, byArray, n + this.bufOff, this.macSize);
            n3 += this.macSize;
        } else {
            byte[] byArray5 = new byte[this.macSize];
            System.arraycopy(this.bufBlock, n2, byArray5, 0, this.macSize);
            if (!Arrays.constantTimeAreEqual(this.macBlock, byArray5)) {
                throw new InvalidCipherTextException("mac check in GCM failed");
            }
        }
        this.reset(false);
        return n3;
    }

    public void reset() {
        this.reset(true);
    }

    private void reset(boolean bl) {
        this.cipher.reset();
        this.S = new byte[16];
        this.S_at = new byte[16];
        this.S_atPre = new byte[16];
        this.atBlock = new byte[16];
        this.atBlockPos = 0;
        this.atLength = 0L;
        this.atLengthPre = 0L;
        this.counter = Arrays.clone(this.J0);
        this.blocksRemaining = -2;
        this.bufOff = 0;
        this.totalLength = 0L;
        if (this.bufBlock != null) {
            Arrays.fill(this.bufBlock, (byte)0);
        }
        if (bl) {
            this.macBlock = null;
        }
        if (this.forEncryption) {
            this.initialised = false;
        } else if (this.initialAssociatedText != null) {
            this.processAADBytes(this.initialAssociatedText, 0, this.initialAssociatedText.length);
        }
    }

    private void gCTRBlock(byte[] byArray, byte[] byArray2, int n) {
        byte[] byArray3 = this.getNextCounterBlock();
        GCMUtil.xor(byArray3, byArray);
        System.arraycopy(byArray3, 0, byArray2, n, 16);
        this.gHASHBlock(this.S, this.forEncryption ? byArray3 : byArray);
        this.totalLength += 16L;
    }

    private void gCTRPartial(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        byte[] byArray3 = this.getNextCounterBlock();
        GCMUtil.xor(byArray3, byArray, n, n2);
        System.arraycopy(byArray3, 0, byArray2, n3, n2);
        this.gHASHPartial(this.S, this.forEncryption ? byArray3 : byArray, 0, n2);
        this.totalLength += (long)n2;
    }

    private void gHASH(byte[] byArray, byte[] byArray2, int n) {
        for (int i = 0; i < n; i += 16) {
            int n2 = Math.min(n - i, 16);
            this.gHASHPartial(byArray, byArray2, i, n2);
        }
    }

    private void gHASHBlock(byte[] byArray, byte[] byArray2) {
        GCMUtil.xor(byArray, byArray2);
        this.multiplier.multiplyH(byArray);
    }

    private void gHASHPartial(byte[] byArray, byte[] byArray2, int n, int n2) {
        GCMUtil.xor(byArray, byArray2, n, n2);
        this.multiplier.multiplyH(byArray);
    }

    private byte[] getNextCounterBlock() {
        if (this.blocksRemaining == 0) {
            throw new IllegalStateException("Attempt to process too many blocks");
        }
        --this.blocksRemaining;
        int n = 1;
        this.counter[15] = (byte)(n += this.counter[15] & 0xFF);
        n >>>= 8;
        this.counter[14] = (byte)(n += this.counter[14] & 0xFF);
        n >>>= 8;
        this.counter[13] = (byte)(n += this.counter[13] & 0xFF);
        n >>>= 8;
        this.counter[12] = (byte)(n += this.counter[12] & 0xFF);
        byte[] byArray = new byte[16];
        this.cipher.processBlock(this.counter, 0, byArray, 0);
        return byArray;
    }

    private void checkStatus() {
        if (!this.initialised) {
            if (this.forEncryption) {
                throw new IllegalStateException("GCM cipher cannot be reused for encryption");
            }
            throw new IllegalStateException("GCM cipher needs to be initialised");
        }
    }
}

